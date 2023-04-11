package input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextArea;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReader implements DocumentReader {

	@Override
	public void read(String FormatedFilePath, JTextArea userData) {
		
		File excelFile = new File(FormatedFilePath.replace("\\", "\\\\"));
	    FileInputStream fis = null;
	    XSSFWorkbook workbook = null;
		try {
			fis = new FileInputStream(excelFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	    
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
		}
	    XSSFSheet sheet = workbook.getSheetAt(0);

	    Iterator<Row> rowIt = sheet.iterator();

	    while(rowIt.hasNext()) {
	      Row row = rowIt.next();

	      Iterator<Cell> cellIterator = row.cellIterator();

	      while (cellIterator.hasNext()) {
	        Cell cell = cellIterator.next();
	        System.out.println(cell.toString() + ";");
	        userData.append(cell.toString()+" ");
	      }
	      
	      userData.append("\n");
	      

	      System.out.println();
	    }
	    
	    try {
			workbook.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	    
	  }		
}