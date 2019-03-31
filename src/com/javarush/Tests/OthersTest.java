package com.javarush.Tests;

import com.javarush.ConsoleHelper;
import com.javarush.DirectorTablet;
import com.javarush.Tablet;
import com.javarush.ad.Advertisement;
import com.javarush.ad.AdvertisementStorage;
import com.javarush.ad.StatisticAdvertisementManager;
import com.javarush.kitchen.Cook;
import com.javarush.kitchen.Dish;
import com.javarush.statistic.StatisticManager;
import com.javarush.statistic.event.CookedOrderEventDataRow;
import com.javarush.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OthersTest {

    Cook cook1 = new Cook("Sabastian Perrero");
    Cook cook2 = new Cook("Roberto Goodini");
    Cook cook3 = new Cook("Pablo Picasso");
    Tablet tablet1 = new Tablet(1);
    Tablet tablet2 = new Tablet(2);
    Tablet tablet3 = new Tablet(3);

    public static void main(String[] args) {
//        System.out.println("==========================================");
//        System.out.println("Others test");
//        System.out.println("==========================================");
//        System.out.println();

        OthersTest inst = new OthersTest();
        //Advertisement adv1 = new Advertisement("Trolili lololo", "Simple name", 10, 1, 3);

        inst.statisticTest();
    }

    public void statisticTest() {
        System.out.println("==========================================");
        System.out.println("Statistic test");
        System.out.println("==========================================");
        System.out.println();

        DirectorTablet directorTablet = new DirectorTablet();
        StatisticManager statisticManager = StatisticManager.getInstance();
        CookedOrderEventDataRow cookedOrderEventDataRow;
        VideoSelectedEventDataRow videoSelectedEventDataRow;
        List<Dish> dishes = new ArrayList<>();



        dishes.add(Dish.Fish);
        dishes.add(Dish.Soup);
        dishes.add(Dish.Steak);
        dishes.add(Dish.Steak);
        cookedOrderEventDataRow = new CookedOrderEventDataRow(tablet1.toString(), cook1.toString(), 30*60,
                dishes, new Date());
        statisticManager.register(cookedOrderEventDataRow);
        cookedOrderEventDataRow = new CookedOrderEventDataRow(tablet1.toString(), cook2.toString(), 30*60,
                dishes, new Date());
        statisticManager.register(cookedOrderEventDataRow);
        cookedOrderEventDataRow = new CookedOrderEventDataRow(tablet1.toString(), cook3.toString(), 30*60,
                dishes, new Date());
        statisticManager.register(cookedOrderEventDataRow);

        cookedOrderEventDataRow = new CookedOrderEventDataRow(tablet1.toString(), cook1.toString(), 30*60,
                dishes, new Date(1245990000000L));
        statisticManager.register(cookedOrderEventDataRow);
        cookedOrderEventDataRow = new CookedOrderEventDataRow(tablet1.toString(), cook3.toString(), 30*60,
                dishes, new Date(1245990000000L));
        statisticManager.register(cookedOrderEventDataRow);

        cookedOrderEventDataRow = new CookedOrderEventDataRow(tablet1.toString(), cook1.toString(), 10*60,
                dishes, new Date(1545990000000L));
        statisticManager.register(cookedOrderEventDataRow);
        cookedOrderEventDataRow = new CookedOrderEventDataRow(tablet1.toString(), cook2.toString(), 61,
                dishes, new Date(1545990000000L));
        statisticManager.register(cookedOrderEventDataRow);

        directorTablet.printCookWorkloading();



        videoSelectedEventDataRow = new VideoSelectedEventDataRow(null, 400, 10*60, new Date());
        statisticManager.register(videoSelectedEventDataRow);
        videoSelectedEventDataRow = new VideoSelectedEventDataRow(null, 200, 10*60, new Date());
        statisticManager.register(videoSelectedEventDataRow);
        videoSelectedEventDataRow = new VideoSelectedEventDataRow(null, 222, 10*60, new Date());
        statisticManager.register(videoSelectedEventDataRow);
        videoSelectedEventDataRow = new VideoSelectedEventDataRow(null, 4500, 10*60, new Date(10000000));
        statisticManager.register(videoSelectedEventDataRow);
        videoSelectedEventDataRow = new VideoSelectedEventDataRow(null, 450, 10*60, new Date(1245990000000L));
        statisticManager.register(videoSelectedEventDataRow);

        directorTablet.printAdvertisementProfit();

        System.out.println("========= Active list =========");
        directorTablet.printActiveVideoSet();
        System.out.println("========= Expired list =========");
        directorTablet.printArchivedVideoSet();


        StatisticAdvertisementManager.getInstance().getActiveVideoSet().forEach(Advertisement::revalidate);
        StatisticAdvertisementManager.getInstance().getActiveVideoSet().forEach(Advertisement::revalidate);
        StatisticAdvertisementManager.getInstance().getActiveVideoSet().forEach(Advertisement::revalidate);

        System.out.println("========= Active list =========");
        directorTablet.printActiveVideoSet();
        System.out.println("========= Expired list =========");
        directorTablet.printArchivedVideoSet();
    }
}
