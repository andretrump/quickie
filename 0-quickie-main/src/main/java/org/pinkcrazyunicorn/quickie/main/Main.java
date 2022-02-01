package org.pinkcrazyunicorn.quickie.main;

import org.pinkcrazyunicorn.quickie.plugins.jpa.JPAProfileRepository;
import org.pinkcrazyunicorn.quickie.plugins.cli.CommandLineUI;
import org.pinkcrazyunicorn.quickie.plugins.jpa.PersistenceManager;
import org.pinkcrazyunicorn.quickie.adapters.Controller;
import org.pinkcrazyunicorn.quickie.domain.profile.ProfileRepository;
import org.pinkcrazyunicorn.quickie.application.profile.ProfileService;

public class Main {
    public static void main(String[] args) {
        PersistenceManager persistenceManager = PersistenceManager.getInstance();
        ProfileRepository profileRepository = new JPAProfileRepository(persistenceManager.getManager());

        CommandLineUI ui = new CommandLineUI(args);
        Controller controller = new Controller(ui, new ProfileService(profileRepository));
        controller.run();
    }
}
