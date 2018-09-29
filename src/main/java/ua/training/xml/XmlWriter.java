package ua.training.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ua.training.entity.Person;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Map;

public class XmlWriter {

    public static void writeInitialXml(String path, List<Person> values) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("catalog");
            document.appendChild(rootElement);

            Element notebookElement = document.createElement("notebook");
            rootElement.appendChild(notebookElement);
            int count=0;

            for (Person person: values) {
                notebookElement.appendChild(createPersonNode(document, String.valueOf(++count), person.getName(),
                        person.getAddress(), String.valueOf(person.getCash()), person.getEducation()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path));

            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void writeFilteredXml(String path, List<Person> values) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("catalog");
            document.appendChild(rootElement);

            Element notebookElement = document.createElement("notebook");
            rootElement.appendChild(notebookElement);

            for (Person person: values) {
                notebookElement.appendChild(createFilteredPersonNode(document, String.valueOf(person.getId()), person.getName(),
                        person.getAddress(), String.valueOf(person.getCash())));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path));

            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static Node createPersonNode(Document document, String id, String name, String address, String cash, String education) {
        Element person = document.createElement("person");
        person.setAttribute("id", id);
        person.appendChild(createPersonElement(document,"name", name));
        person.appendChild(createPersonElement(document,"address", address));
        person.appendChild(createPersonElement(document,"cash", cash));
        person.appendChild(createPersonElement(document,"education", education));
        return person;
    }

    private static Node createFilteredPersonNode(Document document, String id, String name, String address, String cash) {
        Element person = document.createElement("person");
        person.setAttribute("id", id);
        person.appendChild(createPersonElement(document,"name", name));
        person.appendChild(createPersonElement(document,"address", address));
        person.appendChild(createPersonElement(document,"cash", cash));
        return person;
    }

    private static Node createPersonElement(Document document, String name, String value) {
        Element node = document.createElement(name);
        node.appendChild(document.createTextNode(value));
        return node;
    }
}



/*public static void createXml(String path, List<Person> values) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("catalog");
            document.appendChild(rootElement);

            Element notebookElement = document.createElement("notebook");
            rootElement.appendChild(notebookElement);

            init(document, notebookElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path));

            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void init(Document document, Element notebookElement) {
        notebookElement.appendChild(createPersonNode(document, "1", "Darth Vader", "Far-far galaxy", "100500", "Death Star"));
        notebookElement.appendChild(createPersonNode(document, "2", "Ivan", "Ukraine", "9999", "NTUU KPI"));
        notebookElement.appendChild(createPersonNode(document, "3", "Alexandra", "Ukraine", "1234567", "NAU"));
    }

    private static Node createPersonNode(Document document, String id, String name, String address, String cash, String education) {
        Element person = document.createElement("person");
        person.setAttribute("id", id);
        person.appendChild(createPersonElement(document,"name", name));
        person.appendChild(createPersonElement(document,"address", address));
        person.appendChild(createPersonElement(document,"cash", cash));
        person.appendChild(createPersonElement(document,"education", education));
        return person;
    }

    private static Node createPersonElement(Document document, String name, String value) {
        Element node = document.createElement(name);
        node.appendChild(document.createTextNode(value));
        return node;
    }*/


/*public static void createXml(String path, List<String> tags, List<Person> values) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("catalog");
            document.appendChild(rootElement);

            Element notebookElement = document.createElement("notebook");
            rootElement.appendChild(notebookElement);

            for (int i=0; i<values.size(); i++){
                notebookElement.appendChild(createPersonElement(document, i+1, ))
            }

            notebookElement.appendChild(createPersonNode(document, "1", "Darth Vader", "Far-far galaxy", "100500", "Death Star"));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path));

            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void init(Document document, Element notebookElement) {

    }

    private static Node createPersonNode(Document document, int id, List<String> tags, List<Person> values) {
        Element person = document.createElement("person");
        person.setAttribute("id", String.valueOf(id));
        for (Person value: values)
            person.appendChild(createPersonElement(document,, entry.getValue()));
        }
        return person;
    }

    private static Node createPersonElement(Document document, String name, String value) {
        Element node = document.createElement(name);
        node.appendChild(document.createTextNode(value));
        return node;
    }*/