package liu.util.fileUpload;
/*     */ 
/*     */ import com.lyc.store.FileStoreException;
/*     */ import java.io.InputStream;
/*     */ import java.util.List;
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
/*     */ public abstract class FileStoreAdpter
/*     */ {
/*  19 */   protected static String default_result_str = "";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  24 */   protected static boolean default_result_bool = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  29 */   protected static InputStream default_result_is = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FileStoreAdpter() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String save(String des, String target)
/*     */     throws FileStoreException
/*     */   {
/*  43 */     throw new FileStoreException("文件保存错误");
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
/*     */   public String save(InputStream is, String target)
/*     */     throws FileStoreException
/*     */   {
/*  58 */     throw new FileStoreException("文件保存错误");
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
/*     */   public boolean delete(String target)
/*     */     throws FileStoreException
/*     */   {
/*  72 */     throw new FileStoreException("文件删除错误");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public InputStream get(String target)
/*     */     throws FileStoreException
/*     */   {
/*  85 */     throw new FileStoreException("获取文件错误");
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
/*     */   public boolean down(String target, String savePath)
/*     */     throws FileStoreException
/*     */   {
/* 100 */     throw new FileStoreException("下载文件错误");
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
/*     */   public boolean rename(String filePath, String newFileName)
/*     */     throws FileStoreException
/*     */   {
/* 115 */     throw new FileStoreException("修改文件名称错误");
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
/*     */   public boolean mkdirs(String dirPath)
/*     */     throws FileStoreException
/*     */   {
/* 129 */     throw new FileStoreException("创建目录错误");
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
/*     */   public List<String> listFiles(String dirPath)
/*     */     throws FileStoreException
/*     */   {
/* 143 */     throw new FileStoreException("列出所有的文件错误");
/*     */   }
/*     */   
/*     */   public void unzipOnServer(String zipFileName)
/*     */     throws FileStoreException
/*     */   {}
/*     */ }

/* Location:           C:\Users\Administrator\.m2\repository\lyc\file-store\cd-dev-1.0\file-store-cd-dev-1.0.jar
 * Qualified Name:     com.lyc.store.adpter.FileStoreAdpter
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.0.1
 */