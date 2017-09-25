package liu.util.fileUpload.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public final class StringUtil
{
  public static boolean isMobileNO(String mobile)
  {
    if (StringUtils.isBlank(mobile)) {
      return false;
    }
    String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|170|147)\\d{8}$";
    return mobile.matches(regex);
  }

  public static boolean checkChinese(String str)
  {
    if (StringUtils.isBlank(str)) {
      return false;
    }
    str = new String(str.getBytes());
    String regex = "^[一-龥]+$";
    return str.matches(regex);
  }

  public static boolean checkByPattern(String str, String regex)
  {
    if (StringUtils.isBlank(str)) {
      return false;
    }
    return str.matches(regex);
  }

  public static String UUIDGenerate()
  {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  public static byte[] gzip(String msg)
    throws IOException
  {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    GZIPOutputStream gzip = null;
    try {
      gzip = new GZIPOutputStream(out);
      gzip.write(msg.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (gzip != null) {
        gzip.close();
      }
      IOUtils.closeQuietly(out);
    }
    return out.toByteArray();
  }

  public static byte[] gzip(byte[] msg)
    throws IOException
  {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    GZIPOutputStream gzip = null;
    try {
      gzip = new GZIPOutputStream(out);
      gzip.write(msg);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (gzip != null) {
        gzip.close();
      }
      IOUtils.closeQuietly(out);
    }
    return out.toByteArray();
  }

  public static byte[] unZip(String msg)
    throws IOException
  {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ByteArrayInputStream in = new ByteArrayInputStream(msg.getBytes());
    GZIPInputStream gunzip = new GZIPInputStream(in);
    byte[] buffer = new byte[4096];
    int n;
    while ((n = gunzip.read(buffer)) >= 0) {
      out.write(buffer, 0, n);
    }
    gunzip.close();

    IOUtils.closeQuietly(in);
    IOUtils.closeQuietly(out);

    return out.toByteArray();
  }

  public static String unZip(byte[] msg)
    throws IOException
  {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ByteArrayInputStream in = new ByteArrayInputStream(msg);
    GZIPInputStream gunzip = new GZIPInputStream(in);
    byte[] buffer = new byte[4096];
    int n;
    while ((n = gunzip.read(buffer)) >= 0) {
      out.write(buffer, 0, n);
    }
    gunzip.close();

    IOUtils.closeQuietly(in);
    IOUtils.closeQuietly(out);

    return new String(out.toByteArray(), "utf-8");
  }

  public static byte[] unZip2(byte[] msg)
    throws IOException
  {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ByteArrayInputStream in = new ByteArrayInputStream(msg);
    GZIPInputStream gunzip = new GZIPInputStream(in);
    byte[] buffer = new byte[4096];
    int n;
    while ((n = gunzip.read(buffer)) >= 0) {
      out.write(buffer, 0, n);
    }
    gunzip.close();
    IOUtils.closeQuietly(in);
    IOUtils.closeQuietly(out);
    return out.toByteArray();
  }

  public static boolean isBlank(String val)
  {
    return StringUtils.isBlank(val);
  }

  public static boolean isNotBlank(String val)
  {
    return StringUtils.isNotBlank(val);
  }

  public static boolean equalsIgnoreCase(String str1, String str2)
  {
    return StringUtils.equalsIgnoreCase(str1, str2);
  }

  public static boolean equals(String str1, String str2)
  {
    return StringUtils.equals(str1, str2);
  }

  public static String substringBeforeLast(String str, String separator)
  {
    return StringUtils.substringBeforeLast(str, separator);
  }

  public static String substringAfterLast(String str, String separator)
  {
    return StringUtils.substringAfterLast(str, separator);
  }

  public static String substringBetween(String str, String separator1, String separator2)
  {
    return StringUtils.substringBetween(str, separator1, separator2);
  }

  public static boolean isDigital(String str)
  {
    return isBlank(str) ? false : str.matches("^[0-9]*$");
  }

  public static boolean isNotDigital(String str)
  {
    return !isDigital(str);
  }

  public static String joinString(List<?> params, String separator) {
    if (params == null) {
      return null;
    }
    return StringUtils.join(params, separator);
  }

  public static String replaceStringByParamValue(String src, Map<String, String> data)
  {
    if (data == null) {
      return src;
    }
    Set set = data.entrySet();
    Iterator it = set.iterator();
    while (it.hasNext()) {
      Map.Entry entry = (Map.Entry)it.next();
      src = src.replace((CharSequence)entry.getKey(), (CharSequence)entry.getValue());
    }
    return src;
  }

  public static boolean isPicture(String fileName)
  {
    boolean isPic = true;
    if (fileName.endsWith("gif")) {
      return isPic;
    }

    if (fileName.endsWith("jpg")) {
      return isPic;
    }
    if (fileName.endsWith("png")) {
      return isPic;
    }
    if (fileName.endsWith("bmp")) {
      return isPic;
    }
    return !isPic;
  }

  public static int random(int n)
  {
    return new Random().nextInt(n);
  }
}