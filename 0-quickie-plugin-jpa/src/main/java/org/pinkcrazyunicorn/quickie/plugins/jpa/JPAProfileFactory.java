package org.pinkcrazyunicorn.quickie.plugins.jpa;

import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentProfile;
import org.pinkcrazyunicorn.quickie.adapters.persistence.PersistentProfileFactory;

public class JPAProfileFactory implements PersistentProfileFactory {

    @Override
    public PersistentProfile createEmpty() {
        return new JPAProfile();
    }
}
