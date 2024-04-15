import parcs.*;
import java.util.Scanner;
import java.io.File;

public class VigenereCipher {
    public static void main(String[] args) throws Exception {
        task curTask = new task();
        curTask.addJarFile("VigenereTask.jar");

        String[] input = readInputFile(curTask.findFile("input.txt"));
        String text = input[0];
        String key = input[1];

        AMInfo info = new AMInfo(curTask, null);
        point p = info.createPoint();
        channel c = p.createChannel();
        p.execute("VigenereTask");
        c.write(new TextChunk(text));
        c.write(key);

        System.out.println("Waiting for result...");
        String result = (String) c.readObject();
        System.out.println("Result: " + result);
        curTask.end();
    }

    private static String[] readInputFile(String filename) throws Exception {
        try (Scanner sc = new Scanner(new File(filename))) {
            String text = sc.nextLine();
            String key = sc.nextLine();
            return new String[]{text, key};
        }
    }
}
