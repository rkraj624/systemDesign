package dsa.practice.multiThreading.producerConsumerProblem;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class SharedResource {
//    private boolean isItemAvailable = false;
    private final Queue<String> itemQueue = new ArrayBlockingQueue<>(10);
    public synchronized void addItem(){
        try {
            while(itemQueue.size() == 10){
                wait();
            }
//                isItemAvailable = true;
            itemQueue.offer("apple");
            notifyAll();
            System.out.println("Producer has produced item & also notified to all.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public synchronized void consumeItem(){
        try {
            System.out.println("Consumer thread is inside consumer method");
            while(itemQueue.isEmpty()){
                System.out.println("Consumer is waiting for Producer to produce item's notification.");
                wait();
            }
            itemQueue.poll();
            notifyAll();
            System.out.println("Consumer has consumed item.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
//        isItemAvailable = false;

    }
}
