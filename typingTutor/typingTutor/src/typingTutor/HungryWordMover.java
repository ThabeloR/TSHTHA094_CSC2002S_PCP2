package typingTutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class HungryWordMover extends Thread {
	private FallingWord myWord;
	private AtomicBoolean done;
	private AtomicBoolean pause; 
	private Score score;
	private FallingWord[] words;
	CountDownLatch startLatch; //so all can start at once
	
	HungryWordMover( FallingWord word) {
		myWord = word;
	}
	
	HungryWordMover( FallingWord word,WordDictionary dict, Score score,
			CountDownLatch startLatch, AtomicBoolean d, AtomicBoolean p) {
		this(word);
		this.startLatch = startLatch;
		this.score=score;
		this.done=d;
		this.pause=p;
	}

    HungryWordMover( FallingWord word,WordDictionary dict, Score score,
    CountDownLatch startLatch, AtomicBoolean d, AtomicBoolean p,FallingWord[] words) {
        this(word);
        this.startLatch = startLatch;
        this.score=score;
        this.done=d;
        this.pause=p;
		this.words = words;
    }
	
	public void run() {

		//System.out.println(myWord.getWord() + " falling speed = " + myWord.getSpeed());
		try {
			System.out.println(myWord.getWord() + " waiting to start " );
			startLatch.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //wait for other threads to start
		System.out.println(myWord.getWord() + " started" );
		while (!done.get()) {				
			//animate the word
			//System.out.println("The hungry word is " + TypingTutorApp.hungrywWord.getWord()+ " is the same as "+myWord.getWord() );
			while (!myWord.droppedx() && !done.get()) {
				if (myWord.equals(TypingTutorApp.hungrywWord)){
				    myWord.dropx(10);
					for (int i = 0 ; i <words.length; i++){
						if(!words[i].equals(TypingTutorApp.hungrywWord)){
							if (words[i].getY() == myWord.getY()){
								//System.out.println("The hungry word is at " + myWord.getY()+ "the other at"+ words[i].getY());
										if( words[i].getX() >= myWord.getX()-myWord.getWord().length() && words[i].getX() <= myWord.getX()+ myWord.getWord().length() ){
											score.missedWord();
											words[i].resetWord();
										}

							}
						}
					}
				}
					try {
						sleep(myWord.getSpeed());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};		
					while(pause.get()&&!done.get()) {};
			}
			if (!done.get() && myWord.droppedx()) {
				score.missedWord();
				myWord.resetWordx();
			}
			myWord.resetWordx();
		}
	}
	
}