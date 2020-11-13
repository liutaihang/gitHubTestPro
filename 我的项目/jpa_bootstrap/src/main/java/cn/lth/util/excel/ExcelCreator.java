package cn.lth.util.excel;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * 生成Excel工具类
 * 
 * @ClassName: ExcelCreator
 * @Description: TODO
 * @author dh
 * @date 2020年6月8日
 *
 */
@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
public class ExcelCreator {

	public static final String XLSX = "xlsx";
	public static final String XLX = "xls";
	/** 当前Excel类型 */
	private String currentExcelType;
	/** Excel对象 */
	private Workbook book;

	/** 当前sheet对象 */
	private Sheet sheet;

	private Row row;

	private Cell cell;

	private Integer rowHeight;

	/** 图片工具 */
	private Drawing drawing;
	/** 是否需要增加序号 */
	private boolean needOrderNum = false;

	/** 需要插入的数据 */
	private Object object;

	/** 表头信息 */
	private String[] header;

	/** 标题 */
	private String title;

	/** sheet页名称默认是sheet0 */
	private String sheetName;

	/** 插入字段和下标的映射 */
	private Map<Integer, ExcelModel> columnMappingInfo = new HashMap<>();

	/** 需要合并单元格的信息,要合并的列下标以及对哪个字段进行合并 */
	private Map<Integer, String> columnMergeInfo = new HashMap<>();

	/** 如果有多个sheet页那么添加子类对象即可 */
	private LinkedList<ExcelCreator> child = new LinkedList<>();

	/** 标题样式 */
	private CellStyle titleCellStyle;

	/** 表头样式 */
	private CellStyle headerCellStyle;

	/** 数据单元格样式 */
	private CellStyle cellStyle;

	private static final int DEFAULT_ROW_HEIGHT = 1000;

	private static final int DEFAULT_COLUMN_WIDTH = 20;

	private boolean isSettingColumnWidth = false;

	public ExcelCreator(String sheetName) {
		this.sheetName = sheetName;
	}

	public ExcelCreator(String excelType, String sheetName) {
		this.sheetName = sheetName;
		init(excelType);
	}

	public void setColumnWidth(int columnIndex, int width) {
		isSettingColumnWidth = true;
		sheet.setColumnWidth(columnIndex, width);
	}

	/**
	 * 设置行高，如果不设置默认为DEFAULT_ROW_HEIGHT
	 * @Title: setRowHeight
	 * @Description: TODO
	 * @param rowHeight  
	 * @return void
	 */
	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	public ExcelCreator(Workbook book) {
		this.book = book;
	}
	public ExcelCreator(Workbook book, Drawing drawing, String type) {
		this.book = book;
		this.drawing = drawing;
		this.currentExcelType = type;
	}

	private void init(String excelType) {
		// 检查excel类型
		checkExcelType(excelType);
		// 创建工作簿对象
		createWorkBook(excelType);
	}

	/**
	 * 创建excel
	 */
	public void createExcel() {

		// 添加表头以及标题对象
		settingHeaderAndTitle();

		// 添加数据到单元格
		settingData();

		// 合并单元格操作
		mergeCell();

		// 操作子sheet页
		generateChildSheet();

	}

	/**
	 * 处理多个sheet
	 * 
	 * @Title: generateChildSheet
	 * @Description: TODO
	 * @return void
	 */
	private void generateChildSheet() {
		if (child != null && child.size() > 0) {
			for (ExcelCreator excelCreator : child) {
				excelCreator.book = this.book;
				if (sheetName != null && sheetName.trim().length() > 0)
					excelCreator.sheet = excelCreator.book.createSheet(sheetName);
				else
					excelCreator.sheet = excelCreator.book.createSheet();
				drawing = sheet.createDrawingPatriarch();
				excelCreator.currentExcelType = this.currentExcelType;
				// 默认样式设置
				excelCreator.defaultCellStyle();
				excelCreator.createExcel();
			}
		}
	}

	/**
	 * 合并单元格操作
	 * 
	 * @Title: mergeCell
	 * @Description: TODO
	 * @return void
	 */
	private void mergeCell() {

		for (Map.Entry<Integer, String> entry : columnMergeInfo.entrySet()) {
			int indexColumn = entry.getKey();
			if (needOrderNum)
				indexColumn += 1;
			String field = entry.getValue();
			List dataList = changeList();
			int rowNum = 0;

			int index = 0;
			if (title != null && title.trim().length() != 0) {
				index += 1;
				rowNum += 1;
			}
			if (header != null && header.length != 0) {
				index += 1;
				rowNum += 1;
			}
			Object currentValue = "";
			for (int i = 0; i < dataList.size(); i++) {
				Object data = dataList.get(i);
				Object value = getValue(field, data);
				if (currentValue.equals("")) {
					currentValue = value;
				} else if (!currentValue.equals(value) || (currentValue.equals(value) && i == dataList.size() - 1)) {
					sheet.addMergedRegion(new CellRangeAddress(index, i - 1 + rowNum, indexColumn, indexColumn));

					currentValue = value;
					index = i + rowNum;
				}
			}
		}
	}

