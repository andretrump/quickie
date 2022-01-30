package org.pinkcrazyunicorn.jpa;

import org.pinkcrazyunicorn.profile.AbstractJPAProfileRepository;
import org.pinkcrazyunicorn.profile.JPAProfile;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;

public class JPAProfileRepository extends AbstractJPAProfileRepository {
    private final EntityManager entityManager;

    public JPAProfileRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    protected Collection<JPAProfile> getAllJPA() {
        TypedQuery<JPAProfile> query = this.entityManager.createQuery("SELECT p FROM JPAProfile p", JPAProfile.class);
        return query.getResultList();
    }

    @Override
    protected Optional<JPAProfile> getByJPA(String name) {
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
    protected void addJPA(JPAProfile profile) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();
        try {
            this.entityManager.persist(profile);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void remove(String name) {

    }
}
