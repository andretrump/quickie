package org.pinkcrazyunicorn.event;

public class NumberEventAnswerData<T extends Number> implements EventAnswerData {
    T data;

    public NumberEventAnswerData(T data) {
        this.data = data;
    }

    @Override
    public String toJson() {
        return this.data.toString();
    }
}
