package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.event.EventAnswer;
import org.pinkcrazyunicorn.event.EventAnswerData;
import org.pinkcrazyunicorn.event.EventCallback;

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
        if (name == null) {
            return new EventAnswer("'profile-name' must be specified");
        }
        Optional<Profile> profile = this.service.getBy(name);

        if (profile.isEmpty()) {
            return new EventAnswer("Profile '" + name + "' was not found");
        }

        EventAnswerData returnData = mapper.mapToEventAnswer(profile.get());

        return new EventAnswer("", returnData);
    }
}
