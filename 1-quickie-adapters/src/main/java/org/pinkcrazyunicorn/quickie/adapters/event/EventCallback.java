package org.pinkcrazyunicorn.quickie.adapters.event;

import java.util.Collection;
import java.util.Map;

public interface EventCallback {
    EventAnswer call(Map<String, String> data);
    Collection<EventParameter> getRequiredParameters();
}
