package org.pinkcrazyunicorn.quickie.adapters;

import org.pinkcrazyunicorn.quickie.adapters.callbacks.profile.*;
import org.pinkcrazyunicorn.quickie.adapters.event.Event;
import org.pinkcrazyunicorn.quickie.adapters.event.EventType;
import org.pinkcrazyunicorn.quickie.adapters.callbacks.recipe.RefreshFromDatasourceCallback;
import org.pinkcrazyunicorn.quickie.adapters.callbacks.recipe.ViewMatchingRecipesForCallback;
import org.pinkcrazyunicorn.quickie.adapters.callbacks.recipe.ViewRecipesCallback;
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

        this.registerProfileEvents();
        this.registerRecipeEvents();
        this.registerMatchingEvents();
    }

    public void run() {
        Event event;
        while ((event = ui.getUserEvent()) != null) {
            ui.handleEvent(event);
        }
    }

    private void registerProfileEvents() {
        this.ui.registerEvent(new EventType("addProfile"), new AddProfileCallback(profileService));
        this.ui.registerEvent(new EventType("viewProfile"), new ViewProfileCallback(profileService));
        this.ui.registerEvent(new EventType("viewProfiles"), new ViewProfilesCallback(profileService));
        this.ui.registerEvent(new EventType("setOpinion"), new SetOpinionCallback(profileService));
        this.ui.registerEvent(new EventType("addAvailable"), new AddAvailableCallback(profileService));
        this.ui.registerEvent(new EventType("removeProfile"), new RemoveProfileCallback(profileService));
        this.ui.registerEvent(new EventType("removeOpinion"), new RemoveOpinionCallback(profileService));
        this.ui.registerEvent(new EventType("removeAvailable"), new RemoveAvailableCallback(profileService));
    }

    private void registerRecipeEvents() {
        this.ui.registerEvent(new EventType("refreshFromDatasource"), new RefreshFromDatasourceCallback(recipeService));
        this.ui.registerEvent(new EventType("viewRecipes"), new ViewRecipesCallback(recipeService));
    }

    private void registerMatchingEvents() {
        this.ui.registerEvent(new EventType("viewMatchingRecipesFor"), new ViewMatchingRecipesForCallback(profileService, matchingService));
    }
}
