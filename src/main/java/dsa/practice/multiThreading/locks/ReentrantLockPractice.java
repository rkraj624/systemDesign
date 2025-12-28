package dsa.practice.multiThreading.locks;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Shows how {@link ReentrantLock} enforces exclusive access to a critical
 * section even when several threads execute the same method on the same
 * resource instance.
 * <p>
 * This example spins up two threads that call {@link CriticalSection#produce},
 * and each thread must wait until the previous holder releases the lock before
 * entering the protected section. Useful when teaching or diagnosing basic lock
 * fairness and mutual exclusion guarantees (note that this example builds a
 * fair lock via {@code new ReentrantLock(true)}).
 * </p>
 */
public class ReentrantLockPractice {
    public static void main(String[] args) {
        CriticalSection criticalSection = new CriticalSection();
        Thread t1 = new Thread(criticalSection::produce);
        Thread t2 = new Thread(criticalSection::produce);
        t1.setName("Thread-1");
        t2.setName("Thread-2");
        t1.start();
        t2.start();
    }
    static class CriticalSection{
        boolean isAvailable = false;
        /**
         * Using the fair constructor ensures waiting threads acquire the lock
         * <p>
         * roughly in the order they requested it, preventing starvation.
         * </p>
         */
        ReentrantLock reentrantLock = new ReentrantLock(true);

        public void produce(){
            try{
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + " Acquired lock @ " + new Date());
                isAvailable = true;
                Thread.sleep(3000L);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                reentrantLock.unlock();
                System.out.println(Thread.currentThread().getName() + " released lock @ " + new Date());
            }
        }
    }
}


