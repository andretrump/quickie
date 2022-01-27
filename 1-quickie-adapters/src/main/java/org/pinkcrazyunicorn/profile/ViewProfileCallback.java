package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.EventAnswer;
import org.pinkcrazyunicorn.EventCallback;
import org.pinkcrazyunicorn.Food;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ViewProfileCallback implements EventCallback {
    ProfileService service;

    public ViewProfileCallback(ProfileService service) {
        super();
        this.service = service;
    }

    @Override
    public EventAnswer call(Map<String, String> data) {
        String name = data.get("name");
        Profile profile = this.service.getBy(name);

        Map<String, String> properties = new HashMap<>();
        properties.put("name", profile.getName());
        properties.putAll(mapStock(profile.getStock()));
        properties.putAll(mapOpinions(profile.getOpinions()));

        return new EventAnswer(name, properties);
    }

    private Map<String, String> mapOpinions(Collection<OpinionAbout> opinions) {
        Map<String, String> properties = new HashMap<>();

        for (OpinionAbout opinion : opinions) {
            properties.put("Opinion about " + opinion.getFood().getName(), opinion.getOpinion().getName());
        }

        return properties;
    }

    private Map<String, String> mapStock(Stock stock) {
        Map<String, String> properties = new HashMap<>();

        int index = 0;
        for (Food food : stock.getFood()) {
            index++;
            properties.put("Food " + index, food.getName());
        }

        return properties;
    }
}
