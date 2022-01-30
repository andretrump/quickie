package org.pinkcrazyunicorn.profile;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractJPAProfileRepository implements ProfileRepository {
    protected abstract Collection<JPAProfile> getAllJPA();
    protected abstract Optional<JPAProfile> getByJPA(String name);
    protected abstract void addJPA(JPAProfile profile);
    protected abstract void updateJPA(JPAProfile profile);
    protected abstract void removeJPA(JPAProfile jpaProfile);

    private ProfileMapper mapper;

    public AbstractJPAProfileRepository() {
        this.mapper = new ProfileMapper();
    }

    @Override
    public Collection<Profile> getAll() {
        return this.getAllJPA().stream()
                .map(jpa -> this.mapper.fromJPAProfile(jpa))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Profile> getBy(String name) {
        Optional<JPAProfile> jpa = this.getByJPA(name);
        if (jpa.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.mapper.fromJPAProfile(jpa.get()));
    }

    @Override
    public void add(String name) {
        Profile profile = new Profile(name);
        JPAProfile jpa = this.mapper.mapToJPAProfile(profile);
        this.addJPA(jpa);
    }

    @Override
    public void update(Profile profile) {
        JPAProfile jpa = this.mapper.mapToJPAProfile(profile);
        this.updateJPA(jpa);
    }

    @Override
    public void remove(String name) {
        Optional<JPAProfile> maybeJpa = this.getByJPA(name);
        if (maybeJpa.isEmpty()) {
            return;
        }
        this.removeJPA(maybeJpa.get());
    }
}
