package com.javarush.ad;

public class Advertisement {
    private Object content; //видео
    private String name; //имя/название
    private long initialAmount; //начальная сумма, стоимость рекламы в копейках. Используем long, чтобы избежать проблем с округлением
    private int hits; //количество оплаченных показов
    private int duration; //продолжительность в секундах

    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        amountPerOneDisplaying = hits == 0 ? 0 : initialAmount / hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getInitialAmount() {
        return initialAmount;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public int getHits() {
        return hits;
    }

    public void revalidate(){
        if(hits < 1)
            throw new  UnsupportedOperationException();
        else
            hits--;
    }
}
