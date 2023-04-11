package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class testUC2 {
	@Test
	void test() {
		
		//String actualFileData = "Hello world";
		
		
		//String path1 = "C:\\Users\\Vasilis Stergiou\\Desktop\\test1.xlsx";
		
		String path1 = "C:\\Users\\Vasilis Stergiou\\Desktop\\bill4.docx";
		
		//String path1 = "C:\\Users\\Vasilis Stergiou\\Desktop\\test3.docx";
		
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
		
		System.out.println(data);
		
		if (path1.contains(".docx")) {
			wordReadTester wrt = new wordReadTester();
			String output = wrt.readWord(path1);
			assertEquals(data.strip(),output.strip());
		}
		else {
			excelReadTester ert = new excelReadTester();
			String output = ert.readExcel(path1);
			assertEquals(data.strip(),output.strip());
		}
	}
}
