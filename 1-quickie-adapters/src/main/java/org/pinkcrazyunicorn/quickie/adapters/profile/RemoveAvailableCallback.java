package org.pinkcrazyunicorn.quickie.adapters.profile;

import org.pinkcrazyunicorn.quickie.application.profile.ProfileService;
import org.pinkcrazyunicorn.quickie.domain.Food;
import org.pinkcrazyunicorn.quickie.adapters.FoodMapper;
import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswer;
import org.pinkcrazyunicorn.quickie.adapters.event.EventCallback;
import org.pinkcrazyunicorn.quickie.adapters.event.EventParameter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RemoveAvailableCallback implements EventCallback {
    private final ProfileService service;
    private final FoodMapper foodMapper;

    public RemoveAvailableCallback(ProfileService service) {
        this.service = service;
        foodMapper = new FoodMapper();
    }

    @Override
    public EventAnswer call(Map<String, String> data) {
        String name = data.get("profile-name");
        String foodString = data.get("food");

        Food food = this.foodMapper.fromString(foodString);
        this.service.markUnavailable(name, food);

        return new EventAnswer("Successfully removed opinion");
    }

    @Override
    public Collection<EventParameter> getRequiredParameters() {
        return List.of(EventParameter.Profile, EventParameter.Food);
    }
}
