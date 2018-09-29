package ua.training.service;

import ua.training.entity.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonService {

    public static List<Person> selectPersonsWithCashMoreThen(int threshold, List<Person> persons ) {
        return persons.stream().filter(p -> p.getCash() >= threshold).collect(Collectors.toList());
    }

    public static void printListToConsole(List<Person> persons) {
        for (Person person: persons) {
            System.out.println(person);
        }
    }

}
