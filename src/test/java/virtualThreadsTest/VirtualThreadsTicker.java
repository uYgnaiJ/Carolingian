package virtualThreadsTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class VirtualThreadsTicker {
    public static void main(String[] args) throws InterruptedException {
        int n = 13;
        List<Thread> threads = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            final int idx = i;
            Thread t = Thread.ofVirtual().name("vt-" + idx).start(() -> {
                long count = 0;
                while (true) {
                    System.out.printf("%s  idx=%d  count=%d  thread=%s%n",
                            LocalTime.now(), idx, count, Thread.currentThread().getName());
                    count++;

                }
            });
            threads.add(t);
        }

        // Keep main alive (or join all virtual threads)
        for (Thread t : threads) t.join();
    }
}
