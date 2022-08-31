package typingTutor;

import java.util.concurrent.atomic.AtomicBoolean;

//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	static AtomicBoolean done ; //REMOVE
	static AtomicBoolean pause; //REMOVE
	
	private static  FallingWord[] words; //list of words
	private static int noWords; //how many
	private static Score score; //user score
	
	CatchWord(String typedWord) {
		target=typedWord;
	}
	
	public static void setWords(FallingWord[] wordList) {
		words=wordList;	
		noWords = words.length;
	}
	
	public static void setScore(Score sharedScore) {
		score=sharedScore;
	}
	
	public static void setFlags(AtomicBoolean d, AtomicBoolean p) {
		done=d;
		pause=p;
	}
	
	public void run() {
		int i=0;
		while (i<noWords) {			
				while(pause.get()) {};
				int counter = 0;
				int Y_value = 0;
				if(words[i].getWord().equals(target)){
					for(int j = 0; j < noWords; j++ ){
						if (words[j].getWord().equals(target)){
							if (words[j].getY()>Y_value){
								Y_value = words[j].getY();
								counter = j;
							}
						}
					}
					if (words[counter].matchWord(target)) {
						System.out.println( " score! '" + target); //for checking
						score.caughtWord(target.length());	
						//FallingWord.increaseSpeed();
						break;
					}
				}		
			   i++;
			}
		
	}	
}
