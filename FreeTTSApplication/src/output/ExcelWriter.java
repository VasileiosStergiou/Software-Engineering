package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JTextArea;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter implements DocumentWriter {

	public void createExcelSheet(String fileName) {
		
		  //Create blank workbook
	      XSSFWorkbook workbook = new XSSFWorkbook();
	      
	      //Create a blank sheet
	      XSSFSheet sheet = workbook.createSheet( "Excel File");

	      //Create row object
	      XSSFRow row;

		  row = sheet.createRow(0);
		  XSSFCell cell = row.createCell(0);
		  
		  cell.setCellValue("HERE");
	      
	      FileOutputStream out;
	      try {
			out = new FileOutputStream(new File(fileName));
		      workbook.write(out);
		      out.close();
	      } catch (FileNotFoundException e) {
			e.printStackTrace();
	      } catch (IOException e) {
			e.printStackTrace();
		}
	     
	}
	
	
	public void write(JTextArea userData, String fileName) throws IOException {
		
        XSSFWorkbook workbook = null;
		workbook = new XSSFWorkbook(fileName);
		
        XSSFSheet sheet = workbook.getSheetAt(0);
        
        String [] excelData = userData.getText().split("\n");
        
        
        ArrayList<ArrayList<String>> totalCharacters = new ArrayList<ArrayList<String>>();
        
        
        for (String i : excelData) {
        	System.out.println("Excel data: "+i);
        }      
        
        
        int excelDataLength =  excelData.length;
        
		for (int rowNumber = 0; rowNumber < excelData.length; rowNumber++ ) {
		    XSSFRow row = sheet.createRow(rowNumber);

		    if  (row == null) {
		    	continue;
		    }
		    
		    // Iterate columns
		    
		    String [] data = excelData[rowNumber].split(" ");
		    
		    int dataLength = data.length;
		    
		    for (int columnNumber = 0; columnNumber <data.length; columnNumber++ ) {
		        XSSFCell cell = row.createCell(columnNumber);

		        if (cell == null) {
		        	continue;
		        }

		       cell.setCellValue(data[columnNumber]);  		    
		}
		
		    
        
		try (FileOutputStream fileOut = new FileOutputStream(fileName + "(new).xlsx")) {
		    workbook.write(fileOut);
		}
		Files.delete(Paths.get(fileName));
		
		Files.move(Paths.get(fileName + "(new).xlsx"), Paths.get(fileName));
        
		}
	}
	
	public ExcelWriter() {
		
	}
}
