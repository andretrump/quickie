package org.pinkcrazyunicorn.profile;

import java.util.HashSet;

public class ProfileService {
    private ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        super();
        this.repository = repository;
    }

    public void save(Profile profile) {
        this.repository.save(profile);
    }

    public void add(String name) {
        Stock stock = new Stock(new HashSet<>());
        Profile profile = new Profile(name, stock, new HashSet<OpinionAbout>());
        this.repository.save(profile);
    }

    public void remove(String name) {
        this.repository.remove(name);
    }

    public Profile getBy(String name) {
        return this.repository.getBy(name);
    }
}
