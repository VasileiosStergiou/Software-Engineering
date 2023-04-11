package input;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.JTextArea;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordReader implements DocumentReader {
	
	
	
	public WordReader() {
		
	}
	

	@Override
	public void read(String FormatedFilePath,JTextArea userData) {
		XWPFDocument document = null;
		FileInputStream fileInputStream = null;
		try {
 
			
			File fileToBeRead = new File(FormatedFilePath.replace("\\", "\\\\"));
			
			System.out.println(fileToBeRead);
			
			fileInputStream = new FileInputStream(fileToBeRead);
			document = new XWPFDocument(fileInputStream);
			XWPFWordExtractor extractor = new XWPFWordExtractor(document);
 
			System.out.println(extractor.getText());
			userData.append(extractor.getText());
 
		} catch (Exception e) {
			System.out.println("We had an error while reading the Word Doc");
		}finally {
			try {
				if (document != null) {
					System.out.println("");
					document.close();
				}
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (Exception ex) {
				
			}
		}
	}
}
