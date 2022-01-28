package org.pinkcrazyunicorn.profile;

import org.pinkcrazyunicorn.event.EventAnswerData;
import org.pinkcrazyunicorn.event.MapEventAnswerData;
import org.pinkcrazyunicorn.event.StringEventAnswerData;

import java.util.Collection;

public class OpinionMapper {
    public EventAnswerData mapToEventAnswer(Collection<OpinionAbout> opinions) {
        MapEventAnswerData result = new MapEventAnswerData();

        for (OpinionAbout opinion : opinions) {
            result.put(opinion.getFood().getName(), new StringEventAnswerData(opinion.getOpinion().getName()));
        }

        return result;
    }
}
