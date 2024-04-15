all: run

clean:
	rm -f out/*.jar

out/VigenereTask.jar: out/parcs.jar src/VigenereTask.java src/TextChunk.java
	@javac -cp out/parcs.jar --release 8 src/VigenereTask.java src/TextChunk.java
	@jar cf out/VigenereTask.jar -C src VigenereTask.class -C src TextChunk.class
	@rm -f src/VigenereTask.class src/TextChunk.class

out/VigenereCipher.jar: out/parcs.jar src/VigenereCipher.java src/TextChunk.java
	@javac -cp out/parcs.jar --release 8 src/VigenereCipher.java src/TextChunk.java
	@jar cf out/VigenereCipher.jar -C src VigenereCipher.class -C src TextChunk.class
	@rm -f src/VigenereCipher.class src/TextChunk.class

build: out/VigenereTask.jar out/VigenereCipher.jar

run: out/VigenereCipher.jar out/VigenereTask.jar
	@cd out && java -cp 'parcs.jar:VigenereCipher.jar' VigenereCipher
