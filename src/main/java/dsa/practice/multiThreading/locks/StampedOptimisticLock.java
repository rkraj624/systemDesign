package dsa.practice.multiThreading.locks;

import java.util.Date;
import java.util.concurrent.locks.StampedLock;

/**
 * Demonstrates optimistic reads using {@link StampedLock} and a write fallback to showcase
 * how optimistic validation avoids unnecessary locking when no concurrent writer is detected.
 */
public class StampedOptimisticLock {
    /**
     * Starts two threads that alternately perform optimistic reads and an exclusive write.
     */
    public static void main(String[] args) {
        SharedSection criticalSection = new SharedSection();
        Thread t1 = new Thread(criticalSection::produce);
        Thread t2 = new Thread(criticalSection::consume);
        t1.setName("Thread-1");
        t2.setName("Thread-2");
        t1.start();
        t2.start();
    }

    /**
     * Encapsulates shared state that is guarded by a {@link StampedLock}.
     */
    private static class SharedSection {
        int a = 10;
        StampedLock stampedLock = new StampedLock();

        /**
         * Attempts an optimistic read, validates it, and either commits the update or rolls it back if
         * a concurrent write was detected.
         */
        public void produce() {
            long optimisticLock = stampedLock.tryOptimisticRead();

            try {
                System.out.println(Thread.currentThread().getName() + " Acquired optimistic lock @ " + new Date());
                if(stampedLock.validate(optimisticLock)){
                    a = 100;
                    System.out.println("Updated value successfully");
                }else{
                    a = 10;
                    System.out.println("roll back");
                }
            } catch (Exception e) {
//                throw new RuntimeException(e);
            }
        }

        /**
         * Acquires a write lock, performs the operation, and releases the lock in the finally block.
         */
        public void consume() {
            long writeLock = stampedLock.writeLock();
            try{
                System.out.println(Thread.currentThread().getName() + " Acquired write lock @ " + new Date());
                System.out.println("Performed operation");
                a = 9;

            }finally {
                stampedLock.unlockWrite(writeLock);
            }

        }
    }
}
