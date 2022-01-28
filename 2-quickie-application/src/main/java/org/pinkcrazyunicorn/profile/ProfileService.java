package org.pinkcrazyunicorn.profile;

import java.util.HashSet;
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
}
