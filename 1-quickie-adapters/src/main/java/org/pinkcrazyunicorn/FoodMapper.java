package org.pinkcrazyunicorn;

import org.pinkcrazyunicorn.event.EventAnswerData;
import org.pinkcrazyunicorn.event.ListEventAnswerData;
import org.pinkcrazyunicorn.event.StringEventAnswerData;

import java.util.Collection;

public class FoodMapper {
    public FoodMapper() {
        super();
    }

    public EventAnswerData mapManyToEventAnswer(Collection<Food> foods) {
        ListEventAnswerData result = new ListEventAnswerData();

        foods.forEach(food -> result.add(this.mapToEventAnswer(food)));

        return result;
    }

    public EventAnswerData mapToEventAnswer(Food food) {
        return new StringEventAnswerData(food.getName());
    }
}
