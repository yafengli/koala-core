package hellopoi;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelReader {
	public static void main(String[] arg) {
		ExcelReader er = new ExcelReader();
		try {
			er.readExcel("F:/Document/fdq/anhui/1284862675030.xls");			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("a");
	}

	/**
	 * 读EXCEL文件
	 * 
	 * @param path
	 * @return
	 */
	public void readExcel(String path) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));
			// 表单
			for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
				if (null != workbook.getSheetAt(numSheets)) {
					HSSFSheet aSheet = workbook.getSheetAt(numSheets);// 获得一个sheet
					// 行
					for (int rowNumOfSheet = 1; rowNumOfSheet <= aSheet
							.getLastRowNum(); rowNumOfSheet++) {
						if (null != aSheet.getRow(rowNumOfSheet)) {
							HSSFRow aRow = aSheet.getRow(rowNumOfSheet);
							LinkedList al = new LinkedList();
							// 列
							for (int cellNumOfRow = 1; cellNumOfRow <= aRow
									.getLastCellNum(); cellNumOfRow++) {
								String strCell = "";
								Object a = aRow.getCell(cellNumOfRow);
								if (null != aRow.getCell(cellNumOfRow)) {
									HSSFCell aCell = aRow.getCell(cellNumOfRow);
									if (aCell.getCellType() == (aCell.CELL_TYPE_NUMERIC)) {
										strCell = String.valueOf(aCell
												.getNumericCellValue());
										int index = strCell.indexOf(".");
										if (index != -1) {
											strCell = strCell.substring(0,
													index);
										}
										if (HSSFDateUtil
												.isCellDateFormatted(aCell)) {
											strCell = new SimpleDateFormat(
													"yyyy-MM-dd HH:mm:ss")
													.format(aCell
															.getDateCellValue());
										}
									} else {
										strCell = aCell.getStringCellValue();
									}
									al.add(cellNumOfRow - 1, strCell);
								}
							}
							for(Iterator<String> it=al.iterator();it.hasNext();){
								String val=it.next();
								System.out.printf("%s,",val);
							}
							System.out.println();
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("-------------ReadExcelError------------------"
					+ e.getMessage());
			e.printStackTrace();
		}
	}
}