package dsa.practice.multiThreading;

import org.springframework.lang.NonNull;

import java.util.concurrent.*;

public class ThreadPollExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(2), new CustomThreadFactory(),new CustomThreadRejectedExecutionHandler());
        try {
            for (int i = 1 ; i <= 8 ; i++){
                threadPoolExecutor.submit(()->{
                    try{
                        Thread.sleep(5000L);
                        System.out.println("Thread executed by : " +Thread.currentThread().getName() );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}

class CustomThreadFactory implements ThreadFactory{
    @Override
    public Thread newThread( @NonNull Runnable r) {
        Thread thread =  new Thread(r);
        thread.setDaemon(false);
        thread.setPriority(Thread.NORM_PRIORITY);
        return thread;
    }
}

class CustomThreadRejectedExecutionHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Thread is being rejected " + r.toString());
    }
}


