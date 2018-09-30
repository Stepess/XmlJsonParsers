package ua.training.xml;

import ua.training.entity.Person;
import ua.training.entity.PersonBuilder;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class StaxParser implements XmlParser {
    @Override
    public List<Person> parseToList(String xmlPath) {
        List<Person> persons = new LinkedList<>();
        try (StaxStreamProcessor processor = new StaxStreamProcessor(new FileInputStream(new File(xmlPath)))) {
            while (processor.doUntil(XMLEvent.START_ELEMENT, "person")) {
                PersonBuilder builder = new PersonBuilder();
                builder.setId(Integer.parseInt(processor.getAttribute("id")))
                        .setName(processor.getTextByTagName("name"))
                        .setAddress(processor.getTextByTagName("address"))
                        .setCash(Integer.parseInt(processor.getTextByTagName("cash")));
                persons.add(builder.createPerson());
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return persons;
    }
}