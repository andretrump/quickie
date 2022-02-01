package org.pinkcrazyunicorn.quickie.adapters.profile;

import org.pinkcrazyunicorn.quickie.application.profile.ProfileService;
import org.pinkcrazyunicorn.quickie.domain.Food;
import org.pinkcrazyunicorn.quickie.adapters.FoodMapper;
import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswer;
import org.pinkcrazyunicorn.quickie.adapters.event.EventCallback;
import org.pinkcrazyunicorn.quickie.adapters.event.EventParameter;
import org.pinkcrazyunicorn.quickie.domain.profile.Opinion;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SetOpinionCallback implements EventCallback {
    private final ProfileService service;
    private final FoodMapper foodMapper;
    private final OpinionMapper opinionMapper;

    public SetOpinionCallback(ProfileService service) {
        this.service = service;
        this.foodMapper = new FoodMapper();
        this.opinionMapper = new OpinionMapper();
    }

    @Override
    public EventAnswer call(Map<String, String> data) {
        String name = data.get("profile-name");
        String foodString = data.get("food");
        String opinionString = data.get("opinion");

        Food food = this.foodMapper.fromString(foodString);
        Opinion opinion = this.opinionMapper.fromString(opinionString);

        this.service.setOpinionAbout(name, food, opinion);
        return new EventAnswer("Successfully added opinion");
    }

    @Override
    public Collection<EventParameter> getRequiredParameters() {
        return List.of(EventParameter.Profile, EventParameter.Food, EventParameter.Opinion);
    }
}
