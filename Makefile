CC = javac -classpath lib/rsyntaxarea.jar -d

build: 
	$(CC) out src/DomJ/*.java

clean: 
	rm -r out/*.class

rebuild: clean build
