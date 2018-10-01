package ua.training.xml;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.training.entity.Person;
import ua.training.entity.PersonBuilder;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class DomParserTest {
    private final static String PATH_TO_XML = "src\\main\\java\\resource\\test\\testDataDom.xml";
    private static List<Person> initialValues;
    private XmlParser parser = new DomParser();

    @BeforeClass
    public static void setUp() throws Exception {
        initialValues = new LinkedList<>();

        PersonBuilder builder = new PersonBuilder();

        builder.setName("Darth Vader")
                .setId(1)
                .setAddress("Far-far galaxy")
                .setCash(100500)
                .setEducation("Death Star");

        initialValues.add(builder.createPerson());

        builder.setName("Ivan")
                .setId(2)
                .setAddress("Ukraine")
                .setCash(9999)
                .setEducation("NTUU KPI");

        initialValues.add(builder.createPerson());

        builder.setName("Alexandra")
                .setId(3)
                .setAddress("Ukraine")
                .setCash(1234567)
                .setEducation("NAU");

        initialValues.add(builder.createPerson());

        XmlWriter.writeInXml(PATH_TO_XML, initialValues);
    }

    @Test
    public void GivenInitialDataSetWhenParseItFromXmlThenParsedDataSetHasSameSize() {
        List<Person> parsedData = parser.parseToList(PATH_TO_XML);
        assertEquals(initialValues.size(), parsedData.size());
    }

    @Test
    public void GivenInitialDataSetWhenParseItFromXmlThenParsedDataSetEqual() {
        List<Person> parsedData = parser.parseToList(PATH_TO_XML);
        assertEquals(initialValues.size(), parsedData.size());
        for (int i = 0; i < initialValues.size(); i++) {
            assertEquals(initialValues.get(i), parsedData.get(i));
        }
    }
}