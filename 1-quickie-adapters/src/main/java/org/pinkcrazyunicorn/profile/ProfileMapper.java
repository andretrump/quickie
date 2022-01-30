package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.Food;
import org.pinkcrazyunicorn.FoodMapper;
import org.pinkcrazyunicorn.event.EventAnswerData;
import org.pinkcrazyunicorn.event.ListEventAnswerData;
import org.pinkcrazyunicorn.event.MapEventAnswerData;
import org.pinkcrazyunicorn.event.StringEventAnswerData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    public JPAProfile mapToJPAProfile(Profile profile) {
        JPAProfile jpa = new JPAProfile(profile.getName());

        jpa.setStock(profile.getAvailable().stream().map(food -> food.getName()).collect(Collectors.toList()));
        Map<String, String> jpaOpinions = new HashMap<>();
        profile.getOpinions().entrySet().forEach(opinionAbout -> {
            jpaOpinions.put(opinionAbout.getKey().getName(), opinionAbout.getValue().getName());
        });
        jpa.setOpinions(jpaOpinions);

        return jpa;
    }

    public Profile fromJPAProfile(JPAProfile jpaProfile) {
        Profile profile = new Profile(jpaProfile.getName());

        for (String foodName : jpaProfile.getStock()) {
            profile.addToAvailable(new Food(foodName));
        }

        Map<String, String> opinions = jpaProfile.getOpinions();
        for (Map.Entry<String, String> entry : opinions.entrySet()) {
            Opinion opinion = this.opinionMapper.fromString(entry.getValue());
            profile.addOpinionAbout(new Food(entry.getKey()), opinion);
        }

        return profile;
    }
}
