# Thabelo Tshikalange
# 24 August 2022

.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
JAVAC=/usr/bin/typingTutor/javac
JAVA=usr/bin/typingTutor/java

$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<
	
CLASSES=GamePanel.class  CatchWorld.class FallingWord.class  Score.class ScoreUpdater.class\
	 WordDictionary.class WordMover.class HungryWordMover.class TypingTutorApp.class 

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)
	

default: $(CLASS_FILES)
	
run: $(CLASS_FILES)	$(JAVA) -cp $(BINDIR) TypingTutorApp 
	
clean:
	rm $(BINDIR)/*.class
	