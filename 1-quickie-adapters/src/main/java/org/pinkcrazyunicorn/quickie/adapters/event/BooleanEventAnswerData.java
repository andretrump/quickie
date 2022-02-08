package org.pinkcrazyunicorn.quickie.adapters.event;

public class BooleanEventAnswerData implements EventAnswerData {
    private boolean data;

    public BooleanEventAnswerData(boolean data) {
        this.data = data;
    }

    public boolean getData() {
        return data;
    }
}
