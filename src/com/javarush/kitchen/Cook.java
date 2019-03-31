package com.javarush.kitchen;

import com.javarush.ConsoleHelper;
import com.javarush.statistic.StatisticManager;
import com.javarush.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable{
    private final String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            Order order = queue.poll();
            if (order != null) {
                startCookingOrder(order);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignore) { }
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order){
        busy = true;
        //Order order = (Order)arg;
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), this.name, order.getTotalCookingTime()*60, order.getDishes()));
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");

        try {
            Thread.sleep(order.getTotalCookingTime()*10);
        } catch (InterruptedException e) {
        }

        setChanged();
        notifyObservers(order);
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;

        if(!(obj instanceof Cook)) return false;
        Cook cook = (Cook)obj;

        return this.name.equals(cook.name);
    }
}
