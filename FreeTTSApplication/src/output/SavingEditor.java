package output;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.apache.batik.svggen.font.table.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import model.*;

public class SavingEditor {
	
	public void setDocument(Document currentDocument) {
		currentDocument = new Document();
	}
	
	public void saveContents(JTextArea userData,String fileName) throws IOException {
						
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
	
	
	public SavingEditor() {
	}

}
