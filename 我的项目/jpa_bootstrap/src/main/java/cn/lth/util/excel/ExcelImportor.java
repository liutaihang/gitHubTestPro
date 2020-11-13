package cn.lth.util.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * excel导入工具类
 * @ClassName: ExcelImportor
 * @Description: TODO
 * @author dh
 * @date 2020年6月8日
 *
 */
public class ExcelImportor {

	
	/**文件输入流*/
	private InputStream in;
	
	/**当前excel对象*/
	private Workbook workbook;
	
	/**总共的sheet页数量*/
	private LinkedList<Sheet> sheets = new LinkedList<>();
	
	/**sheet对象*/
	private Sheet sheet;
	
	/**行*/
	private Row row;
	
	/**单元格*/
	private Cell cell;
	
	/**从第几行开始取数据*/
	private int startRow=1;
	
	/**每个sheet页的开始行*/
	private LinkedList<Integer>sheetStartRow = new LinkedList<>();
	
	/**列信息设置*/
	private LinkedList<ExcelModel>columnNameList = new LinkedList<>();
	
	/**不解析的列下标*/
	private List<Integer> exceptColumnNum = new ArrayList<Integer>();
	
	/**解析出来的数据*/
	private LinkedList<LinkedList<Map<String,Object>>> datas = new LinkedList<>();
	
	public ExcelImportor(InputStream in) {
		this.in=in;
		init();
	}
	
	private void init() {
		
			try {
				workbook = WorkbookFactory.create(in);
				int sheetNum = workbook.getNumberOfSheets();
				for(int i =0 ;  i < sheetNum ; i ++) {
					sheets.add(workbook.getSheetAt(i));
				}
			} catch (InvalidFormatException | IOException e) {
				e.printStackTrace();
			}
	}
	
	public void analysisExcel() {
		//遍历每一个sheet然后取出数据
		for(int s=0;s<workbook.getNumberOfSheets();s++) {
			sheet = sheets.get(s);
			LinkedList<Map<String,Object>>sheetDatas = new LinkedList<>();
			int lastRowNum = sheet.getLastRowNum();
			for(int i = startRow; i <=lastRowNum;i++) {
//				cell
			}
		}
	}
	
	/**
	 * 设置需要忽略的列
	 * @Title: addExceptColumnNum
	 * @Description: TODO
	 * @param columnIndex  
	 * @return void
	 */
	public void  addExceptColumnNum(int columnIndex) {
		this.exceptColumnNum.add(columnIndex);
	}
	
	/**
	 * 设置需要忽略的列
	 * @Title: addExceptColumnNums
	 * @Description: TODO
	 * @param exceptColumnNums  
	 * @return void
	 */
	public void  addExceptColumnNums(List<Integer> exceptColumnNums) {
		this.exceptColumnNum.addAll(exceptColumnNums);
	}
	
	
	public void addSheetStartRow(int startRow) {
		this.sheetStartRow.add(startRow);
	}
	
	public void addSheetStartRows(LinkedList<Integer> sheetStartRow) {
		this.sheetStartRow.addAll(sheetStartRow);
	}
	
	/**
	 * 设置开始取数据的行下标
	 * @Title: setStartRow
	 * @Description: TODO
	 * @param startRow  
	 * @return void
	 */
	public void  setStartRow(int startRow) {
		this.startRow=startRow;
	}
	/**
	 * 设置列对应的属性名称
	 * @Title: addColumnName 
	 * @Description: TODO
	 * @param columnName   列名称
	 * @return void
	 */
	public void addColumnName(ExcelModel excelModel) {
		this.columnNameList.add(excelModel);
	}
	/**
	 * 设置列对应的属性名称
	 * @Title: addColumnNames
	 * @Description: TODO
	 * @param columnNameList  
	 * @return void
	 */
	public void addColumnNames( LinkedList<ExcelModel>excelModels) {
		this.columnNameList.addAll(excelModels);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		FileInputStream input = new FileInputStream("D:\\imageai\\1.xls");
		ExcelImportor excelImportor = new ExcelImportor(input);
	}
}
