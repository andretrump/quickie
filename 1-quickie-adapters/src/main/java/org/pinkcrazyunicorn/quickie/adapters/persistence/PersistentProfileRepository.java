package org.pinkcrazyunicorn.quickie.adapters.persistence;

import org.pinkcrazyunicorn.quickie.domain.profile.Profile;
import org.pinkcrazyunicorn.quickie.domain.profile.ProfileRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class PersistentProfileRepository implements ProfileRepository {
    protected abstract Collection<? extends PersistentProfile> persistentGetAll();
    protected abstract Optional<? extends PersistentProfile> persistentGetBy(String name);
    protected abstract void persistentAdd(PersistentProfile profile);
    protected abstract void persistentUpdate(PersistentProfile profile);
    protected abstract void persistentRemove(PersistentProfile profile);

    private final PersistentProfileMapper mapper;

    protected PersistentProfileRepository(PersistentProfileFactory factory) {
        super();
        this.mapper = new PersistentProfileMapper(factory);
    }

    @Override
    public void add(String name) {
        Profile profile = new Profile(name);
        PersistentProfile persistent = this.mapper.mapToPersistent(profile);
        this.persistentAdd(persistent);
    }

    @Override
    public Collection<Profile> getAll() {
        return this.persistentGetAll().stream()
                .map(this.mapper::mapFromPersistent)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Profile> getBy(String name) {
        Optional<? extends PersistentProfile> persistent = this.persistentGetBy(name);
        if (persistent.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.mapper.mapFromPersistent(persistent.get()));
    }

    @Override
    public void remove(String name) {
        Optional<? extends PersistentProfile> maybePersistent = this.persistentGetBy(name);
        if (maybePersistent.isEmpty()) {
            return;
        }
        this.persistentRemove(maybePersistent.get());
    }

    @Override
    public void update(Profile profile) {
        PersistentProfile persistent = this.mapper.mapToPersistent(profile);
        this.persistentUpdate(persistent);
    }
}
