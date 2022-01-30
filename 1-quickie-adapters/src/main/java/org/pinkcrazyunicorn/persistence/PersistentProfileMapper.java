package org.pinkcrazyunicorn.persistence;

import org.pinkcrazyunicorn.Food;
import org.pinkcrazyunicorn.FoodMapper;
import org.pinkcrazyunicorn.profile.Opinion;
import org.pinkcrazyunicorn.profile.OpinionMapper;
import org.pinkcrazyunicorn.profile.Profile;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PersistentProfileMapper {
    private final PersistentProfileFactory profileFactory;
    private final OpinionMapper opinionMapper;
    private final FoodMapper foodMapper;

    public PersistentProfileMapper(PersistentProfileFactory profileFactory) {
        this.profileFactory = profileFactory;
        this.opinionMapper = new OpinionMapper();
        this.foodMapper = new FoodMapper();
    }

    public PersistentProfile mapToPersistent(Profile profile) {
        PersistentProfile persistent = this.profileFactory.createEmpty();

        persistent.setName(profile.getName());
        persistent.setAvailable(profile.getAvailable().stream().map(food -> food.getName()).collect(Collectors.toSet()));
        Map<String, String> opinions = new HashMap<>();
        profile.getOpinions().entrySet().forEach(opinionAbout -> {
            opinions.put(opinionAbout.getKey().getName(), opinionAbout.getValue().getName());
        });
        persistent.setOpinions(opinions);

        return persistent;
    }

    public Profile mapFromPersistent(PersistentProfile persistentProfile) {
        Profile profile = new Profile(persistentProfile.getName());

        for (String foodName : persistentProfile.getAvailable()) {
            profile.addToAvailable(this.foodMapper.fromString(foodName));
        }

        Map<String, String> opinions = persistentProfile.getOpinions();
        for (Map.Entry<String, String> entry : opinions.entrySet()) {
            Opinion opinion = this.opinionMapper.fromString(entry.getValue());
            profile.addOpinionAbout(new Food(entry.getKey()), opinion);
        }

        return profile;
    }
}
