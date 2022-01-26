package org.pinkcrazyunicorn;

public interface UI {
    void registerEvent(EventType event, EventCallback callback);
    void run();
}
