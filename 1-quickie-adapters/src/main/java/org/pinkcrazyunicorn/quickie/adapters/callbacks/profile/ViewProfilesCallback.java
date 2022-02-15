package org.pinkcrazyunicorn.quickie.adapters.callbacks.profile;

import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswer;
import org.pinkcrazyunicorn.quickie.adapters.event.EventCallback;
import org.pinkcrazyunicorn.quickie.adapters.event.EventParameter;
import org.pinkcrazyunicorn.quickie.adapters.mappers.profile.ProfileMapper;
import org.pinkcrazyunicorn.quickie.application.profile.ProfileService;
import org.pinkcrazyunicorn.quickie.domain.profile.Profile;

import java.util.Collection;
import java.util.List;
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

    @Override
    public Collection<EventParameter> getRequiredParameters() {
        return List.of();
    }
}
