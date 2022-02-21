package org.pinkcrazyunicorn.quickie.adapters.callbacks.recipe;

import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswer;
import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswerData;
import org.pinkcrazyunicorn.quickie.adapters.event.EventCallback;
import org.pinkcrazyunicorn.quickie.adapters.event.EventParameter;
import org.pinkcrazyunicorn.quickie.adapters.mappers.recipe.RecipeMapper;
import org.pinkcrazyunicorn.quickie.application.recipe.RecipeService;
import org.pinkcrazyunicorn.quickie.domain.recipe.Recipe;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ViewRecipeCallback implements EventCallback {
    private final RecipeService service;
    private final RecipeMapper mapper;

    public ViewRecipeCallback(RecipeService service) {
        this.service = service;
        this.mapper = new RecipeMapper();
    }

    @Override
    public EventAnswer call(Map<String, String> data) {
        String origin = data.get(EventParameter.RecipeOrigin.getName());
        Optional<Recipe> recipe = this.service.getBy(origin);

        if (recipe.isEmpty()) {
            return new EventAnswer("Recipe from " + origin + " was not found");
        }

        return new EventAnswer(origin, this.mapper.mapToEventAnswer(recipe.get()));
    }

    @Override
    public Collection<EventParameter> getRequiredParameters() {
        return List.of(EventParameter.RecipeOrigin);
    }
}
