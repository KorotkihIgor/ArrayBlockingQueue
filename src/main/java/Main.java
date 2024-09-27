import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static ArrayBlockingQueue<String> queueA = new ArrayBlockingQueue<>(100);
    public static ArrayBlockingQueue<String> queueB = new ArrayBlockingQueue<>(100);
    public static ArrayBlockingQueue<String> queueC = new ArrayBlockingQueue<>(100);
    public static Thread charGenerator;

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        charGenerator = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                String text = generateText("abc", 100_000);
                try {
                    queueA.put(text);
                    queueB.put(text);
                    queueC.put(text);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        charGenerator.start();
        Thread a = MethodsClass.maxChar(queueA, 'a');
        Thread b = MethodsClass.maxChar(queueB, 'b');
        Thread c = MethodsClass.maxChar(queueC, 'c');

        a.start();
        b.start();
        c.start();

        a.join();
        b.join();
        c.join();
    }
}
