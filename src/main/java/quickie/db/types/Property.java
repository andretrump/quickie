package quickie.db.types;

public enum Property {
    LATEST_DOWNLOAD("latestDownload");

    private final String text;

    Property(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
