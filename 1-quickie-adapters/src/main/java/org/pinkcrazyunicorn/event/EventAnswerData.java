package org.pinkcrazyunicorn.event;

public interface EventAnswerData {
    default String toJson(){
        return this.toJson(0);
    }
    default String indentationOf(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length*4; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }
    String toJson(int indentationLevel);
}
