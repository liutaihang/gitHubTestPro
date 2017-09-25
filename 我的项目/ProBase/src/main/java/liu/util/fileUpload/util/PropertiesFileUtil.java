package liu.util.fileUpload.util;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;

public class PropertiesFileUtil extends FileUtil
{
  private static PropertiesConfiguration configuration = null;

  public static PropertiesFileUtil builder(String filePath)
//    throws ConfigurationException
  {
    if (StringUtils.isBlank(filePath)) {
      return null;
    }
    InputStream is = PropertiesFileUtil.class.getClassLoader().getResourceAsStream(filePath);
//    configuration.load(is);

    return new PropertiesFileUtil();
  }

  public String getFileLine(int number)
  {
    int i = 1;
    String line = "";
    for (Iterator keys = configuration.getKeys(); keys.hasNext(); ) {
      if (i == number) {
        line = (String)keys.next();
      }
      i++;
    }
    return line;
  }

  public Iterator<String> getKeys()
  {
    return configuration.getKeys();
  }

  public String getString(String key)
  {
    return getString(key, "");
  }

  public String getString(String key, String defaultStr) {
    return configuration.getString(key, defaultStr);
  }

  public String[] getStringArray(String key) {
    return configuration.getStringArray(key);
  }

  public int getInt(String key) {
    return getInt(key, 0);
  }

  public int getInt(String key, int defaultValue) {
    return configuration.getInt(key, defaultValue);
  }

  public short getShort(String key) {
    return configuration.getShort(key, (short)0);
  }

  public long getLong(String key) {
    return configuration.getLong(key, 0L);
  }

  public Boolean getBoolean(String key) {
    return Boolean.valueOf(configuration.getBoolean(key, false));
  }

  static
  {
    if (configuration == null)
      configuration = new PropertiesConfiguration();
  }
}