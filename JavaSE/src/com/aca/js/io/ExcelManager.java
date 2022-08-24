package com.aca.js.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * 자바프로그램에서 엑셀파일 제어하기
 */
public class ExcelManager {
	FileInputStream fis;
	XSSFWorkbook workbook; //엑셀 파일 
	XSSFSheet sheet;
	
	public ExcelManager() {
		try {
			fis = new FileInputStream("D:/javase_workspace/JavaSE/data/상품.xlsx");
			System.out.println(fis);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);//첫번째 시트
			Iterator<Row> rowIt = sheet.iterator(); 
			while(rowIt.hasNext()) {
				Row row  =(Row)rowIt.next();
				Iterator<Cell> cellIt = row.cellIterator();
				while(cellIt.hasNext()) {
					Cell cell =cellIt.next();
					if(cell.getCellType() ==CellType.STRING) {
						System.out.print(cell.getStringCellValue());
					}else if(cell.getCellType()==CellType.NUMERIC){
						System.out.print(cell.getNumericCellValue());
					}
				}
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		new ExcelManager();
	}
}
