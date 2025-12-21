package dsa.practice.multiThreading.monitorLock;

public class Main {
    public static void main(String[] args) {
        MonitorLock monitorLock = new MonitorLock();
        Thread t1 = new Thread(()->{monitorLock.task1();});
        Thread t2 = new Thread(()->{monitorLock.task2();});
        Thread t3 = new Thread(()->{monitorLock.task3();});

        t1.start(); t2.start(); t3.start();
    }
}
