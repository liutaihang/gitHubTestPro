package liu.util.fileUpload;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class StringUtil
/*     */ {
/*     */   private StringUtil() {}
/*     */   
/*     */   public static boolean isMobileNO(String mobile)
/*     */   {
/*  42 */     if (StringUtils.isBlank(mobile)) {
/*  43 */       return false;
/*     */     }
/*  45 */     String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|170|147)\\d{8}$";
/*  46 */     return mobile.matches(regex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean checkChinese(String str)
/*     */   {
/*  61 */     if (StringUtils.isBlank(str)) {
/*  62 */       return false;
/*     */     }
/*  64 */     str = new String(str.getBytes());
/*  65 */     String regex = "^[一-龥]+$";
/*  66 */     return str.matches(regex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean checkByPattern(String str, String regex)
/*     */   {
/*  81 */     if (StringUtils.isBlank(str)) {
/*  82 */       return false;
/*     */     }
/*  84 */     return str.matches(regex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String UUIDGenerate()
/*     */   {
/*  95 */     return UUID.randomUUID().toString().replaceAll("-", "");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] gzip(String msg)
/*     */     throws IOException
/*     */   {
/* 108 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 109 */     GZIPOutputStream gzip = null;
/*     */     try {
/* 111 */       gzip = new GZIPOutputStream(out);
/* 112 */       gzip.write(msg.getBytes());
/*     */     } catch (IOException e) {
/* 114 */       e.printStackTrace();
/*     */     } finally {
/* 116 */       if (gzip != null) {
/* 117 */         gzip.close();
/*     */       }
/* 119 */       IOUtils.closeQuietly(out);
/*     */     }
/* 121 */     return out.toByteArray();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] gzip(byte[] msg)
/*     */     throws IOException
/*     */   {
/* 134 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 135 */     GZIPOutputStream gzip = null;
/*     */     try {
/* 137 */       gzip = new GZIPOutputStream(out);
/* 138 */       gzip.write(msg);
/*     */     } catch (IOException e) {
/* 140 */       e.printStackTrace();
/*     */     } finally {
/* 142 */       if (gzip != null) {
/* 143 */         gzip.close();
/*     */       }
/* 145 */       IOUtils.closeQuietly(out);
/*     */     }
/* 147 */     return out.toByteArray();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] unZip(String msg)
/*     */     throws IOException
/*     */   {
/* 161 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 162 */     ByteArrayInputStream in = new ByteArrayInputStream(msg.getBytes());
/* 163 */     GZIPInputStream gunzip = new GZIPInputStream(in);
/* 164 */     byte[] buffer = new byte['?'];
/*     */     int n;
/* 166 */     while ((n = gunzip.read(buffer)) >= 0) {
/* 167 */       out.write(buffer, 0, n);
/*     */     }
/* 169 */     gunzip.close();
/*     */     
/* 171 */     IOUtils.closeQuietly(in);
/* 172 */     IOUtils.closeQuietly(out);
/*     */     
/* 174 */     return out.toByteArray();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String unZip(byte[] msg)
/*     */     throws IOException
/*     */   {
/* 188 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 189 */     ByteArrayInputStream in = new ByteArrayInputStream(msg);
/* 190 */     GZIPInputStream gunzip = new GZIPInputStream(in);
/* 191 */     byte[] buffer = new byte['?'];
/*     */     int n;
/* 193 */     while ((n = gunzip.read(buffer)) >= 0) {
/* 194 */       out.write(buffer, 0, n);
/*     */     }
/* 196 */     gunzip.close();
/*     */     
/* 198 */     IOUtils.closeQuietly(in);
/* 199 */     IOUtils.closeQuietly(out);
/*     */     
/*     */ 
/* 202 */     return new String(out.toByteArray(), "utf-8");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] unZip2(byte[] msg)
/*     */     throws IOException
/*     */   {
/* 215 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 216 */     ByteArrayInputStream in = new ByteArrayInputStream(msg);
/* 217 */     GZIPInputStream gunzip = new GZIPInputStream(in);
/* 218 */     byte[] buffer = new byte['?'];
/*     */     int n;
/* 220 */     while ((n = gunzip.read(buffer)) >= 0) {
/* 221 */       out.write(buffer, 0, n);
/*     */     }
/* 223 */     gunzip.close();
/* 224 */     IOUtils.closeQuietly(in);
/* 225 */     IOUtils.closeQuietly(out);
/* 226 */     return out.toByteArray();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isBlank(String val)
/*     */   {
/* 241 */     return StringUtils.isBlank(val);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isNotBlank(String val)
/*     */   {
/* 253 */     return StringUtils.isNotBlank(val);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean equalsIgnoreCase(String str1, String str2)
/*     */   {
/* 275 */     return StringUtils.equalsIgnoreCase(str1, str2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean equals(String str1, String str2)
/*     */   {
/* 297 */     return StringUtils.equals(str1, str2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String substringBeforeLast(String str, String separator)
/*     */   {
/* 310 */     return StringUtils.substringBeforeLast(str, separator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String substringAfterLast(String str, String separator)
/*     */   {
/* 323 */     return StringUtils.substringAfterLast(str, separator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String substringBetween(String str, String separator1, String separator2)
/*     */   {
/* 337 */     return StringUtils.substringBetween(str, separator1, separator2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isDigital(String str)
/*     */   {
/* 349 */     return isBlank(str) ? false : str.matches("^[0-9]*$");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isNotDigital(String str)
/*     */   {
/* 361 */     return !isDigital(str);
/*     */   }
/*     */   
/*     */   public static String joinString(List<?> params, String separator) {
/* 365 */     if (params == null) {
/* 366 */       return null;
/*     */     }
/* 368 */     return StringUtils.join(params, separator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String replaceStringByParamValue(String src, Map<String, String> data)
/*     */   {
/* 384 */     if (data == null) {
/* 385 */       return src;
/*     */     }
/* 387 */     Set<Map.Entry<String, String>> set = data.entrySet();
/* 388 */     Iterator<Map.Entry<String, String>> it = set.iterator();
/* 389 */     while (it.hasNext()) {
/* 390 */       Map.Entry<String, String> entry = (Map.Entry)it.next();
/* 391 */       src = src.replace((CharSequence)entry.getKey(), (CharSequence)entry.getValue());
/*     */     }
/* 393 */     return src;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isPicture(String fileName)
/*     */   {
/* 408 */     boolean isPic = true;
/* 409 */     if (fileName.endsWith("gif")) {
/* 410 */       return isPic;
/*     */     }
/*     */     
/* 413 */     if (fileName.endsWith("jpg")) {
/* 414 */       return isPic;
/*     */     }
/* 416 */     if (fileName.endsWith("png")) {
/* 417 */       return isPic;
/*     */     }
/* 419 */     if (fileName.endsWith("bmp")) {
/* 420 */       return isPic;
/*     */     }
/* 422 */     return !isPic;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int random(int n)
/*     */   {
/* 434 */     return new Random().nextInt(n);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\.m2\repository\lyc\file-store\cd-dev-1.0\file-store-cd-dev-1.0.jar
 * Qualified Name:     com.lyc.store.util.StringUtil
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.0.1
 */