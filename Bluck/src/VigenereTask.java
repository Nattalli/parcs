import parcs.*;

public class VigenereTask implements AM {
    public void run(AMInfo info) {
        TextChunk chunk = (TextChunk) info.parent.readObject();
        String key = (String) info.parent.readObject();
        
        String encryptedText = encryptVigenere(chunk.getTextSegment(), key);
        info.parent.write(encryptedText);
    }

    private String encryptVigenere(String text, String key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();
        
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            result.append((char)((c + key.charAt(j) - 2 * 'A') % 26 + 'A'));
            j = ++j % key.length();
        }
        return result.toString();
    }
}
