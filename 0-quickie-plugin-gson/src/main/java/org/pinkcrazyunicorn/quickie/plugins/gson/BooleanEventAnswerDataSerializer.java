package org.pinkcrazyunicorn.quickie.plugins.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.pinkcrazyunicorn.quickie.adapters.event.BooleanEventAnswerData;

import java.lang.reflect.Type;

public class BooleanEventAnswerDataSerializer implements JsonSerializer<BooleanEventAnswerData> {
    @Override
    public JsonElement serialize(BooleanEventAnswerData booleanEventAnswerData, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(booleanEventAnswerData.getData());
    }
}
