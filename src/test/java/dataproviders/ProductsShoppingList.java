package dataproviders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ProductsShoppingList {

	String excelFilePath ;
	public ProductsShoppingList() {
		excelFilePath ="src\\test\\resources\\datafiles\\ProductsList.xlsx";
	}
	
	@DataProvider(name="ProductList")
	public String [][] getData() throws IOException{
		String [][] data = new String[5][4];
		
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet =  workbook.getSheet("Products");
		
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(1).getLastCellNum();
		for(int row = 0; row <= rows ; row++) {
			XSSFRow rowObj = sheet.getRow(row);
			for(int col = 0; col < columns ; col++)
			{
			XSSFCell cellObj = rowObj.getCell(col);
			
			switch(cellObj.getCellType())
			{
			case STRING:
			case NUMERIC:
			case BOOLEAN:
			default:
				break; 
			}
			}
		}
		
		
		return data;
	}
}
