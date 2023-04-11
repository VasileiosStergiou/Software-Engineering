package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSFacade {
	
	private VoiceManager vm;
	private Voice voice;
	private int rate;
	private int pitch;
	private int volume;
	
	public void play(String text) {
		voice.speak(text);	
	}
	
	public TTSFacade(int rate,int pitch,int volume) {
		
		this.rate = rate;
		this.pitch = pitch;
		this.volume = volume;
		
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		voice = vm.getInstance().getVoice("kevin16");
		voice.allocate();
		voice.setRate(rate);//Setting the rate of the voice
		voice.setPitch(pitch);//Setting the Pitch of the voice
		voice.setVolume(volume);//Setting the volume of the voice
	}
}
