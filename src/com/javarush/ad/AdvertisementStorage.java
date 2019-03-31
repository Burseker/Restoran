package com.javarush.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static volatile AdvertisementStorage instance;
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage(){
        Object someContent = new Object();
//        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min
        videos.add(new Advertisement(someContent, "first Video", 50, 1, 3 * 60)); // 3 min
        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60)); //15 min
//        videos.add(new Advertisement(someContent, "Second10_1 Video", 100, 10, 3 * 60)); //15 min
//        videos.add(new Advertisement(someContent, "Second10_2 Video", 100, 10, 3 * 60)); //15 min
//        videos.add(new Advertisement(someContent, "Second10_3 Video", 100, 10, 3 * 60)); //15 min
//        videos.add(new Advertisement(someContent, "Second10_4 Video", 100, 10, 3 * 60)); //15 min
//        videos.add(new Advertisement(someContent, "Second10_5 Video", 100, 10, 3 * 60)); //15 min
//        videos.add(new Advertisement(someContent, "Second10_6 Video", 100, 10, 4 * 60)); //15 min
//        videos.add(new Advertisement(someContent, "Second10_7 Video", 100, 10, 3 * 60)); //15 min
//        videos.add(new Advertisement(someContent, "Second10_8 Video", 100, 10, 4 * 60)); //15 min
//        videos.add(new Advertisement(someContent, "Second10_9 Video", 200, 10, 6 * 60)); //15 min
        videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60)); //10 min
//        videos.add(new Advertisement(someContent, "ААААА", 400, 2, 10 * 60)); //10 min
//        videos.add(new Advertisement(someContent, "ВААА", 400, 3, 10 * 60)); //10 min
    }

    public static AdvertisementStorage getInstance(){
        if(instance == null)
            synchronized (AdvertisementStorage.class) {
                if (instance == null) {
                    instance = new AdvertisementStorage();
                }
            }
        return instance;
    }

    public List<Advertisement> list(){
        return videos;
    }

    public void add(Advertisement advertisement){
        videos.add(advertisement);
    }
}
