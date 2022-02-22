package org.pinkcrazyunicorn.quickie.plugins.main;

import org.pinkcrazyunicorn.quickie.application.recipe.MatchingService;
import org.pinkcrazyunicorn.quickie.application.recipe.RecipeService;
import org.pinkcrazyunicorn.quickie.domain.recipe.RecipeRepository;
import org.pinkcrazyunicorn.quickie.plugins.gson.GSONFormatter;
import org.pinkcrazyunicorn.quickie.plugins.jpa.JPAProfileRepository;
import org.pinkcrazyunicorn.quickie.plugins.cli.CommandLineUI;
import org.pinkcrazyunicorn.quickie.plugins.jpa.JPARecipeRepository;
import org.pinkcrazyunicorn.quickie.plugins.jpa.PersistenceManager;
import org.pinkcrazyunicorn.quickie.adapters.Controller;
import org.pinkcrazyunicorn.quickie.domain.profile.ProfileRepository;
import org.pinkcrazyunicorn.quickie.application.profile.ProfileService;

public class Main {
    public static void main(String[] args) {
        PersistenceManager persistenceManager = PersistenceManager.getInstance();
        ProfileRepository profileRepository = new JPAProfileRepository(persistenceManager.getManager());
        RecipeRepository recipeRepository = new JPARecipeRepository(persistenceManager.getManager());
        GSONFormatter formatter = new GSONFormatter();

        CommandLineUI ui = new CommandLineUI(args, formatter);

        ProfileService profileService = new ProfileService(profileRepository);
        RecipeService recipeService = new RecipeService(recipeRepository);
        MatchingService matchingService = new MatchingService(recipeService);

        Controller controller = new Controller(ui, profileService, recipeService, matchingService);
        controller.run();
    }
}
