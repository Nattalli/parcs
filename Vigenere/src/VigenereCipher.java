import parcs.*;
import java.util.Scanner;
import java.io.File;

public class VigenereCipher {
    public static void main(String[] args) throws Exception {
        task curTask = new task();
        curTask.addJarFile("VigenereTask.jar");

        String[] input = readInputFile(curTask.findFile("input2.txt"));
        String text = input[0];
        String key = input[1];

        int numWorkers = 6;
        int chunkSize = text.length() / numWorkers;

        AMInfo info = new AMInfo(curTask, null);
        StringBuilder finalResult = new StringBuilder();

        point[] points = new point[numWorkers];
        channel[] channels = new channel[numWorkers];

        for (int i = 0; i < numWorkers; i++) {
            points[i] = info.createPoint();
            channels[i] = points[i].createChannel();
            points[i].execute("VigenereTask");

            int start = i * chunkSize;
            int end = (i == numWorkers - 1) ? text.length() : start + chunkSize;
            String part = text.substring(start, end);

            channels[i].write(new TextChunk(part));
            channels[i].write(key);
        }

        for (int i = 0; i < numWorkers; i++) {
            System.out.println("Waiting for result from worker " + i + "...");
            String result = (String) channels[i].readObject();
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
