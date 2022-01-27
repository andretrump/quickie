package org.pinkcrazyunicorn;

public interface UI {
    void registerEvent(EventType event, EventCallback callback);
    void handleEvent(Event event);
    Event getUserEvent();
}
