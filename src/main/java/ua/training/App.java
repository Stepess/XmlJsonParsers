package ua.training;

import ua.training.entity.Currency;
import ua.training.entity.Person;
import ua.training.entity.PersonBuilder;
import ua.training.json.GsonParser;
import ua.training.json.StringReaderFromUrl;
import ua.training.service.CurrencyService;
import ua.training.service.PersonService;
import ua.training.xml.dom.util.XmlCreator;
import ua.training.xml.dom.util.XmlParser;
import ua.training.xml.dom.util.DomParser;
import ua.training.xml.dom.util.StaxParser;

import java.util.*;

public class App {
    public static final String PATH_TO_XML = "D:\\temp\\catalog.xml";
    public static final String PATH_TO_XML_DOM = "D:\\temp\\catalogDom.xml";
    public static final String PATH_TO_XML_STAX = "D:\\temp\\catalogStax.xml";

    public static final String[] ACTUAL_CURRENCY = {"Долар США", "Російський рубль", "Євро"};

    public static final String ADDRESS_TO_JSON = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    public static void main(String[] args) {
        XmlCreator.createInitialXml(PATH_TO_XML, generateInitialVakuesForXml());
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

        String jsonData = StringReaderFromUrl.read(ADDRESS_TO_JSON);

        List<Currency> currencies = GsonParser.parseData(jsonData);

        currencies = CurrencyService.selectActualCurrencyByTxt(currencies, new HashSet<>(Arrays.asList(ACTUAL_CURRENCY)));

        CurrencyService.printListToConsole(currencies);
    }

    private static List<Person> generateInitialVakuesForXml() {
        List<Person> initialValues = new LinkedList<>();

        PersonBuilder builder = new PersonBuilder();

        builder.setName("Darth Vader")
                .setAddress("Far-far galaxy")
                .setCash(100500)
                .setEducation("Death Star");

        initialValues.add(builder.createPerson());

        builder.setName("Ivan")
                .setAddress("Ukraine")
                .setCash(9999)
                .setEducation("NTUU KPI");

        initialValues.add(builder.createPerson());

        builder.setName("Alexandra")
                .setAddress("Ukraine")
                .setCash(1234567)
                .setEducation("NAU");

        initialValues.add(builder.createPerson());

        return initialValues;
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


