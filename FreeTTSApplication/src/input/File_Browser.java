package input;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.event.*;
import model.*;

public class File_Browser {
	
	private File targetFile;
	
	public File getFile() {
		return targetFile;
	}
	
	public File_Browser() {
				
		Browser fileBrowser = new Browser();
		
		targetFile = fileBrowser.findFile();
		System.out.println(targetFile);

		System.out.println("Bye");	
		
	}
}
