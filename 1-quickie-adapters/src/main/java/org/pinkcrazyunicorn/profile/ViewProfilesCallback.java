package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.event.EventAnswer;
import org.pinkcrazyunicorn.event.EventCallback;

import java.util.Collection;
import java.util.Map;

public class ViewProfilesCallback implements EventCallback {
    private final ProfileService service;
    private final ProfileMapper mapper;

    public ViewProfilesCallback(ProfileService service) {
        this.service = service;
        this.mapper = new ProfileMapper();
    }

    @Override
    public EventAnswer call(Map<String, String> data) {
        Collection<Profile> profiles = service.getAll();

        return new EventAnswer("All profiles: ", this.mapper.mapManyToEventAnswer(profiles));
    }
}
