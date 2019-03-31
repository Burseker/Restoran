package com.javarush;

import com.javarush.ad.Advertisement;
import com.javarush.ad.StatisticAdvertisementManager;
import com.javarush.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    SimpleDateFormat localDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    StatisticManager statisticManager = StatisticManager.getInstance();

    public void printAdvertisementProfit(){
        Map<Date, Long> advertisementProfit = new TreeMap<>(Collections.reverseOrder());
        advertisementProfit.putAll(statisticManager.getAdvertisementProfit());

        long commonProfit = 0;
        for(Map.Entry<Date, Long> elem: advertisementProfit.entrySet()){
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH,"%s - %.2f", localDateFormat.format(elem.getKey()), (double)elem.getValue()/100));
            commonProfit += elem.getValue();
        }

        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH,"Total - %.2f", (double)commonProfit/100));
    }

    public void printCookWorkloading(){
        Map<Date, Map<String, Integer>> statisticCookWork = new TreeMap<>(Collections.reverseOrder());
        statisticCookWork.putAll(statisticManager.getCookWorkloading());

        for(Map.Entry<Date, Map<String, Integer>> dayElem : statisticCookWork.entrySet()){
            ConsoleHelper.writeMessage(localDateFormat.format(dayElem.getKey()));
            dayElem.getValue().forEach((K, V) -> ConsoleHelper.writeMessage(String.format("%s - %d min", K, V%60 == 0 ? V/60 : V/60+1)));

            ConsoleHelper.writeMessage("");
        }

    }

    public void printActiveVideoSet(){
        StatisticAdvertisementManager statAdManage = StatisticAdvertisementManager.getInstance();
        List<Advertisement> adList = statAdManage.getActiveVideoSet();
        adList.sort(Comparator.comparing( V -> V.getName().toLowerCase()));

        adList.forEach( (V) -> ConsoleHelper.writeMessage(V.getName() + " - " + V.getHits()) );
    }

    public void printArchivedVideoSet(){
        StatisticAdvertisementManager statAdManage = StatisticAdvertisementManager.getInstance();
        List<Advertisement> adList = statAdManage.getArchivedVideoSet();
        adList.sort(Comparator.comparing( V -> V.getName().toLowerCase()));

        adList.forEach( (V) -> ConsoleHelper.writeMessage(V.getName()) );
    }
}
