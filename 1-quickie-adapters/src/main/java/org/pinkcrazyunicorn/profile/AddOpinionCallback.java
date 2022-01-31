package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.Food;
import org.pinkcrazyunicorn.FoodMapper;
import org.pinkcrazyunicorn.event.EventAnswer;
import org.pinkcrazyunicorn.event.EventCallback;

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
        if (name == null) {
            return new EventAnswer("'profile-name' must be specified");
        }
        String foodString = data.get("food");
        if (foodString == null) {
            return new EventAnswer("'food' must be specified");
        }
        String opinionString = data.get("opinion");
        if (opinionString == null) {
            return new EventAnswer("'opinion' must be specified");
        }

        Food food = this.foodMapper.fromString(foodString);
        Opinion opinion = this.opinionMapper.fromString(opinionString);

        this.service.addOpinionAbout(name, food, opinion);
        return new EventAnswer("Successfully added opinion");
    }
}
