package org.pinkcrazyunicorn.event;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapEventAnswerData extends HashMap<String, EventAnswerData> implements EventAnswerData {
    @Override
    public String toJson() {
        return "{\n" +
                this.entrySet().stream()
                        .map(e -> e.getKey() + ": " + e.getValue().toJson())
                        .collect(Collectors.joining(",\n"))
                + "\n}";
    }
}
