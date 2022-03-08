package org.pinkcrazyunicorn.quickie.plugins.main;

import org.pinkcrazyunicorn.quickie.application.recipe.MatchingService;
import org.pinkcrazyunicorn.quickie.application.recipe.RecipeService;
import org.pinkcrazyunicorn.quickie.domain.recipe.RecipeRepository;
import org.pinkcrazyunicorn.quickie.plugins.gson.GSONFormatter;
import org.pinkcrazyunicorn.quickie.plugins.jpa.JPAProfileRepository;
import org.pinkcrazyunicorn.quickie.plugins.cli.CommandLineUI;
import org.pinkcrazyunicorn.quickie.plugins.jpa.JPARecipeRepository;
import org.pinkcrazyunicorn.quickie.adapters.Controller;
import org.pinkcrazyunicorn.quickie.domain.profile.ProfileRepository;
import org.pinkcrazyunicorn.quickie.application.profile.ProfileService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("quickie");
        ProfileRepository profileRepository = new JPAProfileRepository(entityManagerFactory.createEntityManager());
        RecipeRepository recipeRepository = new JPARecipeRepository(entityManagerFactory.createEntityManager());
        GSONFormatter formatter = new GSONFormatter();

        CommandLineUI ui = new CommandLineUI(args, formatter);

        ProfileService profileService = new ProfileService(profileRepository);
        RecipeService recipeService = new RecipeService(recipeRepository);
        MatchingService matchingService = new MatchingService(recipeService);

        Controller controller = new Controller(ui, profileService, recipeService, matchingService);
        controller.run();
    }
}
