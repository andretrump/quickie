package org.pinkcrazyunicorn.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EventAnswer {
    private String title;
    private Optional<EventAnswerData> data;

    public EventAnswer(String title) {
        this.title = title;
        this.data = Optional.empty();
    }

    public EventAnswer(String title, EventAnswerData data) {
        this.title = title;
        this.data = Optional.of(data);
    }

    public String getTitle() {
        return title;
    }

    public Optional<EventAnswerData> getData() {
        return data;
    }
}
