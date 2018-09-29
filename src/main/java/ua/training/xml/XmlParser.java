package ua.training.xml;

import ua.training.entity.Person;

import java.util.List;

public interface XmlParser {
    List<Person> parseToList(String xmlPath);
}
