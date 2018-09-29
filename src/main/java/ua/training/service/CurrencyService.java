package ua.training.service;

import ua.training.entity.Currency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CurrencyService extends EntityService {
    public static List<Currency> selectActualCurrencyByTxt(List<Currency> currencies, Set<String> actualCurrency) {
        return currencies.stream().filter(c -> actualCurrency.contains(c.getTxt())).collect(Collectors.toList());
    }

    public static void writeCurrencyInFile(List<Currency> currencies, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)))) {
            for (Currency currency : currencies) {
                writer.write(currency.toString());
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
