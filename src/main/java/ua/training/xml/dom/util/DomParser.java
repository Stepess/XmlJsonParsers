package ua.training.xml.dom.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ua.training.entity.Person;
import ua.training.entity.PersonBuilder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DomParser {

    public static List<Person> parse(String path) {
        File xmlFile = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        List<Person> persons = new LinkedList<>();
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("person");
            for (int i = 0; i < nodeList.getLength(); i++) {
                persons.add(getPerson(nodeList.item(i)));
            }
        } catch (ParserConfigurationException | IOException | SAXException e1) {
            e1.printStackTrace();
        }
        return persons;
    }


    private static Person getPerson(Node node) {
        PersonBuilder builder = new PersonBuilder();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            builder.setName(getTagValue("name", element));
            builder.setAddress(getTagValue("address", element));
            builder.setCash(Integer.parseInt(getTagValue("cash", element)));
        }
        return builder.createPerson();
    }


    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}


