package org.pinkcrazyunicorn.quickie.adapters.event;

public class NumberEventAnswerData<T extends Number> implements EventAnswerData {
    T data;

    public NumberEventAnswerData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
