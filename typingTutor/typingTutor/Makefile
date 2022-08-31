JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src/typingTutor
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<
CLASSES=Score.class ScoreUpdater.class WordDictionary.class FallingWord.class CatchWord.class GamePanel.class WordMover.class HungryWordMover.class TypingTutorApp.class 


CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
jar: $(CLASS_FILES)
	jar -cmf manifest.txt typingT.jar $(CLASS_FILES)
run: $(CLASS_FILES)
	java -cp bin typingTutor.TypingTutorApp
run1: $(CLASS_FILES)
	java -cp bin typingTutor.TypingTutorApp $(argument)
run2: $(CLASS_FILES)
	java -cp bin typingTutor.TypingTutorApp 30 5 words.txt

