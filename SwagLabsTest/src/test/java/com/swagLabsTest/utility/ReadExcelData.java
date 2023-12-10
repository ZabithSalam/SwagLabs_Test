package com.swagLabsTest.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	
	public static FileInputStream inputStream;
	public static XSSFWorkbook workBook;
	public static XSSFSheet excelSheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static Object getCellValue(String fileName, String sheetName, int rowNo, int cellNo) {
		
		try {
			inputStream= new FileInputStream(fileName);
			workBook = new XSSFWorkbook(inputStream);
			excelSheet=workBook.getSheet(sheetName);
			cell=workBook.getSheet(sheetName).getRow(rowNo).getCell(cellNo);
			Object cellValue;
	        if (cell.getCellType() == CellType.STRING) {
	            cellValue = cell.getStringCellValue();
	        } else if (cell.getCellType() == CellType.NUMERIC) {
	            // Check if the cell value is an integer
	            if (cell.getNumericCellValue() == (int) cell.getNumericCellValue()) {
	                cellValue = (int) cell.getNumericCellValue();
	            } else {
	                // If the cell value has a decimal part, handle it as a string or double as needed
	                cellValue = cell.getNumericCellValue();
	            }
	        } else {
	            // Handle other cell types as needed
	            cellValue = null;
	        }

			workBook.close();
			
			return cellValue;
		} catch (Exception e) {
			return "";
		}
		
		
	}
	
	public static int getRowCount(String fileName, String sheetName) {
		try {
			inputStream= new FileInputStream(fileName);
			workBook = new XSSFWorkbook(inputStream);
			excelSheet=workBook.getSheet(sheetName);
			int ttlRows = excelSheet.getLastRowNum() + 1;
			workBook.close();
			
			return ttlRows;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static int getColCount(String fileName, String sheetName) {
		try {
			inputStream= new FileInputStream(fileName);
			workBook = new XSSFWorkbook(inputStream);
			excelSheet=workBook.getSheet(sheetName);
			
			int ttlCells = excelSheet.getRow(0).getLastCellNum();
			workBook.close();
			
			return ttlCells;
		} catch (Exception e) {
			return 0;
		}

	}
	
	public String getStringData(int sheetIndex, int row, int column) {
		return workBook.getSheetAt(sheetIndex).getRow(row).getCell(column).getStringCellValue();
	}
	public String getStringData(String sheetName, int row, int column) {
		return workBook.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}
	public double getNumericData(String sheetName, int row, int column) {
		return workBook.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
	}

}
