package quickie.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
    private static PersistenceManager INSTANCE;
    private final EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("quickie");

    private PersistenceManager() {}

    public static PersistenceManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PersistenceManager();
        }
        return INSTANCE;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
}
