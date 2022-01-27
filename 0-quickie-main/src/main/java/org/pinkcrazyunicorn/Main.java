package org.pinkcrazyunicorn;

import org.pinkcrazyunicorn.Controller;
import org.pinkcrazyunicorn.cli.CommandLineUI;
import org.pinkcrazyunicorn.profile.ProfileService;

public class Main {
    public static void main(String[] args) {
        CommandLineUI ui = new CommandLineUI(args);
        Controller controller = new Controller(ui, new ProfileService(new DummyRepository()));
        controller.run();
    }
}
