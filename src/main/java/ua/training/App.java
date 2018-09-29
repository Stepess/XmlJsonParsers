package ua.training;

import ua.training.entity.Person;
import ua.training.entity.PersonBuilder;
import ua.training.service.PersonService;
import ua.training.xml.dom.util.DomParser;
import ua.training.xml.dom.util.StaxParser;
import ua.training.xml.dom.util.XmlCreator;
import ua.training.xml.dom.util.XmlParser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static final String PATH_TO_XML = "D:\\temp\\catalog.xml";
    public static final String PATH_TO_XML_DOM = "D:\\temp\\catalogDom.xml";
    public static final String PATH_TO_XML_STAX = "D:\\temp\\catalogStax.xml";

    public static void main(String[] args) {

        List<Person> initialValues = new LinkedList<>();

        PersonBuilder builder = new PersonBuilder();

        builder.setName("Darth Vader");
        builder.setAddress("Far-far galaxy");
        builder.setCash(100500);
        builder.setEducation("Death Star");

        initialValues.add(builder.createPerson());

        builder.setName("Ivan");
        builder.setAddress("Ukraine");
        builder.setCash(9999);
        builder.setEducation("NTUU KPI");

        initialValues.add(builder.createPerson());

        builder.setName("Alexandra");
        builder.setAddress("Ukraine");
        builder.setCash(1234567);
        builder.setEducation("NAU");

        initialValues.add(builder.createPerson());

        XmlCreator.createInitialXml(PATH_TO_XML, initialValues);
        XmlParser DomParser = new DomParser();
        List<Person> persons = DomParser.parseToList(PATH_TO_XML);

        persons = PersonService.selectPersonsWithCashMoreThen(100_000, persons);
        PersonService.printListToConsole(persons);

        XmlCreator.createFilteredXml(PATH_TO_XML_DOM, persons);

        XmlParser StaxParser = new StaxParser();
        persons = StaxParser.parseToList(PATH_TO_XML);
        persons = PersonService.selectPersonsWithCashMoreThen(100_000, persons);
        PersonService.printListToConsole(persons);

        XmlCreator.createFilteredXml(PATH_TO_XML_STAX, persons);

    }
}



/*List<String> tagsName = new LinkedList<>();

        tagsName.add("name");
        tagsName.add("address");
        tagsName.add("cash");
        tagsName.add("education");

        List<Person> initialValues = new LinkedList<>();

        PersonBuilder builder = new PersonBuilder();

        builder.setName("Darth Vader");
        builder.setAddress("Far-far galaxy");
        builder.setCash(100500);
        builder.setEducation("Death Star");

        initialValues.add(builder.createPerson());

        builder.setName("Ivan");
        builder.setAddress("Ukraine");
        builder.setCash(9999);
        builder.setEducation("NTUU KPI");

        initialValues.add(builder.createPerson());

        builder.setName("Alexandra");
        builder.setAddress("Ukraine");
        builder.setCash(1234567);
        builder.setEducation("NAU");

        initialValues.add(builder.createPerson());

        List<Map> records = new LinkedList<>();

        Map<String, String> record = new HashMap<>();

        record.put(tagsName.get(0), )*/


