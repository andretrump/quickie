package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.FoodMapper;
import org.pinkcrazyunicorn.event.EventAnswerData;
import org.pinkcrazyunicorn.event.ListEventAnswerData;
import org.pinkcrazyunicorn.event.MapEventAnswerData;
import org.pinkcrazyunicorn.event.StringEventAnswerData;

import java.util.Collection;

public class ProfileMapper {
    FoodMapper foodMapper;
    OpinionMapper opinionMapper;

    public ProfileMapper() {
        this.foodMapper = new FoodMapper();
        this.opinionMapper = new OpinionMapper();
    }

    public EventAnswerData mapToEventAnswer(Profile profile) {
        MapEventAnswerData result = new MapEventAnswerData();

        result.put("name", new StringEventAnswerData(profile.getName()));
        result.put("stock", this.foodMapper.mapManyToEventAnswer(profile.getAvailable()));
        result.put("opinions", this.opinionMapper.mapManyToEventAnswer(profile.getOpinions()));

        return result;
    }

    public EventAnswerData mapManyToEventAnswer(Collection<Profile> profiles) {
        ListEventAnswerData result = new ListEventAnswerData();

        profiles.forEach(profile -> result.add(this.mapToEventAnswer(profile)));

        return result;
    }
}
