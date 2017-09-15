package liu.util.fileUpload;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.commons.io.FileUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FileUtil
/*    */ {
/*    */   public FileUtil() {}
/*    */   
/*    */   public static File readFile(String path)
/*    */   {
/* 27 */     return FileUtils.getFile(new String[] { path });
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static List<String[]> parseFileByComma(String path, String comma)
/*    */     throws IOException
/*    */   {
/* 40 */     return filePaser(path, comma);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static List<String[]> parseFileByLine(String path, String line)
/*    */     throws IOException
/*    */   {
/* 53 */     return filePaser(path, line);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static List<String[]> parseFileByDoubleLine(String path, String doubleLine)
/*    */     throws IOException
/*    */   {
/* 66 */     return filePaser(path, doubleLine);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static List<String[]> filePaser(String path, String symbol)
/*    */     throws IOException
/*    */   {
/* 79 */     File file = readFile(path);
/* 80 */     BufferedReader reader = new BufferedReader(new FileReader(file));
/* 81 */     List<String[]> data = new ArrayList();
/* 82 */     String str = null;
/* 83 */     while ((str = reader.readLine()) != null) {
/* 84 */       data.add(str.split(symbol));
/*    */     }
/* 86 */     reader.close();
/* 87 */     return data;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\.m2\repository\lyc\file-store\cd-dev-1.0\file-store-cd-dev-1.0.jar
 * Qualified Name:     com.lyc.store.util.FileUtil
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.0.1
 */