package testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JTextArea;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelReadTester {
	public String readExcel(String FormatedFilePath) {
			
			String data="";
		
			File excelFile = new File(FormatedFilePath.replace("\\", "\\\\"));
		    FileInputStream fis = null;
		    XSSFWorkbook workbook = null;
			try {
				fis = new FileInputStream(excelFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	
		    // we create an XSSF Workbook object for our XLSX Excel File
		    
			try {
				workbook = new XSSFWorkbook(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    // we get first sheet
		    XSSFSheet sheet = workbook.getSheetAt(0);
	
		    // we iterate on rows
		    Iterator<Row> rowIt = sheet.iterator();
	
		    while(rowIt.hasNext()) {
		      Row row = rowIt.next();
	
		      // iterate on cells for the current row
		      Iterator<Cell> cellIterator = row.cellIterator();
	
		      while (cellIterator.hasNext()) {
		        Cell cell = cellIterator.next();
		        //System.out.println(cell.toString() + ";");
		        data+=(cell.toString()+" ");
		      }
		      
		      data+="\n";
		      	
		      System.out.println();
		    }
		    
		    try {
				workbook.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return data; 
		    
		  }
}
