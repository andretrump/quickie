package org.pinkcrazyunicorn.jpa;

import org.pinkcrazyunicorn.persistence.PersistentProfile;
import org.pinkcrazyunicorn.persistence.PersistentProfileRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class JPAProfileRepository extends PersistentProfileRepository {
    private final EntityManager entityManager;

    public JPAProfileRepository(EntityManager entityManager) {
        super(new JPAProfileFactory());
        this.entityManager = entityManager;
    }

    @Override
    protected Collection<JPAProfile> persistentGetAll() {
        TypedQuery<JPAProfile> query = this.entityManager.createQuery("SELECT p FROM JPAProfile p", JPAProfile.class);
        return query.getResultList();
    }

    @Override
    protected Optional<? extends PersistentProfile> persistentGetBy(String name) {
        TypedQuery<JPAProfile> query = this.entityManager.createQuery("SELECT p FROM JPAProfile p WHERE p.name = ?1", JPAProfile.class);
        query.setParameter(1, name);
        try {
            JPAProfile profile = query.getSingleResult();
            return Optional.of(profile);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    protected void persistentAdd(PersistentProfile profile) {
        if (!(profile instanceof JPAProfile)) {
            throw new IllegalArgumentException("JPAProfileRepository only supports JPAProfiles");
        }
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        try {
            this.entityManager.persist((JPAProfile)profile);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    protected void persistentUpdate(PersistentProfile profile) {
        if (!(profile instanceof JPAProfile)) {
            throw new IllegalArgumentException("JPAProfileRepository only supports JPAProfiles");
        }
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        try {
            this.entityManager.merge((JPAProfile)profile);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    protected void persistentRemove(PersistentProfile profile) {
        if (!(profile instanceof JPAProfile)) {
            throw new IllegalArgumentException("JPAProfileRepository only supports JPAProfiles");
        }
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        try {
            this.entityManager.remove((JPAProfile)profile);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
