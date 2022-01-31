package org.pinkcrazyunicorn;

import org.pinkcrazyunicorn.jpa.JPAProfileRepository;
import org.pinkcrazyunicorn.cli.CommandLineUI;
import org.pinkcrazyunicorn.jpa.PersistenceManager;
import org.pinkcrazyunicorn.profile.ProfileRepository;
import org.pinkcrazyunicorn.profile.ProfileService;

public class Main {
    public static void main(String[] args) {
        PersistenceManager persistenceManager = PersistenceManager.getInstance();
        ProfileRepository profileRepository = new JPAProfileRepository(persistenceManager.getManager());

        CommandLineUI ui = new CommandLineUI(args);
        Controller controller = new Controller(ui, new ProfileService(profileRepository));
        controller.run();
    }
}
