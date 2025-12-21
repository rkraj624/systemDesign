package dsa.practice.multiThreading.monitorLock;

public class MonitorLock {
    public synchronized void task1(){
        try{
            System.out.println("Inside task1 lock acquired");
            Thread.sleep(5000L);
            System.out.println("lock acquired released");
        }catch (Exception e){

        }
    }
    public void task2(){
        try{
            System.out.println("Before synchronized task2");
            synchronized (this){
                System.out.println("After synchronized task2");
            }
        }catch (Exception e){

        }
    }
    public void task3(){
        try{
            System.out.println("Inside task3");
        }catch (Exception e){

        }
    }
}
