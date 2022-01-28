package org.pinkcrazyunicorn;

import org.pinkcrazyunicorn.profile.Opinion;
import org.pinkcrazyunicorn.profile.Profile;
import org.pinkcrazyunicorn.profile.ProfileRepository;

import java.util.Optional;

public class DummyRepository implements ProfileRepository {
    @Override
    public void add(String name) {

    }

    @Override
    public Iterable<Profile> getAll() {
        return null;
    }

    @Override
    public Optional<Profile> getBy(String name) {
        if (name.equals("test")) {
            Profile profile = new Profile("test");
            profile.addOpinionAbout(Opinion.Dealbreaker, new Food("Fish"));
            profile.addOpinionAbout(Opinion.Love, new Food("Pasta"));
            profile.addOpinionAbout(Opinion.Dislike, new Food("Meat"));
            profile.addToStock(new Food("Onions"));
            profile.addToStock(new Food("Pasta"));
            return Optional.of(profile);
        }
        return Optional.empty();
    }

    @Override
    public void remove(String profile) {

    }
}
