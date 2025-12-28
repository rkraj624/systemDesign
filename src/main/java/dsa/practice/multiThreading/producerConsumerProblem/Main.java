package dsa.practice.multiThreading.producerConsumerProblem;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread producer = new Thread(()->{
            try{
                for(int i = 0; i < 6; i++){
                    sharedResource.addItem();
                    System.out.println("Producing item : "+ i);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumer = new Thread(()->{
            try{
                for(int i = 0; i < 6; i++){
                    sharedResource.consumeItem();
//                    System.out.println("Producing item : "+ i);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }
}
