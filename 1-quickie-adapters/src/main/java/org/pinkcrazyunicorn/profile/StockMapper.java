package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.Food;
import org.pinkcrazyunicorn.event.EventAnswerData;
import org.pinkcrazyunicorn.event.ListEventAnswerData;
import org.pinkcrazyunicorn.event.StringEventAnswerData;

public class StockMapper {
    public EventAnswerData mapToEventAnswer(Stock stock) {
        ListEventAnswerData result = new ListEventAnswerData();

        for (Food food : stock.getFood()) {
            result.add(new StringEventAnswerData(food.getName()));
        }

        return result;
    }
}
