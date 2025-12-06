package dsa.practice.graphs;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

public class ExceptionUtil {

    public static String getLimitedStackTrace(Throwable ex, int maxLines) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        // Split lines
        String[] lines = sw.toString().split("\n");
        StringBuilder limitedTrace = new StringBuilder();

        int limit = Math.min(maxLines, lines.length);
        for (int i = 0; i < limit; i++) {
            limitedTrace.append(lines[i]).append("\n");
        }

        // If truncated, add indicator
        if (lines.length > maxLines) {
            limitedTrace.append("... StackTrace truncated ...\n");
        }

        return limitedTrace.toString();
    }

    public static void main(String[] args) {
        try {

            int x = 1 / 0;
        } catch (Exception e) {
            String limitedTrace = getLimitedStackTrace(e, 5); // only 5 lines
            System.out.println(limitedTrace);
            System.out.println("_______________________________________________");
            StackTraceElement[] stackTrace = e.getStackTrace();
            System.out.println(Arrays.toString(stackTrace));

            // ✅ push to Kafka
            // kafkaProducer.send(new ProducerRecord<>("error-topic", limitedTrace));
        }
    }
}
