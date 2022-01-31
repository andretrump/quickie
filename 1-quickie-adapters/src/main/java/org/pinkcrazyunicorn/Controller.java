package org.pinkcrazyunicorn;

import org.pinkcrazyunicorn.event.Event;
import org.pinkcrazyunicorn.event.EventType;
import org.pinkcrazyunicorn.profile.*;

public class Controller {
    private final UI ui;
    private final ProfileService profileService;

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
        this.ui.registerEvent(new EventType("viewProfiles"), new ViewProfilesCallback(profileService));
        this.ui.registerEvent(new EventType("addOpinion"), new AddOpinionCallback(profileService));
        this.ui.registerEvent(new EventType("addAvailable"), new AddAvailableCallback(profileService));
        this.ui.registerEvent(new EventType("removeProfile"), new RemoveProfileCallback(profileService));
    }
}
