package org.pinkcrazyunicorn.quickie.domain.profile;

import java.util.Collection;
import java.util.Optional;

public interface ProfileRepository {
    void add(String name);
    Collection<Profile> getAll();
    Optional<Profile> getBy(String name);
    void remove(String name);
    void update(Profile profile);
}
