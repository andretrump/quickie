package org.pinkcrazyunicorn.profile;

import java.util.Optional;

public interface ProfileRepository {
    void add(String name);
    Iterable<Profile> getAll();

    Optional<Profile> getBy(String name);

    void remove(String profile);
}
