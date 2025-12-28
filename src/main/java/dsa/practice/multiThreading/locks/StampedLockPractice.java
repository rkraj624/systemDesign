package dsa.practice.multiThreading.locks;

import java.util.Date;
import java.util.concurrent.locks.StampedLock;


/**
 * Exercises {@link StampedLock} to illustrate how readers and writers contend
 * for the same resource, which makes it useful when you want to allow multiple
 * readers but still have exclusive writers without the overhead of full synchronized
 * blocks.
 */
public class StampedLockPractice {
    /**
     * Boots four threads so two producers can repeatedly acquire a read lock and
     * two consumers obtain the write lock, highlighting the standard reader-writer
     * use case for {@link StampedLock}.
     */
    public static void main(String[] args) {
        StampedLockSharedResource criticalSection = new StampedLockSharedResource();
        StampedLock stampedLock = new StampedLock();

        Thread t1 = new Thread(()-> criticalSection.produce(stampedLock));

        Thread t2 = new Thread(()-> criticalSection.consume(stampedLock));

        Thread t3 = new Thread(()-> criticalSection.produce(stampedLock));

        Thread t4 = new Thread(()-> criticalSection.consume(stampedLock));


        t1.setName("Thread-1");
        t2.setName("Thread-2");
        t3.setName("Thread-3");
        t4.setName("Thread-4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
    /**
     * Simulates a shared resource protected by {@link StampedLock} to show the ordering of read
     * and write lock acquisitions.
     */
    static class StampedLockSharedResource {
        boolean isAvailable = false;

        /**
         * Acquires a read lock, performs some work, and releases the lock in a finally block.
         */

        public void produce(StampedLock stampedLock)  {

            long readLock = stampedLock.readLock();
            try{
                System.out.println(Thread.currentThread().getName() + " Acquired read lock @ " + new Date());
                isAvailable = true;
                Thread.sleep(3000L);

            } catch (Exception e) {
                // throw new RuntimeException(e);
            }finally {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    // throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " released read lock @ " + new Date());
                stampedLock.unlockRead(readLock);
            }
        }

        /**
         * Acquires a write lock, updates the shared state, and releases the lock once complete.
         */
        public void consume(StampedLock stampedLock) {
            long writeLock = stampedLock.writeLock();
            try{
                System.out.println(Thread.currentThread().getName() + " Acquired write lock @ " + new Date());
                isAvailable = false;
                Thread.sleep(3000L);

            } catch (Exception e) {
                // throw new RuntimeException(e);
            }finally {

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    // throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " released write lock @ " + new Date());
                stampedLock.unlockWrite(writeLock);
            }
        }
    }
}


