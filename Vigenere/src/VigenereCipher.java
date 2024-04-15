import parcs.*;
import java.util.Scanner;
import java.io.File;

public class VigenereCipher {
    public static void main(String[] args) throws Exception {
        task curTask = new task();
        curTask.addJarFile("VigenereTask.jar");

        String[] input = readInputFile(curTask.findFile("input4.txt"));
        String text = input[0];
        String key = input[1];

        int numWorkers = 2;
        int chunkSize = text.length() / numWorkers;
        AMInfo info = new AMInfo(curTask, null);

        StringBuilder finalResult = new StringBuilder();
        for (int i = 0; i < numWorkers; i++) {
            int start = i * chunkSize;
            int end = (i == numWorkers - 1) ? text.length() : start + chunkSize;
            String part = text.substring(start, end);

            point p = info.createPoint();
            channel c = p.createChannel();
            p.execute("VigenereTask");
            c.write(new TextChunk(part));
            c.write(key);

            System.out.println("Waiting for result from worker " + i + "...");
            String result = (String) c.readObject();
            finalResult.append(result);
        }

        System.out.println("Final encrypted result: " + finalResult.toString());
        curTask.end();
    }

    private static String[] readInputFile(String filename) throws Exception {
        Scanner sc = new Scanner(new File(filename));
        String text = sc.nextLine();
        String key = sc.nextLine();
        sc.close();
        return new String[]{text, key};
    }
}
