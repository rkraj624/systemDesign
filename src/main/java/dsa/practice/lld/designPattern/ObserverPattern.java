package dsa.practice.lld.designPattern;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel();

        Observer s1 = new Subscriber("Ravi");
        Observer s2 = new Subscriber("Kishan");

        channel.attach(s1);
        channel.attach(s2);

        channel.uploadVideo("Design Patterns Tutorial");
    }
}

interface Observer{
    void update(String message);
}

interface Subject{
    void attach(Observer o);
    void detach(Observer o);
    void notifyObserver(String message);
}

class YouTubeChannel implements Subject{
    List<Observer> observerList = new ArrayList<>();

    @Override
    public void attach(Observer o) {
        observerList.add(o);
    }

    @Override
    public void detach(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObserver(String message) {
        for(Observer obs : observerList){
            obs.update(message);
        }
    }
    void uploadVideo(String title){
        notifyObserver("New video uploaded: " + title);
    }
}

class Subscriber implements Observer{
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}
