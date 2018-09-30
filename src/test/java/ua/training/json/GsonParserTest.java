package ua.training.json;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import ua.training.entity.Currency;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;

import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest({StringReaderFromUrl.class})

public class GsonParserTest {

    private static final String JSON_DATA = "[{ \"r030\":36,\"txt\":\"Австралійський долар\",\"rate\":20.392119,\"cc\":\"AUD\",\"exchangedate\":\"01.10.2018\" }," +
            "{ \"r030\":124,\"txt\":\"Канадський долар\",\"rate\":21.724158,\"cc\":\"CAD\",\"exchangedate\":\"01.10.2018\" }," +
            "{ \"r030\":156,\"txt\":\"Юань Женьмiньбi\",\"rate\":4.108015,\"cc\":\"CNY\",\"exchangedate\":\"01.10.2018\" }]";
    private static final String ADDRESS_TO_JSON = "http:\\\\...";
    private static List<Currency> initialValues;

    @BeforeClass
    public static void setUp() {
        initialValues = new LinkedList<>();
        initialValues.add(new Currency(36, "Австралійський долар", 20.392119, "AUD",
                LocalDate.parse("01.10.2018", DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
        initialValues.add(new Currency(124, "Канадський долар", 21.724158, "CAD",
                LocalDate.parse("01.10.2018", DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
        initialValues.add(new Currency(156, "Юань Женьмiньбi", 4.108015, "CNY",
                LocalDate.parse("01.10.2018", DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
    }

    @Test
    public void WhenParseJsonThenSizeOfCurrencyListEqualsSizeOfJsonObjects() {
        PowerMockito.mockStatic(StringReaderFromUrl.class);
        PowerMockito.when(StringReaderFromUrl.read(anyString())).thenReturn(JSON_DATA);

        String jsonData = StringReaderFromUrl.read(ADDRESS_TO_JSON);
        List<Currency> currencies = GsonParser.parseData(jsonData);

        assertEquals(initialValues.size(), currencies.size());
    }

    @Test
    public void WhenParseJsonThenParsedListAndInitialDataAreSame() {
        PowerMockito.mockStatic(StringReaderFromUrl.class);
        PowerMockito.when(StringReaderFromUrl.read(anyString())).thenReturn(JSON_DATA);

        String jsonData = StringReaderFromUrl.read(ADDRESS_TO_JSON);
        List<Currency> parsedData = GsonParser.parseData(jsonData);

        assertEquals(initialValues.size(), parsedData.size());

        for (int i = 0; i < initialValues.size(); i++) {
            assertEquals(initialValues.get(i), parsedData.get(i));
        }
    }

}