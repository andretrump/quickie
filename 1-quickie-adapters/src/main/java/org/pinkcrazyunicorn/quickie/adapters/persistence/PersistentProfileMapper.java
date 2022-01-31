package org.pinkcrazyunicorn.quickie.adapters.persistence;

import org.pinkcrazyunicorn.quickie.domain.Food;
import org.pinkcrazyunicorn.quickie.adapters.FoodMapper;
import org.pinkcrazyunicorn.quickie.domain.profile.Opinion;
import org.pinkcrazyunicorn.quickie.adapters.profile.OpinionMapper;
import org.pinkcrazyunicorn.quickie.domain.profile.Profile;

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
        persistent.setAvailable(profile.getAvailable().stream().map(Food::getName).collect(Collectors.toSet()));
        Map<String, String> opinions = new HashMap<>();
        profile.getOpinions().forEach((key, value) -> opinions.put(key.getName(), value.getName()));
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
            profile.setOpinionAbout(new Food(entry.getKey()), opinion);
        }

        return profile;
    }
}
