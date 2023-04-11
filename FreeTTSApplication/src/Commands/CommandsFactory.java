package Commands;

import input.*;
import output.*;

public class CommandsFactory {
	
	private int commandIndex;

	public Object getCommand() {
		
		switch(commandIndex) {
			case 0:
				File_Browser fileBrowser = new File_Browser();
				return fileBrowser;
			case 1:
				SavingEditor fileSaver = new SavingEditor();
				return fileSaver;
			default:
				return null;
		}
		
	}
	
	public CommandsFactory(int commandIndex) {
		this.commandIndex = commandIndex;		
	}
}
