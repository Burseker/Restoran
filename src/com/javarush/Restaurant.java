package com.javarush;

import com.javarush.kitchen.Cook;
import com.javarush.kitchen.Order;
import com.javarush.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private final static int ORDER_CREATING_INTERVAL = 100;
    private final static LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();



    public static void main(String[] args) throws InterruptedException {

        Cook cook1 = new Cook("Sabastian Perrero");
        cook1.setQueue(orderQueue);
        Cook cook2 = new Cook("Roberto Goodini");
        cook2.setQueue(orderQueue);
        Thread cook1Thr = new Thread(cook1);
        Thread cook2Thr = new Thread(cook2);
        cook1Thr.setDaemon(true);
        cook2Thr.setDaemon(true);
        cook1Thr.start();
        cook2Thr.start();

        List<Tablet> tabletList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tabletList.add(tablet);
        }

        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);
        DirectorTablet directorTablet = new DirectorTablet();

        Thread customer = new Thread(new RandomOrderGeneratorTask(tabletList, ORDER_CREATING_INTERVAL));
        customer.start();

        Thread.sleep(2000);
        customer.interrupt();

        Thread.sleep(2000);
//        while (!orderQueue.isEmpty())
//            Thread.sleep(1000);

        directorTablet.printCookWorkloading();
        directorTablet.printAdvertisementProfit();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
