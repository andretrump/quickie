package org.pinkcrazyunicorn;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventAnswer {
    private String payload;
    private Map<String, String> properties;

    public EventAnswer(String payload) {
        this.payload = payload;
        this.properties = new HashMap<>();
    }

    public EventAnswer(String payload, Map<String, String> properties) {
        this.payload = payload;
        this.properties = properties;
    }
}
