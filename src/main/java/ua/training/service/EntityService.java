package ua.training.service;

import java.util.List;

public abstract class EntityService {
    public static void printListToConsole(List<?> list){
        list.forEach(System.out::println);
    }
}
