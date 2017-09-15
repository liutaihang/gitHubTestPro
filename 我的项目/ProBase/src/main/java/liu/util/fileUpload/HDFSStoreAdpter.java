package liu.util.fileUpload;
/*     */ 
/*     */ import com.lyc.store.FileStoreException;
/*     */ import com.lyc.store.util.PropertiesFileUtil;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.configuration.ConfigurationException;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.hadoop.conf.Configuration;
/*     */ import org.apache.hadoop.fs.FSDataInputStream;
/*     */ import org.apache.hadoop.fs.FileStatus;
/*     */ import org.apache.hadoop.fs.FileSystem;
/*     */ import org.apache.hadoop.fs.Path;
/*     */ import org.apache.hadoop.io.IOUtils;
/*     */ import org.apache.hadoop.io.compress.CompressionCodec;
/*     */ import org.apache.hadoop.io.compress.CompressionCodecFactory;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HDFSStoreAdpter
/*     */   extends FileStoreAdpter
/*     */ {
/*  41 */   private static Logger logger = LoggerFactory.getLogger("HDFSStoreAdpter");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  46 */   static PropertiesFileUtil pf = null;
/*     */   
/*     */ 
/*     */ 
/*     */   private static final int BUFF = 4096;
/*     */   
/*     */ 
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  58 */       pf = PropertiesFileUtil.builder("hdfs.properties");
/*     */     } catch (ConfigurationException e) {
/*  60 */       logger.error("加载hdfs配置文件出错，{}", e);
/*  61 */       throw new RuntimeException("加载hdfs配置文件失败", e);
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
/*     */ 
/*     */ 
/*     */   private Configuration getClusterConfig()
/*     */   {
/*  76 */     Configuration conf = new Configuration();
/*     */     
/*     */ 
/*  79 */     InputStream coreSiteURL = getClass().getClassLoader().getResourceAsStream("core-site.xml");
/*  80 */     if (coreSiteURL == null) {
/*  81 */       throw new RuntimeException("classpath 下未找到 core-site.xml 配置文件");
/*     */     }
/*     */     
/*  84 */     InputStream hdfsSiteURL = getClass().getClassLoader().getResourceAsStream("hdfs-site.xml");
/*  85 */     if (hdfsSiteURL == null) {
/*  86 */       throw new RuntimeException("classpath 下未找到 hdfs-site.xml 配置文件");
/*     */     }
/*  88 */     conf.addResource(coreSiteURL);
/*  89 */     conf.addResource(hdfsSiteURL);
/*     */     
/*  91 */     return conf;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private URI getClusterURI()
/*     */     throws FileStoreException
/*     */   {
/*     */     try
/*     */     {
/* 104 */       return new URI(pf.getString("fs.defaultFS"));
/*     */     } catch (URISyntaxException e) {
/* 106 */       logger.error("读取hdfs配置文件失败，{}", e);
/* 107 */       throw new RuntimeException("读取hdfs配置文件失败", e);
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
/*     */ 
/*     */   private FileSystem getFileSystem()
/*     */     throws FileStoreException
/*     */   {
/*     */     try
/*     */     {
/* 124 */       return FileSystem.get(getClusterURI(), getClusterConfig(), pf.getString("dfs.user"));
/*     */     } catch (IOException|InterruptedException e) {
/* 126 */       logger.error("读取hdfs配置文件失败，{}", e);
/* 127 */       throw new FileStoreException("读取hdfs配置文件失败" + e.getMessage());
/*     */     } catch (Exception e) {
/* 129 */       logger.error("读取hdfs配置文件失败，{}", e);
/* 130 */       throw new FileStoreException("读取hdfs配置文件失败" + e.getMessage());
/*     */     }
/*     */   }
/*     */   
/*     */   public String save(String des, String target) throws FileStoreException
/*     */   {
/* 136 */     InputStream is = null;
/*     */     try {
/* 138 */       is = new FileInputStream(des);
/*     */     } catch (FileNotFoundException e) {
/* 140 */       logger.error("上传文件【" + des + "】失败，{}", e);
/* 141 */       throw new FileStoreException("上传文件【" + des + "】失败" + e.getMessage());
/*     */     } catch (Exception e) {
/* 143 */       logger.error("上传文件【" + des + "】失败，{}", e);
/* 144 */       throw new FileStoreException("上传文件【" + des + "】失败" + e.getMessage());
/*     */     }
/* 146 */     return save(is, target);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String save(InputStream is, String target)
/*     */     throws FileStoreException
/*     */   {
/* 154 */     FileSystem fs = getFileSystem();
/* 155 */     if (StringUtils.isBlank(target))
/*     */     {
/* 157 */       target = File.separatorChar + "";
/*     */     }
/*     */     
/* 160 */     if (!target.startsWith("/")) {
/* 161 */       target = "/" + target;
/*     */     }
/*     */     
/* 164 */     OutputStream out = null;
/*     */     try {
/* 166 */       out = fs.create(new Path(target));
/* 167 */       IOUtils.copyBytes(is, out, 4096, true);
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
/* 183 */       return target;
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 169 */       logger.error("上传文件失败，{}", e);
/* 170 */       throw new FileStoreException("上传文件失败" + e.getMessage());
/*     */     } catch (Exception e) {
/* 172 */       logger.error("上传文件失败，{}", e);
/* 173 */       throw new FileStoreException("上传文件失败" + e.getMessage());
/*     */     } finally {
/*     */       try {
/* 176 */         fs.close();
/*     */       } catch (IOException e) {
/* 178 */         logger.error("关闭文件流失败，{}", e);
/* 179 */         throw new FileStoreException("关闭文件流失败" + e.getMessage());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean delete(String target)
/*     */     throws FileStoreException
/*     */   {
/* 191 */     if (StringUtils.isBlank(target)) {
/* 192 */       return false;
/*     */     }
/*     */     
/* 195 */     if (!target.startsWith("/")) {
/* 196 */       target = "/" + target;
/*     */     }
/*     */     
/* 199 */     FileSystem fs = null;
/*     */     try {
/* 201 */       fs = FileSystem.get(getClusterURI(), getClusterConfig(), pf.getString("dfs.user"));
/* 202 */       return fs.delete(new Path(target), true);
/*     */     } catch (IOException|InterruptedException e) {
/* 204 */       logger.error("删除文件【" + target + "】失败，{}", e);
/* 205 */       throw new FileStoreException("删除文件【" + target + "】失败" + e.getMessage());
/*     */     } catch (Exception e) {
/* 207 */       logger.error("删除文件【" + target + "】失败，{}", e);
/* 208 */       throw new FileStoreException("删除文件【" + target + "】失败" + e.getMessage());
/*     */     } finally {
/*     */       try {
/* 211 */         fs.close();
/*     */       } catch (IOException e) {
/* 213 */         logger.error("关闭文件流失败，{}", e);
/* 214 */         throw new FileStoreException("关闭文件流失败" + e.getMessage());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public InputStream get(String target)
/*     */     throws FileStoreException
/*     */   {
/* 225 */     if (StringUtils.isBlank(target)) {
/* 226 */       return null;
/*     */     }
/* 228 */     FileSystem fs = getFileSystem();
/* 229 */     FSDataInputStream fis = null;
/*     */     try {
/* 231 */       fis = fs.open(new Path(target));
/*     */     } catch (IllegalArgumentException|IOException e) {
/* 233 */       logger.error("下载文件【" + target + "】失败，{}", e);
/* 234 */       throw new FileStoreException("下载文件【" + target + "】失败" + e.getMessage());
/*     */     } catch (Exception e) {
/* 236 */       logger.error("下载文件【" + target + "】失败，{}", e);
/* 237 */       throw new FileStoreException("下载文件【" + target + "】失败" + e.getMessage());
/*     */     }
/* 239 */     return fis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean down(String target, String savePath)
/*     */     throws FileStoreException
/*     */   {
/* 247 */     InputStream is = get(target);
/*     */     
/* 249 */     if (!target.startsWith("/")) {
/* 250 */       target = "/" + target;
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 255 */       OutputStream os = new FileOutputStream(savePath);
/* 256 */       IOUtils.copyBytes(is, os, 4096, true);
/* 257 */       return true;
/*     */     } catch (FileNotFoundException e) {
/* 259 */       logger.error("下载文件【" + target + "】失败，{}", e);
/* 260 */       throw new FileStoreException("下载文件【" + target + "】失败" + e.getMessage());
/*     */     } catch (IOException e) {
/* 262 */       logger.error("下载文件【" + target + "】失败，{}", e);
/* 263 */       throw new FileStoreException("下载文件【" + target + "】失败" + e.getMessage());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean mkdirs(String dirPath)
/*     */     throws FileStoreException
/*     */   {
/* 272 */     if (StringUtils.isBlank(dirPath)) {
/* 273 */       return false;
/*     */     }
/* 275 */     FileSystem fs = getFileSystem();
/*     */     try {
/* 277 */       fs.mkdirs(new Path(dirPath));
/* 278 */       return true;
/*     */     } catch (IllegalArgumentException|IOException e) {
/* 280 */       logger.error("创建目录失败，{}", e);
/* 281 */       throw new FileStoreException("创建目录失败" + e.getMessage());
/*     */     } finally {
/*     */       try {
/* 284 */         fs.close();
/*     */       } catch (IOException e) {
/* 286 */         logger.error("关闭文件流失败，{}", e);
/* 287 */         throw new FileStoreException("关闭文件流失败" + e.getMessage());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean rename(String filePath, String newFileName)
/*     */     throws FileStoreException
/*     */   {
/* 300 */     if (StringUtils.isBlank(filePath)) {
/* 301 */       return false;
/*     */     }
/*     */     
/* 304 */     if (!filePath.startsWith("/")) {
/* 305 */       filePath = "/" + filePath;
/*     */     }
/* 307 */     FileSystem fs = getFileSystem();
/* 308 */     Path srcPath = new Path(filePath);
/* 309 */     Path targetPath = new Path(srcPath.getParent(), newFileName);
/*     */     try
/*     */     {
/* 312 */       return fs.rename(srcPath, targetPath);
/*     */     } catch (IOException e) {
/* 314 */       logger.error("文件重命名失败，{}", e);
/* 315 */       throw new FileStoreException("文件重命名失败" + e.getMessage());
/*     */     } finally {
/*     */       try {
/* 318 */         fs.close();
/*     */       } catch (IOException e) {
/* 320 */         logger.error("关闭文件流失败，{}", e);
/* 321 */         throw new FileStoreException("关闭文件流失败" + e.getMessage());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<String> listFiles(String dirPath)
/*     */     throws FileStoreException
/*     */   {
/* 334 */     if (StringUtils.isBlank(dirPath)) {
/* 335 */       return null;
/*     */     }
/* 337 */     List<String> paths = new ArrayList(5);
/*     */     try
/*     */     {
/* 340 */       FileSystem fs = getFileSystem();
/*     */       
/* 342 */       FileStatus[] fileStatus = fs.listStatus(new Path(dirPath));
/* 343 */       if (ArrayUtils.isEmpty(fileStatus)) {
/* 344 */         return null;
/*     */       }
/* 346 */       for (FileStatus fStatus : fileStatus) {
/* 347 */         paths.add(fStatus.getPath().toUri().getPath());
/*     */       }
/*     */     }
/*     */     catch (IOException e) {
/* 351 */       logger.error("列出文件目录失败，{}", e);
/* 352 */       throw new FileStoreException("列出文件目录失败" + e.getMessage());
/*     */     } catch (Exception e) {
/* 354 */       logger.error("列出文件目录失败，{}", e);
/* 355 */       throw new FileStoreException("列出文件目录失败" + e.getMessage());
/*     */     }
/* 357 */     return paths;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void unzipOnServer(String zipFileName)
/*     */     throws FileStoreException
/*     */   {
/* 365 */     InputStream in = null;
/* 366 */     OutputStream out = null;
/*     */     try {
/* 368 */       FileSystem fs = getFileSystem();
/*     */       
/* 370 */       Path inputPath = new Path(zipFileName);
/* 371 */       CompressionCodecFactory factory = new CompressionCodecFactory(fs.getConf());
/* 372 */       CompressionCodec codec = factory.getCodec(inputPath);
/* 373 */       if (codec == null) {
/* 374 */         logger.error("no codec found for " + zipFileName);
/* 375 */         System.exit(1);
/*     */       }
/* 377 */       String outputUri = CompressionCodecFactory.removeSuffix(zipFileName, codec.getDefaultExtension());
/*     */       
/* 379 */       in = codec.createInputStream(fs.open(inputPath));
/* 380 */       out = fs.create(new Path(outputUri));
/* 381 */       IOUtils.copyBytes(in, out, fs.getConf());
/*     */     } catch (FileStoreException e) {
/* 383 */       logger.error("解压文件失败，{}", e);
/* 384 */       throw e;
/*     */     } catch (Exception e) {
/* 386 */       logger.error("解压文件失败，{}", e);
/* 387 */       throw new FileStoreException("解压文件失败" + e.getMessage());
/*     */     } finally {
/* 389 */       IOUtils.closeStream(out);
/* 390 */       IOUtils.closeStream(in);
/*     */     }
/*     */   }
/*     */   
/*     */   public HDFSStoreAdpter() {}
/*     */ }

/* Location:           C:\Users\Administrator\.m2\repository\lyc\file-store\cd-dev-1.0\file-store-cd-dev-1.0.jar
 * Qualified Name:     com.lyc.store.adpter.HDFSStoreAdpter
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.0.1
 */