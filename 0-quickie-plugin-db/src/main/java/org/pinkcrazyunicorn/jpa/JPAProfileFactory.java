package org.pinkcrazyunicorn.jpa;

import org.pinkcrazyunicorn.persistence.PersistentProfile;
import org.pinkcrazyunicorn.persistence.PersistentProfileFactory;

public class JPAProfileFactory implements PersistentProfileFactory {

    @Override
    public PersistentProfile createEmpty() {
        return new JPAProfile();
    }
}
