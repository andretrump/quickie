package org.pinkcrazyunicorn.profile;

public interface ProfileRepository {
    void add(String name);
    Iterable<Profile> getAll();

    Profile getBy(String name);

    void remove(String profile);
}
