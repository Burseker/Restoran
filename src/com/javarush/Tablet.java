package com.javarush;

import com.javarush.ad.AdvertisementManager;
import com.javarush.ad.NoVideoAvailableException;
import com.javarush.kitchen.Order;
import com.javarush.kitchen.TestOrder;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    final int number;
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        try {
            Order order = new Order(this);
            processOrder(order);

            if (!order.isEmpty()) {
                return order;
            } else {
                return null;
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        }
    }

    public void createTestOrder(){
        try {
            TestOrder order = new TestOrder(this);
            processOrder(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    private void processOrder(Order order) {
        ConsoleHelper.writeMessage(order.toString());
        if (!order.isEmpty()) {
            try {
                new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
            }catch (NoVideoAvailableException e){
                logger.log(Level.INFO,"No video is available for the order " + order);
            }
            queue.offer(order);
        }
    }
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("Tablet{number=%d}", number);
    }


}
