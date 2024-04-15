import java.io.Serializable;

public class TextChunk implements Serializable {
    private String textSegment;

    public TextChunk(String textSegment) {
        this.textSegment = textSegment;
    }

    public String getTextSegment() {
        return textSegment;
    }
}
