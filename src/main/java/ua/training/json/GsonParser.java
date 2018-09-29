package ua.training.json;

import com.google.gson.*;
import ua.training.entity.Currency;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class GsonParser {
    public static List<Currency> parseData(String data) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                return formatter.parse(json.getAsString(), LocalDate::from);
            }
        }).create();
        Currency[] currencies = gson.fromJson(data, Currency[].class);
        return Arrays.asList(currencies);
    }

}
