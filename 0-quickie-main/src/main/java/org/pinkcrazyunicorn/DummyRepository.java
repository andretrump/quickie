package org.pinkcrazyunicorn;

import org.pinkcrazyunicorn.profile.Profile;
import org.pinkcrazyunicorn.profile.ProfileRepository;

public class DummyRepository implements ProfileRepository {
    @Override
    public void add(String name) {

    }

    @Override
    public Iterable<Profile> getAll() {
        return null;
    }

    @Override
    public Profile getBy(String name) {
        return null;
    }

    @Override
    public void remove(String profile) {

    }
}
