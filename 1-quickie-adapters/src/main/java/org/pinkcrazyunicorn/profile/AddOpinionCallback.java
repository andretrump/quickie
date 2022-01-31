package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.Food;
import org.pinkcrazyunicorn.FoodMapper;
import org.pinkcrazyunicorn.event.EventAnswer;
import org.pinkcrazyunicorn.event.EventCallback;
import org.pinkcrazyunicorn.event.EventParameter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AddOpinionCallback implements EventCallback {
    private final ProfileService service;
    private final FoodMapper foodMapper;
    private final OpinionMapper opinionMapper;

    public AddOpinionCallback(ProfileService service) {
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

        this.service.addOpinionAbout(name, food, opinion);
        return new EventAnswer("Successfully added opinion");
    }

    @Override
    public Collection<EventParameter> getRequiredParameters() {
        return List.of(EventParameter.Profile, EventParameter.Food, EventParameter.Opinion);
    }
}
