package com.javarush.Tests;

import com.javarush.ConsoleHelper;
import com.javarush.Tablet;
import com.javarush.kitchen.Order;

import java.io.IOException;

public class ConsoleHelperTest {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("ConsoleHelper class test");
        System.out.println("==========================================");
        System.out.println();

        try {
//            ConsoleHelper.writeMessage(ConsoleHelper.getAllDishesForOrder().toString());

            ConsoleHelper.writeMessage(new Order(new Tablet(3)).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
