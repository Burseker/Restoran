package com.javarush;

import com.javarush.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private final static BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message.length() < 100 ? message : message.substring(0, 100));
    }

    public static String readString() throws IOException {
        return buffReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {

        List<Dish> resultList = new ArrayList<>();
        String inStr = "";

        writeMessage("Доступные для заказа блюда: " + Dish.allDishesToString());
        writeMessage("Введите название блюда: ");

        while (!(inStr = readString()).equals("exit")) {
            try {
                Dish dish = Dish.valueOf(inStr);
                resultList.add(dish);
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage("Такого блюда нет в меню");
            }
            writeMessage("Доступные для заказа блюда: " + Dish.allDishesToString());
            writeMessage("Введите название блюда: ");

        };


        return resultList;
    }
}
