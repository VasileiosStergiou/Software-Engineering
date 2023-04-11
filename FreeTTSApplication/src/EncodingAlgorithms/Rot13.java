package EncodingAlgorithms;

import java.util.ArrayList;

public class Rot13 {
	
	char [] alphabetLetters = 
		{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
		 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	public ArrayList<String> useRot13Algorithm(String inputString) {
		
		
		ArrayList<String []> areaTextLines = new ArrayList<String []>();
		
		ArrayList<String> encodedTextLines = new ArrayList<String>();
		
		String [] formatedInputString = inputString.split("\n");
		
		for (String s: formatedInputString) {
			String [] test = s.split("");
			areaTextLines.add(test);
		}
		
		
		String outputString ="";
		
		String finalString = "";
		for (String[] s: areaTextLines) {
			
			String x="";
			
			String temp="";
			
			for (String s1: s) {
				for (char c:s1.toCharArray()) {
					if (c >= 'a' && c<='z' || (c >= 'A' && c<='Z')) {
						char invertedCharacter;
						int characterIndex = 0;
						for (int i =0;i<alphabetLetters.length;i++) {
							if (c == alphabetLetters[i]) {
								characterIndex = i;
								break;
							}
						}				
						invertedCharacter = alphabetLetters[(characterIndex+13) % 26];
						temp+= invertedCharacter;
					}
					else {
						temp+=c;
					}
				}
			}		
			x+=temp;
			temp="";
			System.out.println(x);
			encodedTextLines.add(x);
		}
		return encodedTextLines;
	}
	
	public Rot13() {
		
	}
	
}
