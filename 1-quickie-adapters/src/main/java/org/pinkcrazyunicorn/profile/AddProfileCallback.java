package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.EventAnswer;
import org.pinkcrazyunicorn.EventCallback;

import java.util.Map;

public class AddProfileCallback implements EventCallback {
    ProfileService service;

    public AddProfileCallback(ProfileService service) {
        super();
        this.service = service;
    }

    @Override
    public EventAnswer call(Map<String, String> data) {
        String name = data.get("profile-name");

        this.service.add(name);

        return new EventAnswer("Successfully added profile '" + name + "'");
    }
}
