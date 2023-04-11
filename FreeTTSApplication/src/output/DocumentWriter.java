package output;

import java.io.IOException;

import javax.swing.JTextArea;

public interface DocumentWriter {
	public void write(JTextArea userData,String fileName) throws IOException;
}
