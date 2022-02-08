package org.pinkcrazyunicorn.quickie.plugins.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.pinkcrazyunicorn.quickie.adapters.event.StringEventAnswerData;

import java.lang.reflect.Type;

public class StringEventAnswerDataSerializer implements JsonSerializer<StringEventAnswerData> {
    @Override
    public JsonElement serialize(StringEventAnswerData stringEventAnswerData, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(stringEventAnswerData.getData());
    }
}
