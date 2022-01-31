package org.pinkcrazyunicorn.quickie.adapters.event;

import java.util.HashMap;
import java.util.stream.Collectors;

public class MapEventAnswerData extends HashMap<String, EventAnswerData> implements EventAnswerData {
    @Override
    public String toJson(int indentationLevel) {
        String innerIndentation = this.indentationOf(indentationLevel+1);
        String outerIndentation = this.indentationOf(indentationLevel);
        return "{\n" + innerIndentation +
                this.entrySet().stream()
                        .map(e -> "\"" + e.getKey() + "\": " + e.getValue().toJson(indentationLevel + 1))
                        .collect(Collectors.joining(",\n" + innerIndentation))
                + "\n" + outerIndentation + "}";
    }
}
