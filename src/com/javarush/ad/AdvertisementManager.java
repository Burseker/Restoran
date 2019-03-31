package com.javarush.ad;

import com.javarush.ConsoleHelper;
import com.javarush.statistic.StatisticManager;
import com.javarush.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public List<Advertisement> chooseAdvertisement(){
        List<Advertisement> advertisementsList = new ArrayList<>();
        for (Advertisement elem : storage.list()){
            if(elem.getHits() > 0)
                advertisementsList.add(elem);
        }

        advertisementsList = chooseAdvertisementRecursive(advertisementsList);

        return advertisementsList;
    }

    private List<Advertisement> chooseAdvertisementRecursive( List<Advertisement> arr ){
        int overalTimeSeconds = 0;

        for (Advertisement elem : arr){
            overalTimeSeconds += elem.getDuration();
        }

        if (overalTimeSeconds <= timeSeconds) {
            return arr;
        } else if (arr.size() == 1) {
            return null;
        } else {
            List<Advertisement> mtArr = new ArrayList<>(arr);
            mtArr.remove(0);
            mtArr = chooseAdvertisementRecursive(mtArr);

            for (int i = 1; i < arr.size(); i++) {
                List<Advertisement> tArr = new ArrayList<>(arr);
                tArr.remove(i);
                tArr = chooseAdvertisementRecursive(tArr);

                if (tArr != null) {
                    long tArrPrice = tArr.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();
                    if (mtArr == null) {
                        mtArr = tArr;
                        continue;
                    }
                    long mtArrPrice = mtArr.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();
                    if (tArrPrice > mtArrPrice) {
                        mtArr = tArr;
                    } else if (tArrPrice == mtArrPrice) {
                        int mtArrTime = mtArr.stream().mapToInt(Advertisement::getDuration).sum();
                        int tArrTime = tArr.stream().mapToInt(Advertisement::getDuration).sum();
                        if (tArrTime > mtArrTime) {
                            mtArr = tArr;
                        } else if (tArrTime == mtArrTime) {
                            if (tArr.size() < mtArr.size()) {
                                mtArr = tArr;
                            }
                        }
                    }
                }
            }
            return mtArr;
        }
    }


    public void processVideos(){
        //ConsoleHelper.writeMessage("calling processVideos method");
//        List<Advertisement> advertisementsList = new ArrayList<Advertisement>(storage.list());
        List<Advertisement> advertisementsList = chooseAdvertisement();
        
        if(advertisementsList == null || advertisementsList.isEmpty()) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        }

        Collections.sort(advertisementsList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int res1 = (int) (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
                if(res1 != 0)
                    return res1;
                else
                    return (int) ((double)o1.getAmountPerOneDisplaying()/o1.getDuration() - (double)o2.getAmountPerOneDisplaying()/o2.getDuration());
            }
        });

        StatisticManager statisticManager = StatisticManager.getInstance();
        long amount = advertisementsList.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();
        int totalDuration = advertisementsList.stream().mapToInt(Advertisement::getDuration).sum();
        statisticManager.register(new VideoSelectedEventDataRow(advertisementsList, amount, totalDuration));


        for(Advertisement elem : advertisementsList){
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
                    elem.getName(), elem.getAmountPerOneDisplaying(), elem.getAmountPerOneDisplaying()*1000/elem.getDuration()));
            elem.revalidate();
        }
    }
}
