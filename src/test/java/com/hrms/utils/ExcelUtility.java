package com.hrms.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	private static Workbook book;
	private static Sheet sheet;

	// 1. Open excel and get to the desired sheet
	public static void openExcel(String filePath, String sheetName) {

		try {
			FileInputStream fis = new FileInputStream(filePath);
			book = new XSSFWorkbook(fis);
			sheet = book.getSheet(sheetName);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// 2. Count how many total number of rows
	public static int rowCount() {
		return sheet.getPhysicalNumberOfRows();

	}

	// 3. last row count

	public static int lastRowNum() {
		return sheet.getLastRowNum();
	}

	// 4. Total number of cells/columns

	public static int colCount() {
		return sheet.getRow(0).getLastCellNum();
	}

	// 5. Giving two indices, extract cell data
	public static String getCellData(int rowIndex, int colIndex) {
		return sheet.getRow(rowIndex).getCell(colIndex).toString();

	}

	// Method to extract data from excel to be used in TestNG DataProvider
	public static Object[][] excelToArray(String filePath, String sheetName) {

		openExcel(filePath, sheetName);
		Object[][] data = new Object[rowCount() - 1][colCount()];
		for (int i = 1; i < rowCount(); i++) {
			for (int j = 0; j < colCount(); j++) {
				data[i - 1][j] = getCellData(i, j);
			}

		}

		return data;
	}

	// Method to extract data from excel to be used in TestNG DataProvider using
	// maps

	public static Object[][] excelToArray2(String filePath, String sheetName) {
		openExcel(filePath, sheetName);
		Object[][] data = new Object[lastRowNum()][1];
		for (int i = 0; i < lastRowNum(); i++) {
			Map<Object, Object> datamap = new HashMap<>();
			for (int j = 0; j < colCount(); j++) {
				datamap.put(getCellData(0, j), getCellData(i + 1, j));
			}
			data[i][0] = datamap;
		}
		return data;
	}

	// Method to extract data from excel to be used in Cucumber DataTable

	public static List<Map<String, String>> excelToListOfMaps(String filePath, String sheetName) { // our return type
																									// will be a list
																									// maps
		openExcel(filePath, sheetName);
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		// storing each row in our excel into our map and then will add this into our
		// list
		for (int i = 1; i < rowCount(); i++) {
			// excluding the header so we start at i=1..
			// adding each row to our list
			Map<String, String> map = new HashMap<>();// can also be tree map...
			for (int j = 0; j < colCount(); j++) {// column also refers to cell. Col count method above
				map.put(getCellData(0, j), getCellData(i, j));// row 0 will always be our key and the remaining rows
																// will be our value as it contains the data. Row 0 is
																// our header(key)
				// key will be (getCellData(0,j).. Row 0 will be be our header
				// value will be (getCellData(i,j). i was 1 because our values start from index
				// 1

			}
			data.add(map);

		}
		return data;
	}

}
