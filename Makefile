CC = javac -classpath lib/rsyntaxarea.jar -d

build: 
	$(CC) out src/DomJ/*.java

clean: 
	rm -r out/DomJ/*.class

rebuild: clean build
