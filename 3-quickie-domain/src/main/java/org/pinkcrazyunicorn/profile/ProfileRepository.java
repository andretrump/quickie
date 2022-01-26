package org.pinkcrazyunicorn.profile;

public interface ProfileRepository {
    void save(Profile profile);
    Iterable<Profile> getAll();

    Profile getBy(String name);

    void remove(String profile);
}
