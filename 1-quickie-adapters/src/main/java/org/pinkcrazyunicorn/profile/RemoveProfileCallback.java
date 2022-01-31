package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.event.EventAnswer;
import org.pinkcrazyunicorn.event.EventCallback;
import org.pinkcrazyunicorn.event.EventParameter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RemoveProfileCallback implements EventCallback {
    private final ProfileService service;

    public RemoveProfileCallback(ProfileService service) {
        this.service = service;
    }

    @Override
    public EventAnswer call(Map<String, String> data) {
        String name = data.get("profile-name");

        this.service.remove(name);

        return new EventAnswer("Successfully removed profile '" + name + "'");
    }

    @Override
    public Collection<EventParameter> getRequiredParameters() {
        return List.of(EventParameter.Profile);
    }
}
