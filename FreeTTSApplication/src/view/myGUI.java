package view;

import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.UIManager;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DropMode;
import javax.swing.JTable;

import input.*;
import input.DocumentReaderFactory;
import input.ExcelReader;
import input.WordReader;

import output.*;
import output.DocumentWriterFactory;
import output.ExcelWriter;
import output.WordWriter;
import output.SavingEditor;

import Commands.*;
import Commands.CommandsFactory;
import Commands.ReplayManager;

import EncodingAlgorithms.*;
import EncodingAlgorithms.AtBash;
import EncodingAlgorithms.Rot13;

import model.*;
import model.Document;
import javax.swing.JScrollPane;

public class myGUI {

	private JFrame frame;
	
	File myFile;
	
	String FormatedFilePath;
	
	ArrayList<String> contents = new ArrayList<String>();
	
	JTextArea userData;
	
	boolean isRecordingEnabled = false;
	
	JTextArea recordingTextArea = new JTextArea();
	
	String replayedContents = "";
	
	JButton openButton;
	JButton saveButton;
	JButton playButton;
	JButton replayButton;
	JButton newFileSaveButton;
	JToggleButton recordingButton;
	
	int rateValue =0;
	int pitchValue =0;
	int volumeValue =0;
	
	int formatOptionIndex=0;
	int encodingOptionIndex=0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myGUI window = new myGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public myGUI() {
		initialize();
	}
	
	
	public void saveContents(String fileName) {
		
		String finalTextArea = userData.getText();
		
		String filePath = fileName;
		try  (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))){
			writer.write(finalTextArea);
		} 
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	
	public String getTextAreaData() {
		System.out.println("user data "+userData.getText());
		return userData.getText();
	}
	
	
	public void createTestFile(String fileName) {
		
		try {
		      FileWriter myWriter = new FileWriter(fileName);
		      myWriter.write(userData.getText());
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e1) {
		      System.out.println("An error occurred.");
		      e1.printStackTrace();
		}
		
	}
	
	public void createTestFile(String fileName,String text) {
		
		try {
		      FileWriter myWriter = new FileWriter(fileName);
		      myWriter.write(text);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e1) {
		      System.out.println("An error occurred.");
		      e1.printStackTrace();
		}
		
	}	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(154, 205, 50));
		frame.setBounds(100, 100, 880, 514);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String comboBox_list_1 [] = {"Default","Rot13 Encoding","AtBash Encoding"};
		
		JComboBox encodingFormatsList = new JComboBox(comboBox_list_1);
		encodingFormatsList.setBackground(Color.WHITE);
		
		String comboBox_list_2 [] = {"Default","Word File Format","Excel File Format"};
		
		
		JLabel lblNewLabel = new JLabel("Available File and Encoding Formats");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSpinner volumeSetting = new JSpinner();
		
		JSpinner pitchSetting = new JSpinner();
		
		JSpinner rateSetting = new JSpinner();
		
		JLabel lblNewLabel_5 = new JLabel("Volume");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblNewLabel_5_1 = new JLabel("Pitch");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblNewLabel_5_2 = new JLabel("Rate");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.LEFT);
		
		JButton settingsConfirmationButton = new JButton("Confirm  Settings");
		settingsConfirmationButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		settingsConfirmationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				volumeValue = (Integer) volumeSetting.getValue();
				pitchValue = (Integer) pitchSetting.getValue();
				rateValue = (Integer) rateSetting.getValue();
				
				encodingOptionIndex = encodingFormatsList.getSelectedIndex();
				
			}
		});
		
		openButton = new JButton("Open File");
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				userData.setText("");
				
				CommandsFactory cf =  new CommandsFactory(0);
				
				File_Browser fb = (File_Browser)cf.getCommand();
				
				myFile =  fb.getFile();
				
				if (!(myFile == null)) {
					FormatedFilePath = (String) myFile.getAbsolutePath();
					Document myDocument = new Document();	
					myDocument.Open(FormatedFilePath, userData);
				}
				
				else {
					ErrorWindow errorWindow = new ErrorWindow("You did not choose a file to open");
				}
				

				
				saveButton.setEnabled(true);
				playButton.setEnabled(true);
				replayButton.setEnabled(true);
				newFileSaveButton.setEnabled(true);
				recordingButton.setEnabled(true);
				
				
				createTestFile("testFile1.txt");
				
				userData.setEditable(true);
				
				if (isRecordingEnabled) {
					SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
					Date date = new Date(System.currentTimeMillis());
					System.out.println(formatter.format(date));
					recordingTextArea.append("-- "+formatter.format(date)+"\n<<Open File>> Button pressed\n\n");
					recordingTextArea.append(FormatedFilePath+"file was opened\n\n");
				}
				
			}
		});
		
		saveButton = new JButton("Save changes in current File");
		saveButton.setEnabled(false);
		
		saveButton.setHorizontalAlignment(SwingConstants.LEFT);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CommandsFactory cf =  new CommandsFactory(1);
				
				SavingEditor se = (SavingEditor) cf.getCommand();				
				
				Document myDocument = new Document();
				
				myDocument.Save(se,FormatedFilePath.replace("\\", "\\\\"),userData);
				
				createTestFile("saveInFile.txt");
				
				if (isRecordingEnabled) {
					SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
					Date date = new Date(System.currentTimeMillis());
					System.out.println(formatter.format(date));
					recordingTextArea.append("-- "+formatter.format(date)+"\n<<Save changes in current File>> Button pressed\n\n");
					recordingTextArea.append("-- "+formatter.format(date)+"\nChanges were saved in file:\n\n"+ FormatedFilePath.replace("\\", "\\\\")+"\n\n");
				}
				
			}
		});
		
		playButton = new JButton("Play Contents");
		playButton.setEnabled(false);
		
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Document myDocument = new Document();
				
				String textToPlay = "";
				
				if (userData.getSelectedText()==null) {
					textToPlay = userData.getText();
					replayedContents = textToPlay;
					
					myDocument.playContents(textToPlay, volumeValue, pitchValue, rateValue);
					createTestFile("playTest.txt",textToPlay);
					textToPlay = "";
				}
				else if (userData.getSelectedText()!=null && userData.getText()!=null) {
					textToPlay = userData.getSelectedText();
					replayedContents = textToPlay;
					userData.setSelectedTextColor(Color.RED);
					myDocument.playContents(textToPlay,volumeValue,pitchValue,rateValue);
					
					createTestFile("playTest.txt",textToPlay);
					
					textToPlay = "";
				}				

				if (isRecordingEnabled) {
					SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
					Date date = new Date(System.currentTimeMillis());
					System.out.println(formatter.format(date));
					recordingTextArea.append("-- "+formatter.format(date)+"\n<<Play Contents>> Button pressed\n\n");
					recordingTextArea.append("-- "+formatter.format(date)+"\n<Contents that were played:\n\n"+replayedContents+"\n\n");
				}
			}
		});
		
		replayButton = new JButton("Replay Contents");
		
		replayButton.setEnabled(false);
		
		replayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ReplayManager rm = new ReplayManager(replayedContents);
				rm.replay(replayedContents,volumeValue,pitchValue,rateValue);
				
				createTestFile("replayTest.txt",replayedContents);
				
				if (isRecordingEnabled) {
					SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
					Date date = new Date(System.currentTimeMillis());
					System.out.println(formatter.format(date));
					recordingTextArea.append("-- "+formatter.format(date)+"\n<<Replay Contents>> Button pressed\n\n");
					recordingTextArea.append("-- "+formatter.format(date)+"\nReplayed Contents\n\n"+replayedContents+"\n\n");
				}
			}
		});
		
		
		newFileSaveButton = new JButton("Save changes in new file");
		
		newFileSaveButton.setEnabled(false);
		
		newFileSaveButton.setHorizontalAlignment(SwingConstants.LEFT);
		newFileSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				JFrame parentFrame = new JFrame();
				
				File fileToSave = null;
				
				JFileChooser fileChooser = new JFileChooser();
				
				
				fileChooser.setDialogTitle("Specify a file to save");
				fileChooser.setCurrentDirectory(new java.io.File("."));
				
				
				int userSelection = fileChooser.showSaveDialog(parentFrame);
				 
				String outputFilePath=""; 
				
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    fileToSave = fileChooser.getSelectedFile();
				    outputFilePath= (String) fileToSave.getAbsolutePath().replace("\\", "\\\\");
				    System.out.println("Save as file: " + outputFilePath);
				} else {
					ErrorWindow errorWindow = new ErrorWindow("null output file not valid to save contents");
				}
				
				switch (encodingFormatsList.getSelectedIndex()) {
					case 1:
						Rot13 rot13Algorithm = new Rot13();
						rot13Algorithm.useRot13Algorithm(userData.getText());
						
						ArrayList<String> rot13Text = rot13Algorithm.useRot13Algorithm(userData.getText());
						userData.setText("");
						
						for (String encodedString: rot13Text) {
							userData.append(encodedString+"\n");
						}
						
						break;
					case 2:
						AtBash atBashAtlgorithm = new AtBash();
						
						ArrayList<String> abText = atBashAtlgorithm.useAtBashAlgorithm(userData.getText());
						userData.setText("");
						
						for (String encodedString: abText) {
							userData.append(encodedString+"\n");
						}
						
						break;				
				}
				
				
				System.out.println("output file path "+outputFilePath);
				
				CommandsFactory cf =  new CommandsFactory(1);
				
				SavingEditor se = (SavingEditor) cf.getCommand();
				
				if (outputFilePath.contains(".xlsx")) {
					ExcelWriter ew = new ExcelWriter();
					ew.createExcelSheet(outputFilePath);
				
				}
				
				Document myDocument = new Document();
				
				myDocument.Save(se,outputFilePath,userData);
				
				createTestFile("saveInFile.txt");
				
				if (isRecordingEnabled) {
					SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
					Date date = new Date(System.currentTimeMillis());
					System.out.println(formatter.format(date));
					recordingTextArea.append("-- "+formatter.format(date)+"\n<<Save changes in new file>> Button pressed\n\n");
					recordingTextArea.append("-- "+formatter.format(date)+"\nChanges were saved in file\n\n"+outputFilePath+"\n\n");
				}
			}
		});
		
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		recordingButton = new JToggleButton("Start Recording");
		
		recordingButton.setEnabled(false);
		
		ItemListener itemListener = new ItemListener() {
			  
            // itemStateChanged() method is nvoked automatically
            // whenever you click or unlick on the Button.
            public void itemStateChanged(ItemEvent itemEvent)
            {
  
                // event is generated in button
                int state = itemEvent.getStateChange();
  
                // if selected print selected in console
                if (state == ItemEvent.SELECTED) {
                	isRecordingEnabled=true;
                	
                	createTestFile("recordingEnabled.txt","1");
                	
                    System.out.println("Selected");
                }
                else {
                	isRecordingEnabled=false;
                    // else print deselected in console
                	createTestFile("recordingDisabled.txt","1");
                	System.out.println("Deselected");
                    
                }
            }
        };
  
        // Attach Listeners
        recordingButton.addItemListener(itemListener);
		
		JScrollPane scrollPane = new JScrollPane(recordingTextArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(recordingTextArea);
		recordingTextArea.setEditable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane(userData,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		userData = new JTextArea();
		userData.setEditable(false);
		scrollPane_1.setViewportView(userData);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(openButton, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(129)
							.addComponent(newFileSaveButton, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
						.addComponent(recordingButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(playButton, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(replayButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(129)
							.addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addComponent(encodingFormatsList, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addComponent(volumeSetting, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(pitchSetting, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.addGap(14))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_5_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addGap(18)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5_2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addComponent(rateSetting, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(settingsConfirmationButton, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
							.addGap(32))
						.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
					.addGap(106)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
					.addGap(134))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(openButton)
						.addComponent(newFileSaveButton)
						.addComponent(recordingButton))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(playButton)
						.addComponent(replayButton)
						.addComponent(saveButton))
					.addGap(11)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(encodingFormatsList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_5_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
									.addGap(1)
									.addComponent(volumeSetting, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(34)
									.addComponent(pitchSetting, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(lblNewLabel_5_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(rateSetting, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
							.addGap(3)
							.addComponent(settingsConfirmationButton)
							.addGap(37)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
							.addGap(55)
							.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
							.addGap(44))))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
