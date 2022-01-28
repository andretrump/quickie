package org.pinkcrazyunicorn.event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListEventAnswerData extends ArrayList<EventAnswerData> implements EventAnswerData {
    @Override
    public String toJson() {
        return "[" +
                this.stream()
                        .map(data -> data.toJson())
                        .collect(Collectors.joining(", "))
                + "]";
    }
}
