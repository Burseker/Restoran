package com.javarush.Tests;

import com.javarush.ConsoleHelper;
import com.javarush.ad.Advertisement;
import com.javarush.ad.AdvertisementManager;

public class ChooseAdvertisementTest {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("ConsoleHelper class test");
        System.out.println("==========================================");
        System.out.println();

        AdvertisementManager advertisementManager = new AdvertisementManager(17*60);
        for(Advertisement elem : advertisementManager.chooseAdvertisement()){
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
                    elem.getName(), elem.getAmountPerOneDisplaying(), elem.getAmountPerOneDisplaying()*1000/elem.getDuration()));
            elem.revalidate();
        }

        System.out.println("==========================================");
        System.out.println("==========================================");
        advertisementManager = new AdvertisementManager(17*60);
        for(Advertisement elem : advertisementManager.chooseAdvertisement()){
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
                    elem.getName(), elem.getAmountPerOneDisplaying(), elem.getAmountPerOneDisplaying()*1000/elem.getDuration()));
            elem.revalidate();
        }
//
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//        advertisementManager = new AdvertisementManager(30*60);
//        for(Advertisement elem : advertisementManager.chooseAdvertisement()){
//            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
//                    elem.getName(), elem.getAmountPerOneDisplaying(), elem.getAmountPerOneDisplaying()*1000/elem.getDuration()));
//            //elem.revalidate();
//        }
//
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//        advertisementManager = new AdvertisementManager(15*60);
//        for(Advertisement elem : advertisementManager.chooseAdvertisement()){
//            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
//                    elem.getName(), elem.getAmountPerOneDisplaying(), elem.getAmountPerOneDisplaying()*1000/elem.getDuration()));
//            //elem.revalidate();
//        }
//
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//        advertisementManager = new AdvertisementManager(10*60);
//        for(Advertisement elem : advertisementManager.chooseAdvertisement()){
//            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
//                    elem.getName(), elem.getAmountPerOneDisplaying(), elem.getAmountPerOneDisplaying()*1000/elem.getDuration()));
//            //elem.revalidate();
//        }
//
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//        advertisementManager = new AdvertisementManager(1*60);
//        for(Advertisement elem : advertisementManager.chooseAdvertisement()){
//            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
//                    elem.getName(), elem.getAmountPerOneDisplaying(), elem.getAmountPerOneDisplaying()*1000/elem.getDuration()));
//            //elem.revalidate();
//        }
    }
}
