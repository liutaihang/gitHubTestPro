package com.java1234.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.java1234.entity.business.BindCardInfo;
import com.java1234.entity.business.BindCardLogInfo;
import com.java1234.entity.business.BusinessInfo;
import com.java1234.entity.business.UserInfo;
import com.java1234.entity.business.UserTransInfo;

public class ExcelUtils {

	/**
	 * 通用生成excel方法,必须和MapUtil里面的配合使用
	 * @author PJJ 11/15
	 * @param obj
	 * @return list of className.fieldName
	 */
	public static List<String> getField(Object obj) {
		List<String> list = new ArrayList<String>();
		Field[] fields = obj.getClass().getDeclaredFields();
		String className = obj.getClass().getSimpleName();
		for (Field field : fields) {
			list.add(className + "." + field.getName());
		}
		return list;
	}

	@SuppressWarnings("deprecation")
	public static <T> void export(List<T> list, String filePath) {
		// 获取泛型的具体类型
		String className = list.get(0).getClass().getSimpleName();
		if (filePath == "" || filePath == null) {
			filePath = "D:\\" + className + "-" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xls";
		}
		List<String> fields = new ArrayList<String>();
		Field[] ld = null;
		// 获取具体的字段，通过字段获取表头的描述
		if ("UserInfo".equals(className)) {
			fields = getField(new UserInfo());
			ld = UserInfo.class.getDeclaredFields();
		} else if ("BindCardInfo".equals(className)) {
			fields = getField(new BindCardInfo());
			ld = BindCardInfo.class.getDeclaredFields();
		} else if ("BindCardLogInfo".equals(className)) {
			fields = getField(new BindCardLogInfo());
			ld = BindCardLogInfo.class.getDeclaredFields();
		} else if ("UserTransInfo".equals(className)) {
			fields = getField(new UserTransInfo());
			ld = UserTransInfo.class.getDeclaredFields();
		} else if ("BusinessInfo".equals(className)) {
			fields = getField(new BusinessInfo());
			ld = BusinessInfo.class.getDeclaredFields();
		} 
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名
		HSSFSheet sheet = wb.createSheet(MapUtil.getDesc(className));
		// 给单子名称一个长度
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		// 创建第一行（也可以称为表头）
		HSSFRow row = sheet.createRow(0);
		// 样式字体居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 根据有的字段依次创建单元格头部描述
		HSSFCell cell = row.createCell((short) 0);
		for (int i = 0; i < fields.size(); i++) {
			//去除用户交易应答编码这一列
			if ("UserTransInfo.respCode".equals(fields.get(i))) continue;
			if ("UserTransInfo.payType".equals(fields.get(i))) continue;
			if ("UserTransInfo.acceptDate".equals(fields.get(i))) continue;
			if ("UserTransInfo.id".equals(fields.get(i))) continue;
			//去除id
			if("UserInfo.id".equals(fields.get(i))) continue;
//			if("UserInfo.phoneNo".equals(fields.get(i))) continue;
			if("BindCardInfo.id".equals(fields.get(i))) continue;
			if("BindCardLogInfo.id".equals(fields.get(i))) continue;
			if("BusinessInfo.id".equals(fields.get(i))) continue;
			cell.setCellValue(MapUtil.getDesc(fields.get(i)));
			cell.setCellStyle(style);
			cell = row.createCell((short) i + 1);
		}

		// 向单元格里填充数据
		for (short i = 0; i < list.size(); i++) {// 每行
			row = sheet.createRow(i + 1);
			// 通过反射获取每个对象每列的值
			for (int j = 0; j < ld.length; j++) {
				ld[j].setAccessible(true);
				//去除用户交易应答编码这一列的value值,去除所有类的id值
				if ("UserTransInfo.respCode".equals((className + "." + ld[j].getName()))) continue;
				if ("UserTransInfo.payType".equals((className + "." + ld[j].getName()))) continue;
				if ("UserTransInfo.acceptDate".equals((className + "." + ld[j].getName()))) continue;
				if ("UserTransInfo.id".equals((className + "." + ld[j].getName()))) continue;
				//去除id
				if("UserInfo.id".equals((className + "." + ld[j].getName()))) continue;
//				if("UserInfo.phoneNo".equals((className + "." + ld[j].getName()))) continue;
				if("BindCardInfo.id".equals((className + "." + ld[j].getName()))) continue;
				if("BindCardLogInfo.id".equals((className + "." + ld[j].getName()))) continue;
				if("BusinessInfo.id".equals((className + "." + ld[j].getName()))) continue;
				try {
					if (ld[j].getType().getName()
							.equals(java.lang.String.class.getName())) {
						row.createCell(j).setCellValue(
								(String) ld[j].get(list.get(i)));
					} else if (ld[j].getType().getName()
							.equals(java.lang.Integer.class.getName())
							|| ld[j].getType().getName().equals("int")) {
						row.createCell(j).setCellValue(ld[j].getInt(list.get(i)));
					}
				} catch (Exception e) {
					continue;
				}
			}
		}
		
       try {
    	   File file = new File(filePath);
    	   File parentFile = file.getParentFile();
    	   if (!file.exists()) {
    		   parentFile.mkdirs();
		   }
		   file.createNewFile();
            //数据导入c盘桌面上
            FileOutputStream out = new FileOutputStream(filePath);
            wb.write(out);
            out.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
