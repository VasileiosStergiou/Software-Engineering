package Commands;

import model.Document;

public class ReplayManager {
	
	private String replayedText;
	
	public void replay(String replayedText, int Volume,int Pitch, int Rate) {
		Document myDocument = new Document();
		myDocument.playContents(replayedText,Volume,Pitch,Rate);
	}
	
	public ReplayManager(String replayedText) {
		this.replayedText = replayedText;
	}
}
