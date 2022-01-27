package org.pinkcrazyunicorn;

import org.pinkcrazyunicorn.profile.AddProfileCallback;
import org.pinkcrazyunicorn.profile.ProfileService;
import org.pinkcrazyunicorn.profile.ViewProfileCallback;

public class Controller {
    private UI ui;
    private ProfileService profileService;

    public Controller(UI ui, ProfileService profileService) {
        super();

        this.ui = ui;
        this.profileService = profileService;

        this.registerProfile();
    }

    public void run() {
        Event event;
        while ((event = ui.getUserEvent()) != null) {
            ui.handleEvent(event);
        }
    }

    private void registerProfile() {
        this.ui.registerEvent(new EventType("addProfile"), new AddProfileCallback(profileService));
        this.ui.registerEvent(new EventType("viewProfile"), new ViewProfileCallback(profileService));
    }
}
