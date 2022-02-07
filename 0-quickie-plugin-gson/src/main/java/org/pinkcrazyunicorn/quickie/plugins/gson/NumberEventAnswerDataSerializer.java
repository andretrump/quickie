package org.pinkcrazyunicorn.quickie.plugins.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.pinkcrazyunicorn.quickie.adapters.event.NumberEventAnswerData;

import java.lang.reflect.Type;

public class NumberEventAnswerDataSerializer implements JsonSerializer<NumberEventAnswerData> {
    @Override
    public JsonElement serialize(NumberEventAnswerData numberEventAnswerData, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(numberEventAnswerData.getData());
    }
}
