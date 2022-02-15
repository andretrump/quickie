package org.pinkcrazyunicorn.quickie.adapters.mappers.profile;

import org.pinkcrazyunicorn.quickie.adapters.mappers.FoodMapper;
import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswerData;
import org.pinkcrazyunicorn.quickie.adapters.event.ListEventAnswerData;
import org.pinkcrazyunicorn.quickie.adapters.event.MapEventAnswerData;
import org.pinkcrazyunicorn.quickie.adapters.event.StringEventAnswerData;
import org.pinkcrazyunicorn.quickie.domain.profile.Profile;

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
        result.put("available", this.foodMapper.mapManyToEventAnswer(profile.getAvailable()));
        result.put("opinions", this.opinionMapper.mapManyToEventAnswer(profile.getOpinions()));

        return result;
    }

    public EventAnswerData mapManyToEventAnswer(Collection<Profile> profiles) {
        ListEventAnswerData result = new ListEventAnswerData();

        profiles.forEach(profile -> result.add(this.mapToEventAnswer(profile)));

        return result;
    }
}
