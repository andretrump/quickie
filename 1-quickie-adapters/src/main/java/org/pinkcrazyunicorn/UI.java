package org.pinkcrazyunicorn;

import org.pinkcrazyunicorn.event.Event;
import org.pinkcrazyunicorn.event.EventCallback;
import org.pinkcrazyunicorn.event.EventType;

public interface UI {
    void registerEvent(EventType event, EventCallback callback);
    void handleEvent(Event event);
    Event getUserEvent();
}
