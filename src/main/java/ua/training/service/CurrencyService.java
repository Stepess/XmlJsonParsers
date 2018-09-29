package ua.training.service;

import ua.training.entity.Currency;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CurrencyService extends EntityService {
    public static List<Currency> selectActualCurrencyByTxt(List<Currency> currencies, Set<String> actualCurrency) {
        return currencies.stream().filter(c -> actualCurrency.contains(c.getTxt())).collect(Collectors.toList());
    }
}