	/**
	 * 通过反射获取对象中对应属性的值
	 * 
	 * @Title: getValue
	 * @Description: TODO
	 * @param field
	 * @param data
	 * @return
	 * @return Object
	 */
	private Object getValue(String field, Object data) {
		Object value = "";
		if (data instanceof Map) {
			Map map = (Map) data;
			Set keygen = map.keySet();
			Iterator it = keygen.iterator();
			while (it.hasNext()) {
				Object key = it.next();
				if (field == key || key.equals(field)) {
					value = map.get(key);
					break;
				}
			}
		} else {
			Character first = new Character(field.charAt(0));
			String getMethod = "get" + String.valueOf(first).toUpperCase() + field.substring(1);
			try {
				Method method = data.getClass().getDeclaredMethod(getMethod);
				value = method.invoke(data);
			} catch (NoSuchMethodException e) {
				throw new RuntimeException(field + " 没有对应的get方法");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 * 将单个对象添加为list列表
	 * 
	 * @Title: changeList
	 * @Description: TODO
	 * @return
	 * @return List
	 */
	private List changeList() {
		List<Map<?, ?>> data = new LinkedList<Map<?, ?>>();
		if (object instanceof Map) {
			Map map = (Map) object;
			data.add(map);
		} else if (object instanceof List) {
			List list = (List) object;
			data.addAll(list);
		}
		return data;
	}

	/**
	 * 添加数据进入单元格
	 */
	private void settingData() {
		List data = changeList();
		int length = header.length;
		if (needOrderNum)
			length += 1;
		for (int i = 0; i < data.size(); i++) {
			Object obj = data.get(i);
			row = sheet.createRow(2 + i);
			if (rowHeight != null) {
				row.setHeight(rowHeight.shortValue());
			} else {
				row.setHeight((short) DEFAULT_ROW_HEIGHT);
			}
			for (int j = 0; j < length; j++) {
				cell = row.createCell(j);
				cell.setCellStyle(cellStyle);
				if (needOrderNum && j == 0) {
					cell.setCellValue(i + 1);
				} else {
					ExcelModel excelModel;
					if (needOrderNum) {
						excelModel = columnMappingInfo.get(j - 1);
					} else {
						excelModel = columnMappingInfo.get(j);
					}

					String field = excelModel.getFieldName();
					boolean needtranslate = excelModel.isNeedtranslate();
					boolean picture = excelModel.isPicture();
					Map<Object, Object> translate = excelModel.getTranslateMappingInfo();
					if (!needtranslate && !picture) {
						setCellValue(cell, field, obj, null, true);
					} else if (needtranslate) {
						setCellValue(cell, field, obj, translate, true);
					} else if (picture) {
						Object value = setCellValue(cell, field, obj, null, false);
						setPicture(j, i + 2, j, i + 2, (String) value);
					}
				}
			}

		}
	}

	/**
	 * 向单元格中设置图片对象
	 * 
	 * @Title: setPicture
	 * @Description: TODO
	 * @param startColunm 开始列
	 * @param startRow    开始行
	 * @param endColunm
	 * @param endColumn
	 * @param imageUrl
	 * @return void
	 */
	public void setPicture(int startColunm, int startRow, int endColunm, int endColumn, String imageUrl) {
		if (imageUrl == null || imageUrl.trim().length() == 0) {
			return;
		}
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		BufferedImage bufferImg = null;

		try {
			if (imageUrl.startsWith("http")) {
				bufferImg = ImageIO.read(new URL(imageUrl));
			} else {
				bufferImg = ImageIO.read(new File(imageUrl));
			}

			ImageIO.write(bufferImg, "jpg", byteArrayOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClientAnchor anchor = null;
		if (currentExcelType.equals(XLSX)) {
			anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) startColunm, startRow, (short) endColunm + 1,
					endColumn + 1);
		} else {
			anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) startColunm, startRow, (short) (endColunm + 1),
					endColumn + 1);
		}
		anchor.setAnchorType(3);
		drawing.createPicture(anchor, book.addPicture(byteArrayOut.toByteArray(), Workbook.PICTURE_TYPE_JPEG));
	}

	/**
	 *
	 * @param cell
	 * @param field
	 * @param data
	 */
	private Object setCellValue(Cell cell, String field, Object data, Map<Object, Object> translate,
			boolean needSetValue) {
		Object value = getValue(field, data);
		if (value == null)
			return "";
		if (translate != null && translate.size() > 0) {
			Set keygen = translate.keySet();
			Iterator it = keygen.iterator();
			while (it.hasNext()) {
				Object key = it.next();
				if (value == key || key.toString().equals(value.toString())) {
					value = translate.get(key);
					break;
				}
			}
		}
		if (needSetValue) {
			changeType(cell, value);
		}
		return value;
	}

	public void changeType(Cell cell, Object value) {
		if (value == null) {
			cell.setCellValue("");
			return;
		}
		cell.setCellValue(value.toString());

	}

	// 添加表头以及标题信息
	private void settingHeaderAndTitle() {
		// 添加标题信息
		if (title != null && title.trim().length() > 0) {
			row = sheet.createRow(0);
			row.setHeight((short) DEFAULT_ROW_HEIGHT);
			cell = row.createCell(0);
			cell.setCellStyle(titleCellStyle);
			cell.setCellValue(title);
			if (header != null && header.length > 0) {
				int headerLength = header.length;
				if (!needOrderNum) {
					headerLength -= 1;
				}
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headerLength));
			} else if (columnMappingInfo != null && columnMappingInfo.size() > 0) {
				int size = columnMappingInfo.size();
				if (!needOrderNum) {
					size -= 1;
				}
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, size));
			}
		}
		// 添加表头
		if (header != null && header.length > 0) {
			row = sheet.createRow(1);
			row.setHeight((short) DEFAULT_ROW_HEIGHT);
			int length = header.length;
			if (needOrderNum) {
				length += 1;
			}
			for (int i = 0; i < length; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(cellStyle);
				if (!isSettingColumnWidth)

					sheet.setColumnWidth(i, DEFAULT_COLUMN_WIDTH * 255);
				if (needOrderNum && i == 0) {
					cell.setCellValue("序号");
				} else if (needOrderNum && i != 0) {
					cell.setCellValue(header[i - 1]);
				} else {
					cell.setCellValue(header[i]);
				}
			}
		}

	}

	/**
	 * 返回工作簿对象
	 * 
	 * @return
	 */
	public Workbook getWorkBook() {
		return book;
	}

	/**
	 * 通过response对象导出
	 */
	public void exportByWResponse(HttpServletResponse response, String exportFileName) {
		try {
			System.out.println("下载成功");
			response.setContentType("application/octet-stream");
			if (!exportFileName.endsWith(currentExcelType)) {
				exportFileName += "." + currentExcelType;
			}
			String outFileName = URLEncoder.encode(exportFileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment;fileName=" + outFileName);
			assert book != null;
			book.write(response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出excel文件到本地
	 */
	public void exportLocal(String filepath) {
		try {
			if (!filepath.endsWith(currentExcelType)) {
				filepath += "." + currentExcelType;
			}
			book.write(new FileOutputStream(filepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void checkExcelType(String excelType) {
		if (excelType == null || excelType.trim().length() == 0)
			throw new IllegalArgumentException("excel类型不能为空");

		if (!XLSX.equals(excelType) && !XLX.equals(excelType))
			throw new IllegalArgumentException("excel类型只能为xlsx、xls");
	}

	private void createWorkBook(String excelType) {
		if (XLSX.equals(excelType))
			book = getXSSFWorkBook();
		else
			book = getHSSFWorkBook();
		if (sheetName != null && sheetName.trim().length() > 0)
			sheet = book.createSheet(sheetName);
		else
			sheet = book.createSheet();

		drawing = sheet.createDrawingPatriarch();
		currentExcelType = excelType;
		// 默认样式设置
		defaultCellStyle();
	}

	/**
	 * 设置默认样式
	 */
	private void defaultCellStyle() {
		// 默认的值单元格的样式
		cellStyle = book.createCellStyle();
		Font font = book.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 12);
		cellStyle.setFont(font);
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle.setWrapText(true);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderRight((short) 1);
		// 表头信息样式
		headerCellStyle = book.createCellStyle();
		headerCellStyle.cloneStyleFrom(cellStyle);
		font = book.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 14);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerCellStyle.setFont(font);
		// 标题样式
		titleCellStyle = book.createCellStyle();
		titleCellStyle.cloneStyleFrom(cellStyle);
		font = book.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 16);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		titleCellStyle.setFont(font);
	}

	public boolean isNeedOrderNum() {
		return needOrderNum;
	}

	/**
	 * 单元格中是否需要设置序号这一列，默认不设置，如果设置那么第一列就是序号列
	 * 
	 * @Title: setNeedOrderNum
	 * @Description: TODO
	 * @param needOrderNum
	 * @return void
	 */
	public void setNeedOrderNum(boolean needOrderNum) {
		this.needOrderNum = needOrderNum;
	}

	public Object getObject() {
		return object;
	}

	/**
	 * 设置需要插入的数据
	 * 
	 * @Title: setObject
	 * @Description: TODO
	 * @param object
	 * @return void
	 */
	public void setObject(Object object) {
		this.object = object;
	}

	public String[] getHeader() {
		return header;
	}

	/**
	 * 设置表格表头，单元格第二行
	 * 
	 * @Title: setHeader
	 * @Description: TODO
	 * @param header
	 * @return void
	 */
	public void setHeader(String[] header) {
		this.header = header;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题名称，单元格第一行
	 * 
	 * @Title: setTitle
	 * @Description: TODO
	 * @param title
	 * @return void
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Map<Integer, ExcelModel> getColumnMappingInfo() {
		return columnMappingInfo;
	}

	/**
	 * 设置列与对象字段的映射关系
	 * 
	 * @Title: setColumnMappingInfo
	 * @Description: TODO
	 * @param columnMappingInfo
	 * @return void
	 */
	public void setColumnMappingInfo(Map<Integer, ExcelModel> columnMappingInfo) {
		this.columnMappingInfo = columnMappingInfo;
	}

	public Map<Integer, String> getColumnMergeInfo() {
		return columnMergeInfo;
	}

	/**
	 * 设置列合并信息
	 * 
	 * @Title: setColumnMergeInfo
	 * @Description: TODO
	 * @param columnMergeInfo
	 * @return void
	 */
	public void setColumnMergeInfo(Map<Integer, String> columnMergeInfo) {
		this.columnMergeInfo = columnMergeInfo;
	}

	public LinkedList<ExcelCreator> getChild() {
		return child;
	}

	/**
	 * 如果有多个shee页则添加子ExcelCreator对象即可
	 * 
	 * @Title: setChild
	 * @Description: TODO
	 * @param child
	 * @return void
	 */
	public void setChild(LinkedList<ExcelCreator> child) {
		this.child = child;
	}

	private HSSFWorkbook getHSSFWorkBook() {
		return new HSSFWorkbook();
	}

	private XSSFWorkbook getXSSFWorkBook() {
		return new XSSFWorkbook();
	}

	/**
	 * 生成Excel字段属性信息
	 * 
	 * @Title: generate
	 * @Description: TODO
	 * @param fieldName            字段名称
	 * @param needtranslate        是否需要翻译成自定义的值
	 * @param translateMappingInfo 自定义翻译信息
	 * @param isPicture            此列是否是图片列
	 * @return
	 * @return ExcelModel
	 */
	public static ExcelModel generate(String fieldName, boolean needtranslate, Map<Object, Object> translateMappingInfo,
			boolean isPicture) {
		return new ExcelModel(fieldName, needtranslate, translateMappingInfo, isPicture);
	}

	public static void main(String[] args) {

		ExcelCreator excelCreator = new ExcelCreator(ExcelCreator.XLSX, "test");
		String[] header = { "测试字段1", "测试字段2", "测试字段3", "测试字段4", "测试字段5" };
		Map<Integer, ExcelModel> mapp = new HashMap<>();
		mapp.put(0, ExcelCreator.generate("cs1", false, null, false));
		mapp.put(1, ExcelCreator.generate("cs2", false, null, false));
		mapp.put(2, ExcelCreator.generate("cs3", false, null, false));
		Map<Object, Object> translateMappingInfo = new HashMap<>();
		translateMappingInfo.put("1", "成功");
		translateMappingInfo.put("2", "失败");
		mapp.put(3, ExcelCreator.generate("cs4", true, translateMappingInfo, false));
		mapp.put(4, ExcelCreator.generate("cs5", false, translateMappingInfo, true));
		excelCreator.setColumnMappingInfo(mapp);
		excelCreator.setHeader(header);
		excelCreator.setTitle("测试");
		List list = new LinkedList();
		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			Map<String, Object> data = new HashMap<>();
			for (int j = 0; j < 5; j++) {
				if (j == 3) {
					data.put("cs" + (j + 1), random.nextInt(2) + 1);
				} else if (j == 4) {
					data.put("cs" + (j + 1), "D://test//52639c5fb6484e15b6d010c8fbd3fb591588695951468_image_.jpg");
				} else {
					data.put("cs" + (j + 1), "ssssss");
				}

			}
			list.add(data);
		}
		excelCreator.setObject(list);
		excelCreator.setSheetName("测试");
		Map<Integer, String> map = new HashMap<>();
		map.put(0, "cs1");
		excelCreator.setColumnMergeInfo(map);
		excelCreator.createExcel();
		excelCreator.exportLocal("D://ttt");
	}
}
