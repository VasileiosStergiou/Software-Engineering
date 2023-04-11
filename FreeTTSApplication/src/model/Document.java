package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import Commands.*;
import input.*;
import output.*;

import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;


import org.apache.poi.hpsf.DocumentSummaryInformation; 
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.extractor.*; 
import org.apache.poi.hwpf.usermodel.HeaderStories;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;

import java.io.*;

public class Document {
	private ArrayList<String> contents;
	
	private TTSFacade audioManager;
	
	private DocumentReaderFactory docReaderFactory;

	
	public void Open(String FormatedFilePath,JTextArea userData) {
		
		
		System.out.println("is an xlsx file? "+FormatedFilePath.contains(".xlsx"));
		
		DocumentReaderFactory drf = new DocumentReaderFactory();
		
		if (FormatedFilePath.contains(".docx")) {
			WordReader wordReader = (WordReader) drf.getReader(0);
			wordReader.read(FormatedFilePath, userData);
		}
		else if (FormatedFilePath.contains(".xlsx") ) {
			ExcelReader excelReader = (ExcelReader) drf.getReader(1);
			excelReader.read(FormatedFilePath, userData);
		}
		else {
			System.out.println("Unsupported type of file. Only .docx and .xlsx files are available for use");
		}		
		
	}
					
	
	public void playContents(String text, int Volume, int Pitch,int Rate) {
		TTSFacade tts = new TTSFacade(Volume,Pitch,Rate);
		tts.play(text);
	}
	
	public void Save(SavingEditor se,String FormatedFilePath,JTextArea userData) {
		
		
		System.out.println("is an xlsx file? "+FormatedFilePath.contains(".xlsx"));
		
		DocumentWriterFactory dwf = new DocumentWriterFactory();
		
		if (FormatedFilePath.contains(".docx")) {
			WordWriter wordWriter = (WordWriter) dwf.getWriter(0);
			try {
				wordWriter.write(userData,FormatedFilePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (FormatedFilePath.contains(".xlsx")) {
			ExcelWriter excelWriter = (ExcelWriter) dwf.getWriter(1);
			try {
				System.out.println("");
				excelWriter.write(userData,FormatedFilePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Unsupported type of file. Only .docx and .xlsx files are available for use");
		}	
		
		
	}
	
	public Document() {
		
	}
}
