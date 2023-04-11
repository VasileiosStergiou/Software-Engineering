package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JTextArea;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import view.*;

import model.*;

import input.*;

class testUC1 {	
	
	@Test
	void test() {
		
		//String actualFileData = "Hello world";
		
		
		//String path1 = "C:\\Users\\Vasilis Stergiou\\Desktop\\test1.xlsx";
		
		String path1 = "C:\\Users\\Vasilis Stergiou\\Desktop\\bill4.docx";
		

		
		String data = "";
		
		try {
		      File myObj = new File("testFile1.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        data += myReader.nextLine()+"\n";
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		System.out.println(data);
		
		if (path1.contains(".docx")) {
			wordReadTester wrt = new wordReadTester();
			String output = wrt.readWord(path1);
			assertEquals(output,data.strip());
		}
		else {
			excelReadTester ert = new excelReadTester();
			String output = ert.readExcel(path1);
			assertEquals(output,data);
		}
		
		//String output2 = wrt.readWord("C:\\Users\\Vasilis Stergiou\\Desktop\\bill4.docx");
		//System.out.println(output2);
		
		// strip in case of word, no strip in case of excel
		
		
		
	}

}
