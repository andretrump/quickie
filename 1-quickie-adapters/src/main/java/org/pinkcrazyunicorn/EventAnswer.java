package org.pinkcrazyunicorn;

import java.util.HashMap;
import java.util.Map;

public class EventAnswer {
    private String title;
    private Map<String, String> properties;

    public EventAnswer(String title) {
        this.title = title;
        this.properties = new HashMap<>();
    }

    public EventAnswer(String payload, Map<String, String> properties) {
        this.title = payload;
        this.properties = properties;
    }

    public String getTitle() {
        return title;
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
