package org.pinkcrazyunicorn.quickie.adapters;

import org.pinkcrazyunicorn.quickie.adapters.event.EventAnswerData;
import org.pinkcrazyunicorn.quickie.adapters.event.ListEventAnswerData;
import org.pinkcrazyunicorn.quickie.adapters.event.StringEventAnswerData;
import org.pinkcrazyunicorn.quickie.domain.Food;

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

    public Food fromString(String foodString) {
        return new Food(foodString);
    }
}
