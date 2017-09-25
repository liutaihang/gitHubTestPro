package liu.util.fileUpload.adpter;

import liu.util.fileUpload.FileStoreException;
import java.io.InputStream;
import java.util.List;

public abstract class FileStoreAdpter
{
  protected static String default_result_str = "";

  protected static boolean default_result_bool = false;

  protected static InputStream default_result_is = null;

  public String save(String des, String target)
    throws FileStoreException
  {
    throw new FileStoreException("文件保存错误");
  }

  public String save(InputStream is, String target)
    throws FileStoreException
  {
    throw new FileStoreException("文件保存错误");
  }

  public boolean delete(String target)
    throws FileStoreException
  {
    throw new FileStoreException("文件删除错误");
  }

  public InputStream get(String target)
    throws FileStoreException
  {
    throw new FileStoreException("获取文件错误");
  }

  public boolean down(String target, String savePath)
    throws FileStoreException
  {
    throw new FileStoreException("下载文件错误");
  }

  public boolean rename(String filePath, String newFileName)
    throws FileStoreException
  {
    throw new FileStoreException("修改文件名称错误");
  }

  public boolean mkdirs(String dirPath)
    throws FileStoreException
  {
    throw new FileStoreException("创建目录错误");
  }

  public List<String> listFiles(String dirPath)
    throws FileStoreException
  {
    throw new FileStoreException("列出所有的文件错误");
  }

  public void unzipOnServer(String zipFileName)
    throws FileStoreException
  {
  }
}