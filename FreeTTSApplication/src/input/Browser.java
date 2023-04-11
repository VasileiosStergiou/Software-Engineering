package input;

import java.io.*;

import javax.swing.*;

public class Browser extends JFrame {
	
	File findFile() {
		JFrame parentFrame = new JFrame();
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fc.showOpenDialog(parentFrame);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		    File targetFile = fc.getSelectedFile();
		    return targetFile;
		}
		return null;
	}
	
	public Browser() {
		
	}
}