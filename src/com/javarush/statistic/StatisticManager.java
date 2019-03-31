package com.javarush.statistic;

import com.javarush.ad.Advertisement;
import com.javarush.ad.AdvertisementManager;
import com.javarush.ad.AdvertisementStorage;
import com.javarush.kitchen.Cook;
import com.javarush.statistic.event.CookedOrderEventDataRow;
import com.javarush.statistic.event.EventDataRow;
import com.javarush.statistic.event.EventType;
import com.javarush.statistic.event.VideoSelectedEventDataRow;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticManager {
    private static volatile StatisticManager instance;
    private StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager(){
    }

    public static StatisticManager getInstance(){
        if (instance == null) {
            synchronized (AdvertisementManager.class) {
                if (instance == null)
                    instance = new StatisticManager();
            }
        }
        return instance;
    }

    public Map<Date, Long> getAdvertisementProfit() {
        Map<Date, Long> result = new HashMap<>();
        List<EventDataRow> adverStorage = statisticStorage.storage.get(EventType.SELECTED_VIDEOS);

        for (EventDataRow elem : adverStorage) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(elem.getDate());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            result.merge(calendar.getTime(), ((VideoSelectedEventDataRow) elem).getAmount(), Long::sum);
        }
        return result;
    }

    public Map<Date, Map<String, Integer>> getCookWorkloading(){
        Map<Date, Map<String, Integer>> result = new HashMap<>();

        List<EventDataRow> adverStorage = statisticStorage.storage.get(EventType.COOKED_ORDER);


        for (EventDataRow elem : adverStorage) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(elem.getDate());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            CookedOrderEventDataRow cookedOrderEventElem = (CookedOrderEventDataRow)elem;

            if(!result.containsKey(calendar.getTime())){
                TreeMap<String, Integer> dayMap = new TreeMap<>();
                dayMap.put(cookedOrderEventElem.getCookName(), cookedOrderEventElem.getTime());
                result.put(calendar.getTime(), dayMap);
            } else {
                Map<String, Integer> dayMap = result.get(calendar.getTime());
                dayMap.merge(cookedOrderEventElem.getCookName(), cookedOrderEventElem.getTime(), Integer::sum);
            }
        }

        return result;
    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    private class StatisticStorage{
        Map<EventType, List<EventDataRow>> storage;

        public StatisticStorage() {
            storage = new HashMap<>();
            for (EventType elem : EventType.values()){
                storage.put(elem, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
        }
    }
}
