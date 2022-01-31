package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.Food;

import java.util.Collection;
import java.util.Optional;

public class ProfileService {
    private final ProfileRepository repository;

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

    public void setOpinionAbout(String name, Food food, Opinion opinion) {
        Profile profile = this.getBy(name)
                .orElseThrow(() -> new IllegalArgumentException("Profile to add opinion to was not found"));
        profile.setOpinionAbout(food, opinion);
        this.repository.update(profile);
    }

    public void addToAvailable(String name, Food food) {
        Profile profile = this.getBy(name)
                .orElseThrow(() -> new IllegalArgumentException("Profile to add opinion to was not found"));
        profile.addToAvailable(food);
        this.repository.update(profile);
    }

    public void removeOpinionAbout(String name, Food food) {
        Profile profile = this.getBy(name)
                .orElseThrow(() -> new IllegalArgumentException("Profile to add opinion to was not found"));
        profile.removeOpinionAbout(food);
        this.repository.update(profile);
    }

    public void markUnavailable(String name, Food food) {
        Profile profile = this.getBy(name)
                .orElseThrow(() -> new IllegalArgumentException("Profile to add opinion to was not found"));
        profile.markUnavailable(food);
        this.repository.update(profile);
    }
}
