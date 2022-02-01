package org.pinkcrazyunicorn.quickie.adapters.profile;

import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswer;
import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswerData;
import org.pinkcrazyunicorn.quickie.adapters.event.EventCallback;
import org.pinkcrazyunicorn.quickie.adapters.event.EventParameter;
import org.pinkcrazyunicorn.quickie.application.profile.ProfileService;
import org.pinkcrazyunicorn.quickie.domain.profile.Profile;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ViewProfileCallback implements EventCallback {
    ProfileService service;
    ProfileMapper mapper;

    public ViewProfileCallback(ProfileService service) {
        super();
        this.service = service;
        this.mapper = new ProfileMapper();
    }

    @Override
    public EventAnswer call(Map<String, String> data) {
        String name = data.get("profile-name");
        Optional<Profile> profile = this.service.getBy(name);

        if (profile.isEmpty()) {
            return new EventAnswer("Profile '" + name + "' was not found");
        }

        EventAnswerData returnData = mapper.mapToEventAnswer(profile.get());

        return new EventAnswer("", returnData);
    }

    @Override
    public Collection<EventParameter> getRequiredParameters() {
        return List.of(EventParameter.Profile);
    }
}
