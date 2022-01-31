package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.event.EventAnswer;
import org.pinkcrazyunicorn.event.EventAnswerData;
import org.pinkcrazyunicorn.event.EventCallback;
import org.pinkcrazyunicorn.event.EventParameter;

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
