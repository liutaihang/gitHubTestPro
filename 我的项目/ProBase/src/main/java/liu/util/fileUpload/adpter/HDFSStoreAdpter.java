package liu.util.fileUpload.adpter;

import liu.util.fileUpload.FileStoreException;
import liu.util.fileUpload.util.PropertiesFileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDFSStoreAdpter extends FileStoreAdpter
{
  private static Logger logger = LoggerFactory.getLogger("HDFSStoreAdpter");

  static PropertiesFileUtil pf = null;
  private static final int BUFF = 4096;

  private Configuration getClusterConfig()
  {
    Configuration conf = new Configuration();

    InputStream coreSiteURL = getClass().getClassLoader().getResourceAsStream("core-site.xml");
    if (coreSiteURL == null) {
      throw new RuntimeException("classpath 下未找到 core-site.xml 配置文件");
    }

    InputStream hdfsSiteURL = getClass().getClassLoader().getResourceAsStream("hdfs-site.xml");
    if (hdfsSiteURL == null) {
      throw new RuntimeException("classpath 下未找到 hdfs-site.xml 配置文件");
    }
    conf.addResource(coreSiteURL);
    conf.addResource(hdfsSiteURL);

    return conf;
  }

  private URI getClusterURI()
    throws FileStoreException
  {
    try
    {
      return new URI(pf.getString("fs.defaultFS"));
    } catch (URISyntaxException e) {
      logger.error("读取hdfs配置文件失败，{}", e);
      throw new RuntimeException("读取hdfs配置文件失败", e);
    }
  }

  private FileSystem getFileSystem()
    throws FileStoreException
  {
    try
    {
      return FileSystem.get(getClusterURI(), getClusterConfig(), pf.getString("dfs.user"));
    } catch (IOException|InterruptedException e) {
      logger.error("读取hdfs配置文件失败，{}", e);
      throw new FileStoreException("读取hdfs配置文件失败" + e.getMessage());
    } catch (Exception e) {
      logger.error("读取hdfs配置文件失败，{}", e);
      throw new FileStoreException("读取hdfs配置文件失败" + e.getMessage());
    }
  }

  public String save(String des, String target) throws FileStoreException
  {
    InputStream is = null;
    try {
      is = new FileInputStream(des);
    } catch (FileNotFoundException e) {
      logger.error("上传文件【" + des + "】失败，{}", e);
      throw new FileStoreException("上传文件【" + des + "】失败" + e.getMessage());
    } catch (Exception e) {
      logger.error("上传文件【" + des + "】失败，{}", e);
      throw new FileStoreException("上传文件【" + des + "】失败" + e.getMessage());
    }
    return save(is, target);
  }

  public String save(InputStream is, String target)
    throws FileStoreException
  {
    FileSystem fs = getFileSystem();
    if (StringUtils.isBlank(target))
    {
      target = File.separatorChar + "";
    }

    if (!target.startsWith("/")) {
      target = "/" + target;
    }

    OutputStream out = null;
    try {
      out = fs.create(new Path(target));
      IOUtils.copyBytes(is, out, 4096, true);
    } catch (IOException e) {
      logger.error("上传文件失败，{}", e);
      throw new FileStoreException("上传文件失败" + e.getMessage());
    } catch (Exception e) {
      logger.error("上传文件失败，{}", e);
      throw new FileStoreException("上传文件失败" + e.getMessage());
    } finally {
      try {
        fs.close();
      } catch (IOException e) {
        logger.error("关闭文件流失败，{}", e);
        throw new FileStoreException("关闭文件流失败" + e.getMessage());
      }
    }

    return target;
  }

  public boolean delete(String target)
    throws FileStoreException
  {
    if (StringUtils.isBlank(target)) {
      return false;
    }

    if (!target.startsWith("/")) {
      target = "/" + target;
    }

    FileSystem fs = null;
    try {
      fs = FileSystem.get(getClusterURI(), getClusterConfig(), pf.getString("dfs.user"));
      return fs.delete(new Path(target), true);
    } catch (IOException|InterruptedException e) {
      logger.error("删除文件【" + target + "】失败，{}", e);
      throw new FileStoreException("删除文件【" + target + "】失败" + e.getMessage());
    } catch (Exception e) {
      logger.error("删除文件【" + target + "】失败，{}", e);
      throw new FileStoreException("删除文件【" + target + "】失败" + e.getMessage());
    } finally {
      try {
        fs.close();
      } catch (IOException e) {
        logger.error("关闭文件流失败，{}", e);
        throw new FileStoreException("关闭文件流失败" + e.getMessage());
      }
    }
  }

  public InputStream get(String target)
    throws FileStoreException
  {
    if (StringUtils.isBlank(target)) {
      return null;
    }
    FileSystem fs = getFileSystem();
    FSDataInputStream fis = null;
    try {
      fis = fs.open(new Path(target));
    } catch (IllegalArgumentException|IOException e) {
      logger.error("下载文件【" + target + "】失败，{}", e);
      throw new FileStoreException("下载文件【" + target + "】失败" + e.getMessage());
    } catch (Exception e) {
      logger.error("下载文件【" + target + "】失败，{}", e);
      throw new FileStoreException("下载文件【" + target + "】失败" + e.getMessage());
    }
    return fis;
  }

  public boolean down(String target, String savePath)
    throws FileStoreException
  {
    InputStream is = get(target);

    if (!target.startsWith("/")) {
      target = "/" + target;
    }

    try
    {
      OutputStream os = new FileOutputStream(savePath);
      IOUtils.copyBytes(is, os, 4096, true);
      return true;
    } catch (FileNotFoundException e) {
      logger.error("下载文件【" + target + "】失败，{}", e);
      throw new FileStoreException("下载文件【" + target + "】失败" + e.getMessage());
    } catch (IOException e) {
      logger.error("下载文件【" + target + "】失败，{}", e);
      throw new FileStoreException("下载文件【" + target + "】失败" + e.getMessage());
    }
  }

  public boolean mkdirs(String dirPath)
    throws FileStoreException
  {
    if (StringUtils.isBlank(dirPath)) {
      return false;
    }
    FileSystem fs = getFileSystem();
    try {
      fs.mkdirs(new Path(dirPath));
      return true;
    } catch (IllegalArgumentException|IOException e) {
      logger.error("创建目录失败，{}", e);
      throw new FileStoreException("创建目录失败" + e.getMessage());
    } finally {
      try {
        fs.close();
      } catch (IOException e) {
        logger.error("关闭文件流失败，{}", e);
        throw new FileStoreException("关闭文件流失败" + e.getMessage());
      }
    }
  }

  public boolean rename(String filePath, String newFileName)
    throws FileStoreException
  {
    if (StringUtils.isBlank(filePath)) {
      return false;
    }

    if (!filePath.startsWith("/")) {
      filePath = "/" + filePath;
    }
    FileSystem fs = getFileSystem();
    Path srcPath = new Path(filePath);
    Path targetPath = new Path(srcPath.getParent(), newFileName);
    try
    {
      return fs.rename(srcPath, targetPath);
    } catch (IOException e) {
      logger.error("文件重命名失败，{}", e);
      throw new FileStoreException("文件重命名失败" + e.getMessage());
    } finally {
      try {
        fs.close();
      } catch (IOException e) {
        logger.error("关闭文件流失败，{}", e);
        throw new FileStoreException("关闭文件流失败" + e.getMessage());
      }
    }
  }

  public List<String> listFiles(String dirPath)
    throws FileStoreException
  {
    if (StringUtils.isBlank(dirPath)) {
      return null;
    }
    List paths = new ArrayList(5);
    try
    {
      FileSystem fs = getFileSystem();

      FileStatus[] fileStatus = fs.listStatus(new Path(dirPath));
      if (ArrayUtils.isEmpty(fileStatus)) {
        return null;
      }
      for (FileStatus fStatus : fileStatus)
        paths.add(fStatus.getPath().toUri().getPath());
    }
    catch (IOException e)
    {
      logger.error("列出文件目录失败，{}", e);
      throw new FileStoreException("列出文件目录失败" + e.getMessage());
    } catch (Exception e) {
      logger.error("列出文件目录失败，{}", e);
      throw new FileStoreException("列出文件目录失败" + e.getMessage());
    }
    return paths;
  }

  public void unzipOnServer(String zipFileName)
    throws FileStoreException
  {
    InputStream in = null;
    OutputStream out = null;
    try {
      FileSystem fs = getFileSystem();

      Path inputPath = new Path(zipFileName);
      CompressionCodecFactory factory = new CompressionCodecFactory(fs.getConf());
      CompressionCodec codec = factory.getCodec(inputPath);
      if (codec == null) {
        logger.error("no codec found for " + zipFileName);
        System.exit(1);
      }
      String outputUri = CompressionCodecFactory.removeSuffix(zipFileName, codec.getDefaultExtension());

      in = codec.createInputStream(fs.open(inputPath));
      out = fs.create(new Path(outputUri));
      IOUtils.copyBytes(in, out, fs.getConf());
    } catch (FileStoreException e) {
      logger.error("解压文件失败，{}", e);
      throw e;
    } catch (Exception e) {
      logger.error("解压文件失败，{}", e);
      throw new FileStoreException("解压文件失败" + e.getMessage());
    } finally {
      IOUtils.closeStream(out);
      IOUtils.closeStream(in);
    }
  }

  static
  {
//    try
//    {
      pf = PropertiesFileUtil.builder("hdfs.properties");
//    } catch (ConfigurationException e) {
//      logger.error("加载hdfs配置文件出错，{}", e);
//      throw new RuntimeException("加载hdfs配置文件失败", e);
//    }
  }
}