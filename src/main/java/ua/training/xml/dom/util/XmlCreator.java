package ua.training.xml.dom.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XmlCreator {

    public static void createXml(String path) {
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

    private static Node createPersonNode(Document document, String id, String name, String address, String salary, String education) {
        Element person = document.createElement("person");
        person.setAttribute("id", id);
        person.appendChild(createPersonElement(document,"name", name));
        person.appendChild(createPersonElement(document,"address", address));
        person.appendChild(createPersonElement(document,"salary", salary));
        person.appendChild(createPersonElement(document,"education", education));
        return person;
    }

    private static Node createPersonElement(Document document, String name, String value) {
        Element node = document.createElement(name);
        node.appendChild(document.createTextNode(value));
        return node;
    }
}
