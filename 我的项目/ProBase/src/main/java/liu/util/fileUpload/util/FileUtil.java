package liu.util.fileUpload.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

public abstract class FileUtil
{
  public static File readFile(String path)
  {
    return FileUtils.getFile(new String[] { path });
  }

  public static List<String[]> parseFileByComma(String path, String comma)
    throws IOException
  {
    return filePaser(path, comma);
  }

  public static List<String[]> parseFileByLine(String path, String line)
    throws IOException
  {
    return filePaser(path, line);
  }

  public static List<String[]> parseFileByDoubleLine(String path, String doubleLine)
    throws IOException
  {
    return filePaser(path, doubleLine);
  }

  public static List<String[]> filePaser(String path, String symbol)
    throws IOException
  {
    File file = readFile(path);
    BufferedReader reader = new BufferedReader(new FileReader(file));
    List data = new ArrayList();
    String str = null;
    while ((str = reader.readLine()) != null) {
      data.add(str.split(symbol));
    }
    reader.close();
    return data;
  }
}