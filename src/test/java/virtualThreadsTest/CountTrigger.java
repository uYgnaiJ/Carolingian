package virtualThreadsTest;

import java.util.concurrent.atomic.AtomicBoolean;

public final class CountTrigger {
    public final long countAt;
    public final Runnable action;
    public final boolean pauseFirst;

    private final AtomicBoolean fired = new AtomicBoolean(false);

    public CountTrigger(long countAt, Runnable action, boolean pauseFirst) {
        this.countAt = countAt;
        this.action = action;
        this.pauseFirst = pauseFirst;
    }

    boolean tryFire(long currentCount, Runnable pauseAndWaitIfNeeded) {
        if (currentCount != countAt) return false;
        if (!fired.compareAndSet(false, true)) return false; // run once

        if (pauseFirst) pauseAndWaitIfNeeded.run();
        action.run();
        return true;
    }
}