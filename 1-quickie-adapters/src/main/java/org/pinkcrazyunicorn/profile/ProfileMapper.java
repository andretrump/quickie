package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.event.EventAnswerData;
import org.pinkcrazyunicorn.event.MapEventAnswerData;
import org.pinkcrazyunicorn.event.StringEventAnswerData;

public class ProfileMapper {
    StockMapper stockMapper;
    OpinionMapper opinionMapper;

    public ProfileMapper() {
        this.stockMapper = new StockMapper();
        this.opinionMapper = new OpinionMapper();
    }

    public EventAnswerData mapToEventAnswer(Profile profile) {
        MapEventAnswerData result = new MapEventAnswerData();

        result.put("name", new StringEventAnswerData(profile.getName()));
        result.put("stock", this.stockMapper.mapToEventAnswer(profile.getStock()));
        result.put("opinions", this.opinionMapper.mapToEventAnswer(profile.getOpinions()));

        return result;
    }
}
