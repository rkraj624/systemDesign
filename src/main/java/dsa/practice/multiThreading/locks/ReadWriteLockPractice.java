package dsa.practice.multiThreading.locks;

import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Demonstrates basic usage of {@link ReadWriteLock} where multiple readers can
 * access a shared resource concurrently while writers obtain exclusive access.
 * <p>
 * The example launches two reader threads that obtain the shared resource in
 * read mode and a writer thread that obtains the write lock. Designers can run
 * this class when they want to compare throughput of read-heavy vs write-heavy
 * workloads or to understand how read/write locks differ from simple mutual
 * exclusion.
 * </p>
 */
public class ReadWriteLockPractice {

    public static void main(String[] args) {
        SharedResource criticalSection = new SharedResource();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Thread t1 = new Thread(()-> criticalSection.produce(readWriteLock));

        Thread t2 = new Thread(()-> criticalSection.consume(readWriteLock));

        Thread t3 = new Thread(()-> criticalSection.produce(readWriteLock));

        t1.setName("Thread-1");
        t2.setName("Thread-2");
        t3.setName("Thread-3");
        t1.start();
        t2.start();
        t3.start();
    }
    static class SharedResource {
        boolean isAvailable = false;

        public void produce(ReadWriteLock readWriteLock)  {
            try{
                readWriteLock.readLock().lock();
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
                readWriteLock.readLock().unlock();
            }
        }

        public void consume(ReadWriteLock readWriteLock) {
            try{
                readWriteLock.writeLock().lock();
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
                readWriteLock.writeLock().unlock();
            }
        }
    }
}


