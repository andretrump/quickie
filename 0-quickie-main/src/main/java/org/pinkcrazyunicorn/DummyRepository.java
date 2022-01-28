package org.pinkcrazyunicorn;

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
        return Optional.empty();
    }

    @Override
    public void remove(String profile) {

    }
}
