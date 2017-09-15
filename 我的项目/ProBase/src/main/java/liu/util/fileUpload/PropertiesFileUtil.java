package liu.util.fileUpload;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.configuration.ConfigurationException;
/*     */ import org.apache.commons.configuration.PropertiesConfiguration;
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
/*     */ public class PropertiesFileUtil
/*     */   extends FileUtil
/*     */ {
/*  21 */   private static PropertiesConfiguration configuration = null;
/*     */   
/*     */   static {
/*  24 */     if (configuration == null) {
/*  25 */       configuration = new PropertiesConfiguration();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static PropertiesFileUtil builder(String filePath)
/*     */     throws ConfigurationException
/*     */   {
/*  39 */     if (StringUtils.isBlank(filePath)) {
/*  40 */       return null;
/*     */     }
/*  42 */     InputStream is = PropertiesFileUtil.class.getClassLoader().getResourceAsStream(filePath);
/*  43 */     configuration.load(is);
/*     */     
/*  45 */     return new PropertiesFileUtil();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getFileLine(int number)
/*     */   {
/*  56 */     int i = 1;
/*  57 */     String line = "";
/*  58 */     for (Iterator<String> keys = configuration.getKeys(); keys.hasNext();) {
/*  59 */       if (i == number) {
/*  60 */         line = (String)keys.next();
/*     */       }
/*  62 */       i++;
/*     */     }
/*  64 */     return line;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<String> getKeys()
/*     */   {
/*  74 */     return configuration.getKeys();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getString(String key)
/*     */   {
/*  85 */     return getString(key, "");
/*     */   }
/*     */   
/*     */   public String getString(String key, String defaultStr) {
/*  89 */     return configuration.getString(key, defaultStr);
/*     */   }
/*     */   
/*     */   public String[] getStringArray(String key) {
/*  93 */     return configuration.getStringArray(key);
/*     */   }
/*     */   
/*     */   public int getInt(String key) {
/*  97 */     return getInt(key, 0);
/*     */   }
/*     */   
/*     */   public int getInt(String key, int defaultValue) {
/* 101 */     return configuration.getInt(key, defaultValue);
/*     */   }
/*     */   
/*     */   public short getShort(String key) {
/* 105 */     return configuration.getShort(key, (short)0);
/*     */   }
/*     */   
/*     */   public long getLong(String key) {
/* 109 */     return configuration.getLong(key, 0L);
/*     */   }
/*     */   
/*     */   public Boolean getBoolean(String key) {
/* 113 */     return Boolean.valueOf(configuration.getBoolean(key, false));
/*     */   }
/*     */   
/*     */   public PropertiesFileUtil() {}
/*     */ }

/* Location:           C:\Users\Administrator\.m2\repository\lyc\file-store\cd-dev-1.0\file-store-cd-dev-1.0.jar
 * Qualified Name:     com.lyc.store.util.PropertiesFileUtil
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.0.1
 */