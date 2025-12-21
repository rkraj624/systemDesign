package dsa.practice.lld.bookMyShow.lock;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class LockManager {
    private final ConcurrentMap<String, Lock> showLocks = new ConcurrentHashMap<>();

    public <T> T withShowLock(String showId, Supplier<T> supplier) {
        Objects.requireNonNull(showId);
        Objects.requireNonNull(supplier);

        Lock lock = showLocks.computeIfAbsent(showId, k -> new ReentrantLock());
        lock.lock();
        try {
            return supplier.get();
        } finally {
            lock.unlock();
        }
    }

    public void withShowLock(String showId, Runnable runnable) {
        withShowLock(showId, () -> {
            runnable.run();
            return null;
        });
    }
}
