package ua.training.xml.dom.util;

import ua.training.entity.Person;

import java.util.List;

public interface XmlParser {
    List<Person> parseToList(String xmlPath);
}
