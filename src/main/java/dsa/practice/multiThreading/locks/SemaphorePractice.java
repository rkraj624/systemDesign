package dsa.practice.multiThreading.locks;

import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * Shows how a {@link Semaphore} can be used to allow a limited number of threads
 * to enter a critical section simultaneously, enabling throttled access to a shared resource.
 */
public class SemaphorePractice {
    /**
     * Constructs multiple producer threads that all try to enter the same critical section.
     */
    public static void main(String[] args) {
        CriticalSection criticalSection = new CriticalSection();
        Thread t1 = new Thread(criticalSection::produce);
        Thread t2 = new Thread(criticalSection::produce);
        Thread t3 = new Thread(criticalSection::produce);
        Thread t4 = new Thread(criticalSection::produce);
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
     * Simple counter-guarding class that uses a semaphore to limit concurrent access.
     */
    static class CriticalSection{
        boolean isAvailable = false;

        Semaphore semaphore = new Semaphore(2);

        /**
         * Acquires the semaphore, simulates work, and always releases the permit so
         * at most two threads can be active inside this method at once.
         */
        public void produce(){
            try{
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " Acquired lock @ " + new Date());
                isAvailable = true;
                Thread.sleep(3000L);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + " released lock @ " + new Date());
            }
        }
    }
}
