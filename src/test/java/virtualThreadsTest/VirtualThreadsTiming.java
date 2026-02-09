package virtualThreadsTest;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;

public class VirtualThreadsTiming {
    static final int N_THREADS = 13;
    static final long TARGET = 10_000_000_000L;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[N_THREADS];
        Instant[] finishedAt = new Instant[N_THREADS];

        CountDownLatch ready = new CountDownLatch(N_THREADS);
        CountDownLatch startGun = new CountDownLatch(1);

        for (int i = 0; i < N_THREADS; i++) {
            final int idx = i;
            threads[i] = Thread.ofVirtual().name("vt-" + idx).start(() -> {
                ready.countDown();
                try {
                    startGun.await(); // start together
                } catch (InterruptedException e) {
                    return;
                }

                long count = 0;
                while (count < TARGET) count++;

                finishedAt[idx] = Instant.now(); // record finish wall time only
            });
        }

        ready.await();
        startGun.countDown();

        for (Thread t : threads) t.join();

        for (int i = 0; i < N_THREADS; i++) {
            System.out.printf("idx=%2d finishedAt=%s%n", i, finishedAt[i]);
        }
    }
}
