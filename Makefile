sourcefiles = \
AI2.java \
Node.java \
CSPCompare.java \

classfiles = $(sourcefiles:.java=.class)

all: $(classfiles)
%.class: %.java
		javac -g . -classpath . $<

clean:
	rm -f *.class
