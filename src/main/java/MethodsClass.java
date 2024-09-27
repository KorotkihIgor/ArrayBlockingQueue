import java.util.concurrent.ArrayBlockingQueue;

public class MethodsClass {
    public static int maxCharCount(ArrayBlockingQueue<String> queue, char symbol) {
        int count = 0;
        int max = 0;
        String text;
        try {
            text = queue.take();
            for (char c : text.toCharArray()) {
                if (c == symbol) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return max;
    }

    public static Thread maxChar(ArrayBlockingQueue<String> queue, char symbol) {
        return new Thread(() -> {
            int max = maxCharCount(queue, symbol);
            System.out.println("Символ " + symbol + " = " + max);
        });
    }
}
