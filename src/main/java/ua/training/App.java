package ua.training;

import ua.training.entity.Person;
import ua.training.xml.dom.util.DomParser;
import ua.training.xml.dom.util.XmlCreator;

import java.util.List;
import java.util.stream.Collectors;

public class App
{
    public static final String PATH_TO_XML = "D:\\temp\\catalog.xml";

    public static void main( String[] args )
    {
        XmlCreator.createXml(PATH_TO_XML);
        List<Person> persons = DomParser.parse(PATH_TO_XML);

        System.out.println(persons.stream().filter(p -> p.getCash()>=10000).collect(Collectors.toList()));
    }
}
