package com.javarush.kitchen;

import com.javarush.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order {

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        int dishesNum = (int)(Math.random()*5);
        dishes = new ArrayList<>();

        for (int i = 0; i < dishesNum; i++) {
            int rndDish = (int)(Math.random()*Dish.values().length);
            dishes.add(Dish.values()[rndDish]);
        }
    }
}
