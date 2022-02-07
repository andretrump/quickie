package org.pinkcrazyunicorn.quickie.adapters.event;

public class StringEventAnswerData implements EventAnswerData {
    String data;

    public StringEventAnswerData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
