package ua.training.service;

import org.junit.Test;
import ua.training.entity.Person;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class CurrencyServiceTest {

    @Test
    public void GivenCollectionWithPersonsWithCashValueLessThanThresholdWhenFilterCollectionThenFilteredCollectionIsEmpty() {
        int threshold = 10_000;
        int expectedSize = 0;
        List<Person> persons = new LinkedList<>();
        persons.add(new Person(1, "TestPerson", "TestAddress", threshold-1, "TestEducation"));
        persons.add(new Person(2, "TestPerson1", "TestAddress1", threshold-1000, "TestEducation"));
        persons = PersonService.selectPersonsWithCashMoreThen(threshold, persons);
        assertEquals(expectedSize, persons.size());
    }

    @Test
    public void GivenCollectionWithPersonsWhenFilterCollectionThenInCollectionPresentOnlyPersonsWithCashLessThanThreshold() {
        int threshold = 10_000;
        int expectedSize = 2;
        List<Person> persons = new LinkedList<>();
        persons.add(new Person(1, "TestPerson", "TestAddress", threshold-1, "TestEducation"));
        persons.add(new Person(2, "TestPerson1", "TestAddress1", threshold-1000, "TestEducation"));
        persons.add(new Person(2, "TestPerson2", "TestAddress2", threshold, "TestEducation"));
        persons.add(new Person(3, "TestPerson3", "TestAddress3", threshold+1, "TestEducation"));
        persons = PersonService.selectPersonsWithCashMoreThen(threshold, persons);
        assertEquals(expectedSize, persons.size());
    }

}