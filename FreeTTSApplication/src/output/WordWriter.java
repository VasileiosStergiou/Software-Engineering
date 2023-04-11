package output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTextArea;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordWriter implements DocumentWriter {

public void write(JTextArea userData,String fileName) throws IOException {
		
		
		XWPFDocument document = new XWPFDocument();

	    //Write the Document in file system
	    FileOutputStream out = new FileOutputStream (new File(fileName));
	      
	    document.write(out);
	    out.close();
	      
		String textAreaData = userData.getText();
		
		XWPFDocument docx = new XWPFDocument(new FileInputStream(new File(fileName)));
		FileOutputStream out1 = new FileOutputStream(fileName);
		
		if (textAreaData.contains("\n")) {
            String[] stringsOnNewLines = textAreaData.split("\n");

            // For each additional line, create a new run. Add carriage return on previous.
            for (int i = 0; i < stringsOnNewLines.length; i++) {

                // For every run except last one, add a carriage return.
                String textForLine = stringsOnNewLines[i];
                docx.createParagraph().createRun().setText(textForLine,0);
                //docx.createParagraph().createRun().addCarriageReturn();
            }
        }	
		
		docx.write(out1);
		docx.close();
		out1.close();
	}

}
