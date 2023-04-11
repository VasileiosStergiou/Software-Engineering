package input;

import java.util.ArrayList;

public class DocumentReaderFactory {
	
public Object getReader(int readerOption) {
		
		switch (readerOption) {
			case 0:
				WordReader wordReader = new WordReader();
				return wordReader;
			case 1:
				ExcelReader excelReader = new ExcelReader();
				return excelReader;
		}
		
		return null;
		
	}
	
	
	public DocumentReaderFactory() {
		
	}
}

