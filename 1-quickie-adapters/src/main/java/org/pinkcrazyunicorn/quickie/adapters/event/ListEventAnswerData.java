package org.pinkcrazyunicorn.quickie.adapters.event;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListEventAnswerData extends ArrayList<EventAnswerData> implements EventAnswerData {
    @Override
    public String toJson(int indentationLevel) {
        return "[" +
                this.stream()
                        .map(data -> data.toJson())
                        .collect(Collectors.joining(", "))
                + "]";
    }
}
