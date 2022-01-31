package org.pinkcrazyunicorn.quickie.adapters.profile;

import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswer;
import org.pinkcrazyunicorn.quickie.adapters.event.EventCallback;
import org.pinkcrazyunicorn.quickie.adapters.event.EventParameter;
import org.pinkcrazyunicorn.quickie.application.profile.ProfileService;

import java.util.Collection;
import java.util.List;
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

    @Override
    public Collection<EventParameter> getRequiredParameters() {
        return List.of(EventParameter.Profile);
    }
}
