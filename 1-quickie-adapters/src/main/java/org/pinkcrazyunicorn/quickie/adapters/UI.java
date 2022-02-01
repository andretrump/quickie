package org.pinkcrazyunicorn.quickie.adapters;

import org.pinkcrazyunicorn.quickie.adapters.event.Event;
import org.pinkcrazyunicorn.quickie.adapters.event.EventCallback;
import org.pinkcrazyunicorn.quickie.adapters.event.EventType;

public interface UI {
    void registerEvent(EventType event, EventCallback callback);
    void handleEvent(Event event);
    Event getUserEvent();
}
