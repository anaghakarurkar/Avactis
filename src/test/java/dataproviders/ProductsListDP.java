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

public class ProductsListDP {

	private String excelFilePath;
	private Object data[][] = null;

	public ProductsListDP() {
		excelFilePath = "src\\test\\resources\\datafiles\\ProductsList.xlsx";
	}

	@DataProvider(name = "ProductList")
	public Object[][] getProductsList() throws IOException {
		// dataFormatter will be used to convert any type from excel cell data to String type
		final DataFormatter dataFormatter = new DataFormatter();

		// Temporary array list to store only chosen products from excel file.
		ArrayList<Object> productArrayList = new ArrayList<Object>();

		// open excel file which contains list of chosen products
		FileInputStream inputStream = new FileInputStream(excelFilePath);

		// Using POI API open workbook and sheet named Products.
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheet("PRODUCTS");

		// Get total row count and column count from excel sheet
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(1).getLastCellNum();

		for (int row = 0; row <= rows; row++) {
			XSSFRow rowObj = sheet.getRow(row);
			String[] tempRow = new String[columns];
			
			// add product data row from excel sheet to an array only if that product is: 
			// 1.  Set available by Admin of Avactis store for purchase and
			// 2.  That product is chosen for automation for testing purpose.

			if (dataFormatter.formatCellValue(rowObj.getCell(4)).equals("YES")) {
					for (int col = 0; col < columns; col++) {
						XSSFCell cellObj = rowObj.getCell(col);
						String valueAsString = dataFormatter.formatCellValue(cellObj);
						tempRow[col] = valueAsString;
					}
					productArrayList.add(tempRow);
			}
		}
		 productArrayList.trimToSize();
		 int listRowCount = productArrayList.size();
		
		data = new Object[listRowCount][columns];

		// code to initialise two dimensional data array from productArrayList
		for (int r = 0; r <= listRowCount - 1; r++) {
			data[r] = (Object[]) productArrayList.get(r);
		}
		
		// close all resources
		workbook.close();
		inputStream.close();
		productArrayList.clear();
		
		return data;
	}

	public int getProductRowCount() {
		return  (data!=null)? data.length: 0;
	}
	
	
	public int getProductAttributesCount() {
		return  (data!=null)? data[0].length: 0;
	}

}
