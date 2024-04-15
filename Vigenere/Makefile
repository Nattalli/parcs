all: build run

clean:
	rm -f out/*.jar

out/TextChunk.class: src/TextChunk.java
	javac --release 11 -d out src/TextChunk.java

out/VigenereTask.class: src/VigenereTask.java out/TextChunk.class
	javac --release 11 -cp out -d out src/VigenereTask.java

out/VigenereCipher.class: src/VigenereCipher.java out/TextChunk.class
	javac --release 11 -cp out -d out src/VigenereCipher.java

out/VigenereTask.jar: out/VigenereTask.class
	jar cf out/VigenereTask.jar -C out VigenereTask.class -C out TextChunk.class

out/VigenereCipher.jar: out/VigenereCipher.class out/TextChunk.class
	jar cf out/VigenereCipher.jar -C out VigenereCipher.class -C out TextChunk.class

build: out/VigenereTask.jar out/VigenereCipher.jar

run: out/VigenereCipher.jar out/VigenereTask.jar
	java -cp 'out/parcs.jar:out/VigenereCipher.jar' VigenereCipher
