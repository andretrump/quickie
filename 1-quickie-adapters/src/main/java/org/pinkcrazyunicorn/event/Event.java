package org.pinkcrazyunicorn.event;

import java.util.Map;

public class Event {
    private final Map<String, String> data;
    private final EventType type;

    public Event(EventType type, Map<String, String> data) {
        this.type = type;
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }

    public EventType getType() {
        return type;
    }
}
