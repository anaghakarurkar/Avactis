package dataproviders;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class CustomerDP {
	private Object data[][] = null;
	private String excelFilePath = "src\\test\\resources\\datafiles\\CustomerData.xlsx";

	@DataProvider(name = "Registration")
	public Object[][] getCustomerDetailsList() {
		data = getData(excelFilePath,"Reg");
		return data;
	}

	@DataProvider(name = "Login")
	public Object[][] getLoginData()  {
		data = getData(excelFilePath, "Login");
		return data;
	}

	public Object[][] getData(String filePath, String regOrLogin )  {
	
		// dataFormatter will be used to convert any type from excel cell data to String type
		 final DataFormatter dataFormatter = new DataFormatter();
		
		ArrayList<Object> tempArrayList = new ArrayList<Object>();

		try {
		FileInputStream inputStream = new FileInputStream(excelFilePath);

		// Using POI API open workbook and sheet named Products.
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheet("UserDetails");

		// Get total row count and column count from excel sheet
		int rows = sheet.getLastRowNum();
		
		int columns = 0;
		if(regOrLogin.equals("Reg")) {
		columns = sheet.getRow(0).getLastCellNum();
		}
		if(regOrLogin.equals("Login")) {
			columns = 2;
		}
		for (int row = 1; row <= rows; row++) {
			XSSFRow rowObj = sheet.getRow(row);
			String[] tempRow = new String[columns];
			
					for (int col = 0; col < columns; col++) {
						XSSFCell cellObj = rowObj.getCell(col);
						String valueAsString = dataFormatter.formatCellValue(cellObj);
						tempRow[col] = valueAsString;
					}
					tempArrayList.add(tempRow);
			}
		
		 tempArrayList.trimToSize();
		 int listRowCount = tempArrayList.size();
		
		data = new Object[listRowCount][columns];

		// code to initialise two dimensional data array from tempArrayList
		for (int r = 0; r <= listRowCount - 1; r++) {
			data[r] = (Object[]) tempArrayList.get(r);
		}
		
		// close all resources
		workbook.close();
		inputStream.close();
		tempArrayList.clear();
		}
		catch(IOException e) {
			System.out.println("Can't read CustomerData file");
		}
		return data;
	}
}
