package dsa.practice.multiThreading.producerConsumerProblem;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread producer = new Thread(()->{
            try{
                Thread.sleep(3000L);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            sharedResource.addItem();
        });
        Thread consumer = new Thread(sharedResource::consumeItem);

        producer.start();
        consumer.start();
    }
}
