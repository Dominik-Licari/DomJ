CC = javac -d
TG = out
MK = $(CC) $(TG)
build: 
	$(MK) src/*.java

clean: 
	rm -r $(TG)/*.class

rebuild: clean build
