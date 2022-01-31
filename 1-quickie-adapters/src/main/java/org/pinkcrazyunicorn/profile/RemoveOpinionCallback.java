package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.Food;
import org.pinkcrazyunicorn.FoodMapper;
import org.pinkcrazyunicorn.event.EventAnswer;
import org.pinkcrazyunicorn.event.EventCallback;
import org.pinkcrazyunicorn.event.EventParameter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RemoveOpinionCallback implements EventCallback {
    private final ProfileService service;
    private final FoodMapper foodMapper;

    public RemoveOpinionCallback(ProfileService service) {
        this.service = service;
        foodMapper = new FoodMapper();
    }

    @Override
    public EventAnswer call(Map<String, String> data) {
        String name = data.get("profile-name");
        String foodString = data.get("food");

        Food food = this.foodMapper.fromString(foodString);
        this.service.removeOpinionAbout(name, food);

        return new EventAnswer("Successfully removed opinion");
    }

    @Override
    public Collection<EventParameter> getRequiredParameters() {
        return List.of(EventParameter.Profile, EventParameter.Food);
    }
}
