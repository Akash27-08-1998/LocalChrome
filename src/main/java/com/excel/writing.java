package com.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class writing {
	public void Write(String amount,String interest) throws IOException {
		
		//Writing data into excel sheet
		 XSSFWorkbook wb = new XSSFWorkbook();
		 XSSFSheet sh= wb.createSheet("Output_Data");
		 Row row1 = sh.createRow(0);

		 //Creating header cell
		 Cell c1 = row1.createCell(0);
		 c1.setCellValue("Principal amount");
		 Cell c2 = row1.createCell(1);
		 c2.setCellValue("Interest");

		 //Inserting values into cells
		 Row row2 = sh.createRow(1);
		 Cell c3 = row2.createCell(0);
		 c3.setCellValue(amount);
		 Cell c4 = row2.createCell(1);
		 c4.setCellValue(interest);

		 
		 File file = new File("C:\\Users\\ADMIN\\eclipse-workspace\\EMIcalculatorHackathon\\excel-files\\output.xlsx");
		 FileOutputStream fos = new FileOutputStream(file);
		 wb.write(fos);
		 wb.close();
		}
	}

