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
import java.util.Optional;

public class XmlWriter {

    public static void writeInXml(String path, List<Person> values) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement = document.createElement("catalog");
            document.appendChild(rootElement);

            Element notebookElement = document.createElement("notebook");
            rootElement.appendChild(notebookElement);
            int count = 0;

            for (Person person : values) {
                notebookElement.appendChild(createPersonNode(document, String.valueOf(++count), person.getName(),
                        person.getAddress(), String.valueOf(person.getCash()), person.getEducation()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for beautiful xml
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
        person.appendChild(createPersonElement(document, "name", name));
        person.appendChild(createPersonElement(document, "address", address));
        person.appendChild(createPersonElement(document, "cash", cash));
        Optional.ofNullable(education)
                .ifPresent(e -> person.appendChild(createPersonElement(document, "education", e)));
        return person;
    }

    private static Node createPersonElement(Document document, String name, String value) {
        Element node = document.createElement(name);
        node.appendChild(document.createTextNode(value));
        return node;
    }
}