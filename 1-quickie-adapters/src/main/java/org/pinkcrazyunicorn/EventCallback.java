package org.pinkcrazyunicorn;

import java.util.Map;

public interface EventCallback {
    EventAnswer call(Map<String, String> data);
}
