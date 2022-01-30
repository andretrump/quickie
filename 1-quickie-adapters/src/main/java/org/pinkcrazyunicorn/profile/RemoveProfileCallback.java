package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.event.EventAnswer;
import org.pinkcrazyunicorn.event.EventCallback;

import java.util.Map;

public class RemoveProfileCallback implements EventCallback {
    private final ProfileService service;
    public RemoveProfileCallback(ProfileService service) {
        this.service = service;
    }

    @Override
    public EventAnswer call(Map<String, String> data) {
        String name = data.get("profile-name");
        if (name == null) {
            return new EventAnswer("'profile-name' must be specified");
        }

        this.service.remove(name);

        return new EventAnswer("Successfully removed profile '" + name + "'");
    }
}
