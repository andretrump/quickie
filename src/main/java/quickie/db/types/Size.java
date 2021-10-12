package quickie.db.types;

public enum Size {
    BIG("gross(e)"),
    MEDIUM("mittlere"),
    SMALL("klein(e)"),
    ;

    private String text;

    Size(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
