package org.pinkcrazyunicorn.quickie.adapters.mappers.profile;

import org.pinkcrazyunicorn.quickie.domain.Food;
import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswerData;
import org.pinkcrazyunicorn.quickie.adapters.event.MapEventAnswerData;
import org.pinkcrazyunicorn.quickie.adapters.event.StringEventAnswerData;
import org.pinkcrazyunicorn.quickie.domain.profile.Opinion;

import java.util.Map;

public class OpinionMapper {
    public Opinion fromString(String string) {
        switch (string) {
            case "Foodgasm": return Opinion.Foodgasm;
            case "Love": return Opinion.Love;
            case "Like": return Opinion.Like;
            case "Indifferent": return Opinion.Indifferent;
            case "Dislike": return Opinion.Dislike;
            case "Hate": return Opinion.Hate;
            case "Dealbreaker": return Opinion.Dealbreaker;
            default: return null;
        }
    }

    public EventAnswerData mapToEventAnswer(Opinion opinion) {
        return new StringEventAnswerData(opinion.getName());
    }

    public EventAnswerData mapManyToEventAnswer(Map<Food, Opinion> opinions) {
        MapEventAnswerData result = new MapEventAnswerData();

        for (Map.Entry<Food, Opinion> opinionAbout : opinions.entrySet()) {
            String key = opinionAbout.getKey().getName();
            EventAnswerData value = this.mapToEventAnswer(opinionAbout.getValue());
            result.put(key, value);
        }

        return result;
    }
}
