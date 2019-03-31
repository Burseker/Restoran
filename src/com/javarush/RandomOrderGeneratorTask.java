package com.javarush;


import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int tabletNum = (int) (Math.random() * tablets.size());
                tablets.get(tabletNum).createTestOrder();
                Thread.sleep(interval);
            }
        } catch (InterruptedException ignore) {
        }
    }
}
