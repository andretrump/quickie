package org.pinkcrazyunicorn;

import java.util.Map;

public class Event {
    private Map<String, String> data;
    private EventType type;

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
