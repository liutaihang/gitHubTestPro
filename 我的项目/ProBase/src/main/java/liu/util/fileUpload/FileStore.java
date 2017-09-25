package liu.util.fileUpload;

import liu.util.fileUpload.adpter.FileStoreAdpter;
import liu.util.fileUpload.util.StringUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileStore
{
  private static Logger LOG = LoggerFactory.getLogger(FileStore.class);

  public static StoreFactory builderStoreFactory(FileStoreAdpter storeAdpter)
  {
    if (storeAdpter == null) {
      LOG.debug("The storage adpter cannot be empty ");
      return null;
    }
    return new StoreFactory(storeAdpter, null);
  }

  public static class StoreFactory
  {
    private FileStoreAdpter storeAdpter;

    private StoreFactory(FileStoreAdpter storeAdpter, Object object)
    {
      this.storeAdpter = storeAdpter;
    }

    public String upload(String des, String target)
      throws FileStoreException
    {
      String path = target.substring(0, target.lastIndexOf(".")).replace("/", "");

      if (!fileNameIsRight(path)) {
        throw new FileStoreException("文件名称只能包含中文、数字、字母和下划线");
      }

      try
      {
        if (target.endsWith(".zip")) {
          checkZipFile(new FileInputStream(des));
        }
        synchronized (this.storeAdpter) {
          return this.storeAdpter.save(des, target);
        }
      } catch (Exception e) {
        FileStore.LOG.error("upload file exception {}", e);
        throw new FileStoreException(new StringBuilder().append("上传文件【").append(des).append("】失败").append(e.getMessage()).toString());
      }
    }

    public String upload(InputStream is, String target)
      throws FileStoreException
    {
      String path = target.substring(0, target.lastIndexOf(".")).replace("/", "");
      if (!fileNameIsRight(path)) {
        throw new FileStoreException("文件名称只能包含中文、数字、字母和下划线");
      }

      if (target.endsWith(".zip")) {
        checkZipFile(is);
      }
      synchronized (this.storeAdpter)
      {
        try {
          return this.storeAdpter.save(is, target);
        } catch (Exception e) {
          FileStore.LOG.error("upload file exception {}", e);
          throw new FileStoreException(new StringBuilder().append("上传文件失败 ").append(e.getMessage()).toString());
        }
      }
    }

    private boolean fileNameIsRight(String fileName)
    {
      String regex = "^[一-龥a-zA-Z0-9_]*$";
      return fileName.matches(regex);
    }

    public void checkZipFile(InputStream inStream)
      throws FileStoreException
    {
    }

    public boolean down(String target, String savePath)
      throws FileStoreException
    {
      try
      {
        return this.storeAdpter.down(target, savePath);
      } catch (Throwable e) {
        FileStore.LOG.error("down file exception {}", e);
        throw new FileStoreException(new StringBuilder().append("下载文件【 ").append(target).append("】失败").append(e.getMessage()).toString());
      }
    }

    public InputStream down(String target)
      throws FileStoreException
    {
      InputStream is = null;
      try {
        is = this.storeAdpter.get(target); } catch (Throwable e) {
        e = 
          e;

        FileStore.LOG.error("down fileStream exception {}", e);
        throw new FileStoreException(new StringBuilder().append("下载文件【 ").append(target).append("】失败").append(e.getMessage()).toString());
      }
      finally {
      }
      return is;
    }

    public boolean delete(String target)
      throws FileStoreException
    {
      try
      {
        return this.storeAdpter.delete(target);
      } catch (Throwable e) {
        FileStore.LOG.error("delete file exception {}", e);
        throw new FileStoreException(new StringBuilder().append("删除文件【").append(target).append("】失败").append(e.getMessage()).toString());
      }
    }

    public String uncompress(String fileName)
      throws FileStoreException
    {
      if (!fileName.endsWith(".zip")) {
        FileStore.LOG.debug("Compress file not endsWith .zip ");
        throw new FileStoreException(new StringBuilder().append("解压文件【").append(fileName).append("】,文件不是zip文件不能解压").toString());
      }
      try {
        int BUFF = 4096;

        InputStream inStream = this.storeAdpter.get(fileName);

        int lastPosition = fileName.lastIndexOf(".");
        String suffix = null; String fileDir = "/";
        if (lastPosition > 0) {
          fileDir = fileName.substring(0, lastPosition);
          suffix = fileName.substring(lastPosition + 1);
        }

        File file = new File(new StringBuilder().append(System.getProperty("java.io.tmpdir")).append(System.currentTimeMillis()).append(".").append(StringUtil.isBlank(suffix) ? "zip" : suffix).toString());
        OutputStream outStram = new FileOutputStream(file);
        IOUtils.copyBytes(inStream, outStram, BUFF, true);

        ZipFile zipFile = new ZipFile(file, "GBK");
        Enumeration entries = zipFile.getEntries();
        while (entries.hasMoreElements()) {
          ZipArchiveEntry zae = (ZipArchiveEntry)entries.nextElement();

          String fsSaveFilePath = new StringBuilder().append(fileDir).append("/").append(zae.getName()).toString();
          if (zae.isDirectory()) {
            this.storeAdpter.mkdirs(fsSaveFilePath);
          }
          else {
            BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(zae));

            upload(bis, fsSaveFilePath);
            if (bis != null) {
              try {
                bis.close();
              } catch (Exception e) {
                e.printStackTrace();
              } finally {
                bis.close();
              }
            }
          }
        }
        if (zipFile != null) {
          try {
            zipFile.close();
          } catch (Exception e) {
            e.printStackTrace();
          } finally {
            zipFile.close();
          }
        }

        if (file.exists()) {
          file.delete();
        }
        return fileDir;
      } catch (FileStoreException e) {
        FileStore.LOG.error(e.getMessage());
        throw new FileStoreException(new StringBuilder().append("解压文件【").append(fileName).append("】失败:").append(e.getMessage()).toString());
      } catch (Exception e) {
        FileStore.LOG.error("uncompress zip file exception {}", e);
        throw new FileStoreException(new StringBuilder().append("解压文件【").append(fileName).append("】失败:").append(e.getMessage()).toString());
      }
    }

    public boolean rename(String filePath, String newName)
      throws FileStoreException
    {
      try
      {
        return this.storeAdpter.rename(filePath, newName);
      } catch (Throwable e) {
        FileStore.LOG.error("rename file exception {}", e);
        throw new FileStoreException(new StringBuilder().append("重命名文件【").append(filePath).append("】到【").append(newName).append("】失败").append(e.getMessage()).toString());
      }
    }

    public List<String> listFiles(String dirPath)
      throws FileStoreException
    {
      try
      {
        return this.storeAdpter.listFiles(dirPath);
      } catch (Throwable e) {
        FileStore.LOG.error("get dir path fail {}", e);
        throw new FileStoreException(new StringBuilder().append("获取目录【").append(dirPath).append("】下级目录失败").append(e.getMessage()).toString());
      }
    }

    public String uncompressOnServer(String zipFileName)
      throws Exception
    {
      if (!zipFileName.endsWith(".zip")) {
        throw new FileStoreException(new StringBuilder().append("解压文件【").append(zipFileName).append("】,文件不是zip文件不能解压").toString());
      }
      this.storeAdpter.unzipOnServer(zipFileName);
      return null;
    }
  }
}