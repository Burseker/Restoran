package com.javarush.kitchen;

public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    private Dish(int duration){
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString(){
        StringBuilder sb = new StringBuilder();
        Dish[] arr = Dish.values();
        int i = 0;
        for (i = 0; i < arr.length - 1; i++) {
            sb.append(arr[i]).append(", ");
        }
        sb.append(arr[i]);
        return sb.toString();
    }

}
