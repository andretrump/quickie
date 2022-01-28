package org.pinkcrazyunicorn.event;

public class StringEventAnswerData implements EventAnswerData {
    String data;

    public StringEventAnswerData(String data) {
        this.data = data;
    }

    @Override
    public String toJson(int indentationLevel) {
        return "\"" + this.data + "\"";
    }
}
