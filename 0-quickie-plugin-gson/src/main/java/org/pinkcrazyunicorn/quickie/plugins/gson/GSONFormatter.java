package org.pinkcrazyunicorn.quickie.plugins.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.pinkcrazyunicorn.quickie.adapters.event.*;

public class GSONFormatter implements EventAnswerDataFormatter {
    private final Gson gson;

    public GSONFormatter() {
        super();
        GsonBuilder builder = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .registerTypeAdapter(StringEventAnswerData.class, new StringEventAnswerDataSerializer())
                .registerTypeAdapter(NumberEventAnswerData.class, new NumberEventAnswerDataSerializer())
                .registerTypeAdapter(BooleanEventAnswerData.class, new BooleanEventAnswerDataSerializer());
        this.gson = builder.create();
    }

    @Override
    public String convert(EventAnswerData data) {
        return this.gson.toJson(data);
    }
}
