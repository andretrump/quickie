package org.pinkcrazyunicorn.quickie.adapters.recipe;

import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswer;
import org.pinkcrazyunicorn.quickie.adapters.event.EventCallback;
import org.pinkcrazyunicorn.quickie.adapters.event.EventParameter;
import org.pinkcrazyunicorn.quickie.adapters.event.ListEventAnswerData;
import org.pinkcrazyunicorn.quickie.application.profile.ProfileService;
import org.pinkcrazyunicorn.quickie.application.recipe.MatchingService;
import org.pinkcrazyunicorn.quickie.domain.profile.Profile;
import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;

import java.util.*;

public class ViewMatchingRecipesFor implements EventCallback {
    private final ProfileService profileService;
    private final MatchingService matchingService;
    private final RecipeMapper recipeMapper;

    public ViewMatchingRecipesFor(ProfileService profileService, MatchingService matchingService) {
        this.profileService = profileService;
        this.matchingService = matchingService;
        recipeMapper = new RecipeMapper();
    }

    @Override
    public EventAnswer call(Map<String, String> data) {
        String profileString = data.get(EventParameter.Profile.getName());
        String recipeIdString = data.get(EventParameter.RecipeId.getName());

        Optional<Profile> profile = this.profileService.getBy(profileString);
        if (profile.isEmpty()) {
            return new EventAnswer("Error: Need to specify existing profile to be used while matching");
        }
        UUID id = UUID.fromString(recipeIdString);

        return new EventAnswer("matching recipes:",
                this.recipeMapper.mapManyToEventAnswer(
                        this.matchingService.getMatchingRecipesFor(id, profile.get())
                )
        );
    }

    @Override
    public Collection<EventParameter> getRequiredParameters() {
        return List.of(
                EventParameter.Profile,
                EventParameter.RecipeId
        );
    }
}
