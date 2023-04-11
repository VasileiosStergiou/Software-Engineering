package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class testUC3 {

	@Test
	void test() {
		//String path1 = "C:\\Users\\Vasilis Stergiou\\Desktop\\test1.xlsx";
		
		String path1 = "C:\\Users\\Vasilis Stergiou\\Desktop\\bill4.docx";
		//XXXX_report (1).docx
		
		
		String data = "";
		
		try {
		      File myObj = new File("saveInFile.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        data += myReader.nextLine()+"\n";
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	
		System.out.println("data"+data);
		
		if (path1.contains(".docx")) {
			wordReadTester wrt = new wordReadTester();
			String output = wrt.readWord(path1).strip();
			
			assertEquals(output.strip(),data.strip());
		}
		else {
			excelReadTester ert = new excelReadTester();
			String output = ert.readExcel(path1);
			assertEquals(output.strip(),data.strip());
		}
	}

}
