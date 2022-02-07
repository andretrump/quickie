package org.pinkcrazyunicorn.quickie.adapters;

import org.pinkcrazyunicorn.quickie.adapters.event.Event;
import org.pinkcrazyunicorn.quickie.adapters.event.EventType;
import org.pinkcrazyunicorn.quickie.adapters.profile.*;
import org.pinkcrazyunicorn.quickie.adapters.recipe.RefreshFromDatasourceCallback;
import org.pinkcrazyunicorn.quickie.adapters.recipe.ViewMatchingRecipesForCallback;
import org.pinkcrazyunicorn.quickie.adapters.recipe.ViewRecipesCallback;
import org.pinkcrazyunicorn.quickie.application.profile.ProfileService;
import org.pinkcrazyunicorn.quickie.application.recipe.MatchingService;
import org.pinkcrazyunicorn.quickie.application.recipe.RecipeService;

public class Controller {
    private final UI ui;
    private final ProfileService profileService;
    private final RecipeService recipeService;
    private final MatchingService matchingService;

    public Controller(UI ui, ProfileService profileService, RecipeService recipeService, MatchingService matchingService) {
        super();

        this.ui = ui;
        this.profileService = profileService;
        this.recipeService = recipeService;
        this.matchingService = matchingService;

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
        this.ui.registerEvent(new EventType("setOpinion"), new SetOpinionCallback(profileService));
        this.ui.registerEvent(new EventType("addAvailable"), new AddAvailableCallback(profileService));
        this.ui.registerEvent(new EventType("removeProfile"), new RemoveProfileCallback(profileService));
        this.ui.registerEvent(new EventType("removeOpinion"), new RemoveOpinionCallback(profileService));
        this.ui.registerEvent(new EventType("removeAvailable"), new RemoveAvailableCallback(profileService));
        this.ui.registerEvent(new EventType("refreshFromDatasource"), new RefreshFromDatasourceCallback(recipeService));
        this.ui.registerEvent(new EventType("viewRecipes"), new ViewRecipesCallback(recipeService));
        this.ui.registerEvent(new EventType("viewMatchingRecipesFor"), new ViewMatchingRecipesForCallback(profileService, matchingService));
    }
}
