package users.api.genericutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getdataFromExcel(String sheetName, int rowNo, int cellNo) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testData/testScriptData_Assg2.xlsx");
		//here i used only 1 excel, so i harcoded the excel path.
		//when u want more excels e.g 4 excels. then pass 4 argnts: (excelName, sheetName,RowNo,ColNo).
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();
		wb.close();
		return data;	
	}
	
	public int getRowCount(String sheetName) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./testData/testScriptData_Assg2.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowCount=wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowCount;	
	}
	
	public void setDataBackToExcel(String sheetName, int rowNo, int cellNo,String data) throws Throwable, EncryptedDocumentException
	{
		FileInputStream fis=new FileInputStream("./testData/testScriptData_Assg2.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNo).createCell(cellNo);
		FileOutputStream fout=new FileOutputStream("./testData/testScriptData_Assg2.xlsx");
		wb.write(fout);
		wb.close();
	}
	
	}

