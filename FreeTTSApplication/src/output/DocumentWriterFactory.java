package output;

public class DocumentWriterFactory {
	
	public Object getWriter(int writerOption) {
		
		switch (writerOption) {
			case 0:
				WordWriter wordWriter = new WordWriter();
				return wordWriter;
			case 1:
				ExcelWriter excelWriter = new ExcelWriter();
				return excelWriter;
		}
		
		return null;
		
	}
	
	public DocumentWriterFactory() {
		
	}
}
