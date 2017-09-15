package liu.util.fileUpload;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;

/*     */ import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.zip.ZipFile;
/*     */ import org.apache.hadoop.io.IOUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;

import com.lyc.store.FileStoreException;
/*     */ 
/*     */ import com.lyc.store.adpter.FileStoreAdpter;
/*     */ import com.lyc.store.util.StringUtil;
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
/*     */ public class FileStore
/*     */ {
/*  29 */   private static Logger LOG = LoggerFactory.getLogger(FileStore.class);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private FileStore() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static StoreFactory builderStoreFactory(FileStoreAdpter storeAdpter)
/*     */   {
/*  48 */     if (storeAdpter == null) {
/*  49 */       LOG.debug("The storage adpter cannot be empty ");
/*  50 */       return null;
/*     */     }
/*  52 */     return new StoreFactory(storeAdpter, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class StoreFactory
/*     */   {
/*     */     private FileStoreAdpter storeAdpter;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private StoreFactory(FileStoreAdpter storeAdpter)
/*     */     {
/*  67 */       this.storeAdpter = storeAdpter;
/*     */     }
/*     */     
/*     */     /* Error */
/*     */     public String upload(String des, String target)
/*     */       throws FileStoreException
/*     */     {
/*     */       // Byte code:
/*     */       //   0: aload_2
/*     */       //   1: iconst_0
/*     */       //   2: aload_2
/*     */       //   3: ldc 4
/*     */       //   5: invokevirtual 5	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
/*     */       //   8: invokevirtual 6	java/lang/String:substring	(II)Ljava/lang/String;
/*     */       //   11: ldc 7
/*     */       //   13: ldc 8
/*     */       //   15: invokevirtual 9	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
/*     */       //   18: astore_3
/*     */       //   19: aload_0
/*     */       //   20: aload_3
/*     */       //   21: invokespecial 10	com/lyc/store/FileStore$StoreFactory:fileNameIsRight	(Ljava/lang/String;)Z
/*     */       //   24: ifne +13 -> 37
/*     */       //   27: new 11	com/lyc/store/FileStoreException
/*     */       //   30: dup
/*     */       //   31: ldc 12
/*     */       //   33: invokespecial 13	com/lyc/store/FileStoreException:<init>	(Ljava/lang/String;)V
/*     */       //   36: athrow
/*     */       //   37: aload_2
/*     */       //   38: ldc 14
/*     */       //   40: invokevirtual 15	java/lang/String:endsWith	(Ljava/lang/String;)Z
/*     */       //   43: ifeq +15 -> 58
/*     */       //   46: aload_0
/*     */       //   47: new 16	java/io/FileInputStream
/*     */       //   50: dup
/*     */       //   51: aload_1
/*     */       //   52: invokespecial 17	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
/*     */       //   55: invokevirtual 18	com/lyc/store/FileStore$StoreFactory:checkZipFile	(Ljava/io/InputStream;)V
/*     */       //   58: aload_0
/*     */       //   59: getfield 3	com/lyc/store/FileStore$StoreFactory:storeAdpter	Lcom/lyc/store/adpter/FileStoreAdpter;
/*     */       //   62: dup
/*     */       //   63: astore 4
/*     */       //   65: monitorenter
/*     */       //   66: aload_0
/*     */       //   67: getfield 3	com/lyc/store/FileStore$StoreFactory:storeAdpter	Lcom/lyc/store/adpter/FileStoreAdpter;
/*     */       //   70: aload_1
/*     */       //   71: aload_2
/*     */       //   72: invokevirtual 19	com/lyc/store/adpter/FileStoreAdpter:save	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/*     */       //   75: aload 4
/*     */       //   77: monitorexit
/*     */       //   78: areturn
/*     */       //   79: astore 5
/*     */       //   81: aload 4
/*     */       //   83: monitorexit
/*     */       //   84: aload 5
/*     */       //   86: athrow
/*     */       //   87: astore 4
/*     */       //   89: invokestatic 21	com/lyc/store/FileStore:access$100	()Lorg/slf4j/Logger;
/*     */       //   92: ldc 22
/*     */       //   94: aload 4
/*     */       //   96: invokeinterface 23 3 0
/*     */       //   101: new 11	com/lyc/store/FileStoreException
/*     */       //   104: dup
/*     */       //   105: new 24	java/lang/StringBuilder
/*     */       //   108: dup
/*     */       //   109: invokespecial 25	java/lang/StringBuilder:<init>	()V
/*     */       //   112: ldc 26
/*     */       //   114: invokevirtual 27	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */       //   117: aload_1
/*     */       //   118: invokevirtual 27	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */       //   121: ldc 28
/*     */       //   123: invokevirtual 27	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */       //   126: aload 4
/*     */       //   128: invokevirtual 29	java/lang/Exception:getMessage	()Ljava/lang/String;
/*     */       //   131: invokevirtual 27	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */       //   134: invokevirtual 30	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */       //   137: invokespecial 13	com/lyc/store/FileStoreException:<init>	(Ljava/lang/String;)V
/*     */       //   140: athrow
/*     */       // Line number table:
/*     */       //   Java source line #83	-> byte code offset #0
/*     */       //   Java source line #85	-> byte code offset #19
/*     */       //   Java source line #86	-> byte code offset #27
/*     */       //   Java source line #91	-> byte code offset #37
/*     */       //   Java source line #92	-> byte code offset #46
/*     */       //   Java source line #94	-> byte code offset #58
/*     */       //   Java source line #95	-> byte code offset #66
/*     */       //   Java source line #96	-> byte code offset #79
/*     */       //   Java source line #97	-> byte code offset #87
/*     */       //   Java source line #98	-> byte code offset #89
/*     */       //   Java source line #99	-> byte code offset #101
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	signature
/*     */       //   0	141	0	this	StoreFactory
/*     */       //   0	141	1	des	String
/*     */       //   0	141	2	target	String
/*     */       //   18	3	3	path	String
/*     */       //   87	40	4	e	Exception
/*     */       //   79	6	5	localObject1	Object
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   66	78	79	finally
/*     */       //   79	84	79	finally
/*     */       //   37	78	87	java/lang/Exception
/*     */       //   79	87	87	java/lang/Exception
/*     */     }
/*     */     
/*     */     public String upload(InputStream is, String target)
/*     */       throws FileStoreException
/*     */     {
/* 118 */       String path = target.substring(0, target.lastIndexOf(".")).replace("/", "");
/* 119 */       if (!fileNameIsRight(path)) {
/* 120 */         throw new FileStoreException("文件名称只能包含中文、数字、字母和下划线");
/*     */       }
/*     */       
/* 123 */       if (target.endsWith(".zip")) {
/* 124 */         checkZipFile(is);
/*     */       }
/* 126 */       synchronized (this.storeAdpter)
/*     */       {
/*     */         try {
/* 129 */           return this.storeAdpter.save(is, target);
/*     */         } catch (Exception e) {
/* 131 */           FileStore.LOG.error("upload file exception {}", e);
/* 132 */           throw new FileStoreException("上传文件失败 " + e.getMessage());
/*     */         }
/*     */       }
/*     */     }
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
/*     */     private boolean fileNameIsRight(String fileName)
/*     */     {
/* 149 */       String regex = "^[一-龥a-zA-Z0-9_]*$";
/* 150 */       return fileName.matches(regex);
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */     public void checkZipFile(InputStream inStream)
/*     */       throws FileStoreException
/*     */     {}
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
/*     */ 
/*     */ 
/*     */     public boolean down(String target, String savePath)
/*     */       throws FileStoreException
/*     */     {
/*     */       try
/*     */       {
/* 203 */         return this.storeAdpter.down(target, savePath);
/*     */       } catch (Throwable e) {
/* 205 */         FileStore.LOG.error("down file exception {}", e);
/* 206 */         throw new FileStoreException("下载文件【 " + target + "】失败" + e.getMessage());
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public InputStream down(String target)
/*     */       throws FileStoreException
/*     */     {
/* 221 */       InputStream is = null;
/*     */       try {
/* 223 */         is = this.storeAdpter.get(target);
/* 224 */       } catch (Throwable e) { e = 
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 229 */           e;FileStore.LOG.error("down fileStream exception {}", e);throw new FileStoreException("下载文件【 " + target + "】失败" + e.getMessage()); } finally {}
/* 230 */       return is;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public boolean delete(String target)
/*     */       throws FileStoreException
/*     */     {
/*     */       try
/*     */       {
/* 245 */         return this.storeAdpter.delete(target);
/*     */       } catch (Throwable e) {
/* 247 */         FileStore.LOG.error("delete file exception {}", e);
/* 248 */         throw new FileStoreException("删除文件【" + target + "】失败" + e.getMessage());
/*     */       }
/*     */     }
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
/*     */     public String uncompress(String fileName)
/*     */       throws FileStoreException
/*     */     {
/* 269 */       if (!fileName.endsWith(".zip")) {
/* 270 */         FileStore.LOG.debug("Compress file not endsWith .zip ");
/* 271 */         throw new FileStoreException("解压文件【" + fileName + "】,文件不是zip文件不能解压");
/*     */       }
/*     */       try {
/* 274 */         int BUFF = 4096;
/*     */         
/* 276 */         InputStream inStream = this.storeAdpter.get(fileName);
/*     */         
/* 278 */         int lastPosition = fileName.lastIndexOf(".");
/* 279 */         String suffix = null;String fileDir = "/";
/* 280 */         if (lastPosition > 0) {
/* 281 */           fileDir = fileName.substring(0, lastPosition);
/* 282 */           suffix = fileName.substring(lastPosition + 1);
/*     */         }
/*     */         
/* 285 */         File file = new File(System.getProperty("java.io.tmpdir") + System.currentTimeMillis() + "." + (StringUtil.isBlank(suffix) ? "zip" : suffix));
/* 286 */         OutputStream outStram = new FileOutputStream(file);
/* 287 */         IOUtils.copyBytes(inStream, outStram, BUFF, true);
/*     */         
/* 289 */         ZipFile zipFile = new ZipFile(file, "GBK");
/* 290 */         Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
/* 291 */         while (entries.hasMoreElements()) {
/* 292 */           ZipArchiveEntry zae = (ZipArchiveEntry)entries.nextElement();
/*     */           
/* 294 */           String fsSaveFilePath = fileDir + "/" + zae.getName();
/* 295 */           if (zae.isDirectory()) {
/* 296 */             this.storeAdpter.mkdirs(fsSaveFilePath);
/*     */           }
/*     */           else {
/* 299 */             BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(zae));
/*     */             
/* 301 */             upload(bis, fsSaveFilePath);
/* 302 */             if (bis != null) {
/*     */               try {
/* 304 */                 bis.close();
/*     */               } catch (Exception e) {
/* 306 */                 e.printStackTrace();
/*     */               } finally {
/* 308 */                 bis.close();
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 313 */         if (zipFile != null) {
/*     */           try {
/* 315 */             zipFile.close();
/*     */           } catch (Exception e) {
/* 317 */             e.printStackTrace();
/*     */           } finally {
/* 319 */             zipFile.close();
/*     */           }
/*     */         }
/*     */         
/* 323 */         if (file.exists()) {
/* 324 */           file.delete();
/*     */         }
/* 326 */         return fileDir;
/*     */       } catch (FileStoreException e) {
/* 328 */         FileStore.LOG.error(e.getMessage());
/* 329 */         throw new FileStoreException("解压文件【" + fileName + "】失败:" + e.getMessage());
/*     */       } catch (Exception e) {
/* 331 */         FileStore.LOG.error("uncompress zip file exception {}", e);
/* 332 */         throw new FileStoreException("解压文件【" + fileName + "】失败:" + e.getMessage());
/*     */       }
/*     */     }
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
/*     */     public boolean rename(String filePath, String newName)
/*     */       throws FileStoreException
/*     */     {
/*     */       try
/*     */       {
/* 351 */         return this.storeAdpter.rename(filePath, newName);
/*     */       } catch (Throwable e) {
/* 353 */         FileStore.LOG.error("rename file exception {}", e);
/* 354 */         throw new FileStoreException("重命名文件【" + filePath + "】到【" + newName + "】失败" + e.getMessage());
/*     */       }
/*     */     }
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
/*     */     public List<String> listFiles(String dirPath)
/*     */       throws FileStoreException
/*     */     {
/*     */       try
/*     */       {
/* 373 */         return this.storeAdpter.listFiles(dirPath);
/*     */       } catch (Throwable e) {
/* 375 */         FileStore.LOG.error("get dir path fail {}", e);
/* 376 */         throw new FileStoreException("获取目录【" + dirPath + "】下级目录失败" + e.getMessage());
/*     */       }
/*     */     }
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
/*     */     public String uncompressOnServer(String zipFileName)
/*     */       throws Exception
/*     */     {
/* 393 */       if (!zipFileName.endsWith(".zip")) {
/* 394 */         throw new FileStoreException("解压文件【" + zipFileName + "】,文件不是zip文件不能解压");
/*     */       }
/* 396 */       this.storeAdpter.unzipOnServer(zipFileName);
/* 397 */       return null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\.m2\repository\lyc\file-store\cd-dev-1.0\file-store-cd-dev-1.0.jar
 * Qualified Name:     com.lyc.store.FileStore
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.0.1
 */