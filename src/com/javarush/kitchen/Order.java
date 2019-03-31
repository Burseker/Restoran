package com.javarush.kitchen;

import com.javarush.ConsoleHelper;
import com.javarush.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;
    private int totalCookingTime;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
        totalCookingTime = 0;
        for(Dish elem : dishes)
            totalCookingTime += elem.getDuration();
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    /**
     *
     * @return cooking time in minutes
     */
    public int getTotalCookingTime(){
        return totalCookingTime;
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    @Override
    public String toString() {
        if(dishes.size() == 0) {
            return "";
        } else {
            return String.format("Your order: %s of %s", dishes.toString(), tablet.toString());
        }
    }
}
