package ua.training.service;

import ua.training.entity.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonService extends EntityService {
    public static List<Person> selectPersonsWithCashMoreThen(int threshold, List<Person> persons) {
        return persons.stream().filter(p -> p.getCash() >= threshold).collect(Collectors.toList());
    }
}
