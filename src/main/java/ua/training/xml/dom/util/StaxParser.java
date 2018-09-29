package ua.training.xml.dom.util;

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



/*/



/*List<Person> persons = new LinkedList<>();
        PersonBuilder builder = new PersonBuilder();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(xmlPath));
            int event = xmlStreamReader.getEventType();
            while (true) {
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (xmlStreamReader.getLocalName().equals("person")) {

                            builder.setId(Integer.parseInt(xmlStreamReader.getAttributeValue(0)));
                        } else if (xmlStreamReader.getLocalName().equals("name")) {
                            bName = true;
                        } else if (xmlStreamReader.getLocalName().equals("address")) {
                            bAddress = true;
                        } else if (xmlStreamReader.getLocalName().equals("cash")) {
                            bCash = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (bName) {
                            builder.setName(xmlStreamReader.getText());
                            bName = false;
                        } else if (bAddress) {
                            builder.setAddress(xmlStreamReader.getText());
                            bAddress = false;
                        } else if (bCash) {
                            builder.setCash(Integer.parseInt(xmlStreamReader.getText()));
                            bCash = false;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (xmlStreamReader.getLocalName().equals("person")) {
                            persons.add(builder.createPerson());
                        }
                        break;
                }
                if (!xmlStreamReader.hasNext())
                    break;

                event = xmlStreamReader.next();
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return persons;*/