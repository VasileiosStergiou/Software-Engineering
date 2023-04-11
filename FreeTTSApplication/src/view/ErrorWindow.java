package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorWindow {
	
	private JFrame errorFrame = new JFrame();
	private JPanel errorPanel = new JPanel();
	private JLabel errorLabel = new JLabel("No file option was chosen");
	
	private String errorMessage;
	
	public void setErrorLabel(String newMessage) {
		this.errorLabel.setText(newMessage);
	}
	
	public ErrorWindow(String errorMessage) {
		
		this.errorMessage = errorMessage;
    	
    	Font errorLabelFont = errorLabel.getFont();
    	
    	errorLabel.setFont(new Font (errorLabelFont.getName(),Font.PLAIN,17));
    	
    	setErrorLabel(this.errorMessage);
    	
    	JButton exitErrorButton = new JButton("OK");
		
    	exitErrorButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Bye");
				errorFrame.dispose();
			}
		});
		
    	errorPanel.setPreferredSize(new Dimension (400,300));
    	errorPanel.setBorder(BorderFactory.createEmptyBorder(120, 50, 200, 50));
    	//errorPanel.setLayout(new GridLayout(20,10));
    	errorPanel.add(errorLabel);
    	errorPanel.add(exitErrorButton);
    	
		errorFrame.add(errorPanel);
    	errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	errorFrame.pack();
    	errorFrame.setVisible(true);
	}
}
