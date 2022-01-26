package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.EventAnswer;
import org.pinkcrazyunicorn.EventCallback;

public class AddProfileCallback implements EventCallback {
    ProfileService service;

    public AddProfileCallback(ProfileService service) {
        super();
        this.service = service;
    }

    @Override
    public EventAnswer call(String data) {
        String name = data;

        this.service.add(name);

        return new EventAnswer("Successfully added profile '" + name + "'");
    }
}
