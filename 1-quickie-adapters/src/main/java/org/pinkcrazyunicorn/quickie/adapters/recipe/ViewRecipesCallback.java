package org.pinkcrazyunicorn.quickie.adapters.recipe;

import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswer;
import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswerData;
import org.pinkcrazyunicorn.quickie.adapters.event.EventCallback;
import org.pinkcrazyunicorn.quickie.adapters.event.EventParameter;
import org.pinkcrazyunicorn.quickie.application.recipe.RecipeService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ViewRecipesCallback implements EventCallback {
    private final RecipeService service;
    private final RecipeMapper mapper;

    public ViewRecipesCallback(RecipeService service) {
        this.service = service;
        this.mapper = new RecipeMapper();
    }

    @Override
    public EventAnswer call(Map<String, String> data) {
        EventAnswerData eventData = this.mapper.mapManyToEventAnswer(this.service.getAll());
        return new EventAnswer("", eventData);
    }

    @Override
    public Collection<EventParameter> getRequiredParameters() {
        return List.of();
    }
}
