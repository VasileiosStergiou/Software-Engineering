package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class FileSaver {
	
	private File outputFile;
	private String fileName;
	
	
	public void saveAs(JTextArea userData) {
	      JFileChooser saveAsFileChooser = new JFileChooser();
	      saveAsFileChooser.setApproveButtonText("Save");
	      int actionDialog = saveAsFileChooser.showOpenDialog(saveAsFileChooser);
	      if (actionDialog != JFileChooser.APPROVE_OPTION) {
	         return;
	      }

	      File file = saveAsFileChooser.getSelectedFile();

	      BufferedWriter outFile = null;
	      try {
	         outFile = new BufferedWriter(new FileWriter(file));

	         userData.write(outFile);

	      } catch (IOException ex) {
	         ex.printStackTrace();
	      } finally {
	         if (outFile != null) {
	            try {
	               outFile.close();
	            } catch (IOException e) {}
	         }
	      }
	   }
	
	
	
	
	public File createOutputFile(String fileName) {
		try {
	      outputFile = new File(fileName);
	      
	      
	      
	      if (outputFile.createNewFile()) {
	        System.out.println("File created: " + outputFile.getName());
	        System.out.println("File created: " + outputFile.getName().replace(".docx", ".xlsx"));
	        File copyFile = new File(outputFile.getName().replace(".docx", ".docx"));
	        boolean success = outputFile.renameTo(copyFile);

	        if (!success) {
	        }
	      }
	      
	      else {
	        System.out.println("File already exists.Choose an other file name");
	      }
		} 
		catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
		return outputFile;
	}
	
	public FileSaver(String fileName) {
		this.fileName = fileName;
	}

}
