package ua.training;

import ua.training.entity.Currency;
import ua.training.entity.Person;
import ua.training.entity.PersonBuilder;
import ua.training.json.GsonParser;
import ua.training.json.StringReaderFromUrl;
import ua.training.service.CurrencyService;
import ua.training.service.PersonService;
import ua.training.xml.XmlWriter;
import ua.training.xml.XmlParser;
import ua.training.xml.DomParser;
import ua.training.xml.StaxParser;

import java.util.*;

public class App {
    public static final String PATH_TO_XML = "src\\main\\java\\resource\\xml\\catalog.xml";
    public static final String PATH_TO_XML_DOM = "src\\main\\java\\resource\\xml\\catalogDom.xml";
    public static final String PATH_TO_XML_STAX = "src\\main\\java\\resource\\xml\\catalogStax.xml";

    public static final String[] ACTUAL_CURRENCY = {"Долар США", "Російський рубль", "Євро"};

    public static final String ADDRESS_TO_JSON = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    public static final String PATH_TO_CURRENCY_OUTPUT_FILE = "src\\main\\java\\resource\\json\\actualCurrency.txt";

    public static void main(String[] args) {
        XmlWriter.writeInXml(PATH_TO_XML, generateInitialValuesForXml());
        XmlParser DomParser = new DomParser();
        List<Person> persons = DomParser.parseToList(PATH_TO_XML);

        persons = PersonService.selectPersonsWithCashMoreThen(100_000, persons);
        PersonService.printListToConsole(persons);

        XmlWriter.writeInXml(PATH_TO_XML_DOM, persons);

        XmlParser StaxParser = new StaxParser();
        persons = StaxParser.parseToList(PATH_TO_XML);
        persons = PersonService.selectPersonsWithCashMoreThen(100_000, persons);
        PersonService.printListToConsole(persons);

        XmlWriter.writeInXml(PATH_TO_XML_STAX, persons);

        String jsonData = StringReaderFromUrl.read(ADDRESS_TO_JSON);

        List<Currency> currencies = GsonParser.parseData(jsonData);

        currencies = CurrencyService.selectActualCurrencyByTxt(currencies, new HashSet<>(Arrays.asList(ACTUAL_CURRENCY)));

        CurrencyService.printListToConsole(currencies);
        CurrencyService.writeCurrencyInFile(currencies, PATH_TO_CURRENCY_OUTPUT_FILE);
    }

    private static List<Person> generateInitialValuesForXml() {
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