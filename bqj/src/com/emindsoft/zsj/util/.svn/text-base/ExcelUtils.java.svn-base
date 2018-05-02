package com.emindsoft.zsj.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import com.jfinal.plugin.activerecord.Model;

public class ExcelUtils {

	public static void generateExcel(OutputStream out,
			LinkedHashMap<String, String> titles, List list) throws Exception {
		// 创建EXCEL
		WritableWorkbook book = Workbook.createWorkbook(out);
		WritableSheet sheet = book.createSheet("sheet1", 0);

		// 表头输出
		int column = 0;
		for (String title : titles.keySet()) {
			sheet.addCell(new Label(column++, 0, titles.get(title)));
		}

		NumberFormat nf = new jxl.write.NumberFormat("#,##0.00");
		WritableCellFormat wcfN = new WritableCellFormat(nf);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 输出EXCEL的内容
		for (int i = 0; i < list.size(); i++) {
			Model map = (Model) list.get(i);
			int rowColumn = 0;
			for (String title : titles.keySet()) {
				Object value = map.get(title);

				if (value == null) {
					sheet.addCell(new Label(rowColumn++, i + 1, ""));
					continue;
				}

				boolean isNumber = value instanceof Double
						|| value instanceof Float || value instanceof Integer
						|| value instanceof Short;
				boolean isString = value instanceof String;
				boolean isDate = value instanceof Date;

				if (isNumber) {
					double doubleValue = Double.parseDouble(value.toString());
					sheet.addCell(new Number(rowColumn++, i + 1, doubleValue,
							wcfN));
				} else if (isDate) {
					String dateStr = sdf.format(value);
					sheet.addCell(new Label(rowColumn++, i + 1, dateStr));
				} else {
					sheet.addCell(new Label(rowColumn++, i + 1, value
							.toString()));
				}
			}
		}

		book.write();
		book.close();
	}

	/**
	 * excel导入
	 * 
	 * @param is
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String[][] ReadExcel(InputStream is) {
		String[][] dataRows = null;
		try {
			// 读取excel文件内容
			HSSFWorkbook wookbook = new HSSFWorkbook(is);
			HSSFSheet sheet = wookbook.getSheetAt(0);

			// 获取行数
			int rows = sheet.getPhysicalNumberOfRows();

			if (rows <= 1) {
				return null;
			}

			// 构造数据对象
			int cells = sheet.getRow(0).getPhysicalNumberOfCells();
			dataRows = new String[rows - 1][cells];
			// 遍历行
			for (int i = 1; i < rows; i++) {
				HSSFRow row = sheet.getRow(i);
				// 遍历列
				if (row != null && !"".equals(row)) {
					for (int j = 0; j < cells; j++) {
						HSSFCell cell = row.getCell(j);
						if (cell == null) {
							dataRows[i - 1][j] = "";
						} else {
							try {
								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_STRING:
									dataRows[i - 1][j] = cell
											.getStringCellValue();
									break;
								case Cell.CELL_TYPE_NUMERIC:
								case Cell.CELL_TYPE_FORMULA:
									if (DateUtil.isCellDateFormatted(cell)) {
										dataRows[i - 1][j] = cell
												.getDateCellValue().toString();
									} else {
										dataRows[i - 1][j] = cell
												.getNumericCellValue() + "";
										//2017-1-12 解决电话号码传入格式为长数字格式导致数据展示出错问题 开始
										if(dataRows[i - 1][j].length()>11){
											String phone = dataRows[i-1][j].toString();
											if(phone.indexOf(".")>0){
												phone = phone.replace(".", "");
											}
											if(phone.indexOf("E")>0){
												phone = phone.substring(0, phone.lastIndexOf("E"));
											}
											
											dataRows[i - 1][j] = phone + "";
										}
										//2017-1-12 解决电话号码传入格式为长数字格式导致数据展示出错问题 开始
									}
								
									break;
								case Cell.CELL_TYPE_BOOLEAN:
									dataRows[i - 1][j] = cell
											.getBooleanCellValue() + "";
									break;
								case Cell.CELL_TYPE_BLANK:
									dataRows[i - 1][j] = "";
									break;
								case Cell.CELL_TYPE_ERROR:
									dataRows[i - 1][j] = "";
									break;
								default:
									dataRows[i - 1][j] = "";
									break;
								}
							} catch (Exception e) {
								dataRows[i - 1][j] = "";
								e.printStackTrace();
							}
						}
					}
				}
			}
			return dataRows;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataRows;
	}

	/**
	 * excel导入
	 * 
	 * @param is
	 *            excel数据流
	 * @param colCount
	 *            列数
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String[][] ReadExcel(InputStream is, int colCount) {
		String[][] dataRows = null;
		try {
			// 读取excel文件内容
			HSSFWorkbook wookbook = new HSSFWorkbook(is);
			HSSFSheet sheet = wookbook.getSheetAt(0);

			// 获取行数
			int rows = sheet.getPhysicalNumberOfRows();

			if (rows <= 1) {
				return null;
			}

			// 构造数据对象
			dataRows = new String[rows - 1][colCount];
			int cells = sheet.getRow(0).getPhysicalNumberOfCells();
			// 遍历行
			for (int i = 1; i < rows; i++) {
				HSSFRow row = sheet.getRow(i);
				// 遍历列
				if (row != null) {
					for (int j = 0; j < cells; j++) {
						HSSFCell cell = row.getCell(j);
						if (cell == null) {
							dataRows[i - 1][j] = "";
						} else {
							try {
								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_STRING:
									dataRows[i - 1][j] = cell
											.getStringCellValue();
									break;
								case Cell.CELL_TYPE_NUMERIC:
								case Cell.CELL_TYPE_FORMULA:
									if (DateUtil.isCellDateFormatted(cell)) {
										dataRows[i - 1][j] = cell
												.getDateCellValue().toString();
									} else {
										dataRows[i - 1][j] = cell
												.getNumericCellValue() + "";
									}
									break;
								case Cell.CELL_TYPE_BOOLEAN:
									dataRows[i - 1][j] = cell
											.getBooleanCellValue() + "";
									break;
								case Cell.CELL_TYPE_BLANK:
									dataRows[i - 1][j] = "";
									break;
								case Cell.CELL_TYPE_ERROR:
									dataRows[i - 1][j] = "";
									break;
								default:
									dataRows[i - 1][j] = "";
									break;
								}
							} catch (Exception e) {
								dataRows[i - 1][j] = "";
								e.printStackTrace();
							}
						}
					}
				}
			}
			return dataRows;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataRows;
	}

}
