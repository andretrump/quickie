package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.Food;

import java.util.Collection;
import java.util.Optional;

public class ProfileService {
    private ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        super();
        this.repository = repository;
    }

    public void add(String name) {
        this.repository.add(name);
    }

    public void remove(String name) {
        this.repository.remove(name);
    }

    public Optional<Profile> getBy(String name) {
        return this.repository.getBy(name);
    }

    public Collection<Profile> getAll() {
        return this.repository.getAll();
    }

    public void addOpinionAbout(String name, Food food, Opinion opinion) {
        Optional<Profile> maybeProfile = this.getBy(name);
        if (maybeProfile.isEmpty()) {
            throw new IllegalArgumentException("Profile to add opinion to was not found");
        }
        Profile profile = maybeProfile.get();
        profile.addOpinionAbout(food, opinion);
        this.repository.update(profile);
    }

    public void addToAvailable(String name, Food food) {
        Optional<Profile> maybeProfile = this.getBy(name);
        if (maybeProfile.isEmpty()) {
            throw new IllegalArgumentException("Profile to add opinion to was not found");
        }
        Profile profile = maybeProfile.get();
        profile.addToAvailable(food);
        this.repository.update(profile);
    }
}
