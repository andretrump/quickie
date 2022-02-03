package org.pinkcrazyunicorn.quickie.adapters.recipe;

import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswer;
import org.pinkcrazyunicorn.quickie.adapters.event.EventCallback;
import org.pinkcrazyunicorn.quickie.adapters.event.EventParameter;
import org.pinkcrazyunicorn.quickie.application.recipe.Datasource;
import org.pinkcrazyunicorn.quickie.application.recipe.RecipeService;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RefreshFromDatasourceCallback implements EventCallback {
    private final RecipeService service;

    public RefreshFromDatasourceCallback(RecipeService service) {
        this.service = service;
    }

    @Override
    public EventAnswer call(Map<String, String> data) {
        String datasourceClassName = data.get(EventParameter.Datasource.getName());

        Object datasourceObject;
        try {
            datasourceObject = Class.forName(datasourceClassName).getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            return new EventAnswer("Error: Datasource needs empty constructor");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return new EventAnswer("Error: Class for datasource was not found");
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            return new EventAnswer("Error: Error while instantiating datasource");
        }
        if (!(datasourceObject instanceof Datasource)) {
            return new EventAnswer("Error: Class does not implement Datasource");
        }
        Datasource datasource = (Datasource) datasourceObject;
        this.service.refreshFrom(datasource);

        return new EventAnswer("Refreshed recipes");
    }

    @Override
    public Collection<EventParameter> getRequiredParameters() {
        return List.of(EventParameter.Datasource);
    }
}
