package quickie.db.types;

public enum Unit {
    BIG_PINCH("gross(e) Prise"),
    BUNCH("Bund"),
    CLOVE("Zehe"),
    CUP("Tasse"),
    GLASS("Glas"),
    GRAMM("Gramm"),
    HANDFUL("Handvoll"),
    KNIFE_TIP("Messerspitze"),
    LEAF("Blatt"),
    LITER("Liter"),
    MILLILITER("milliliter"),
    PACK("Packung(en)"),
    PIECE("Stück(e)"),
    PINCH_KNIFE_TIP("Prise, Msp."),
    PINCH("Prise"),
    SLICE("Scheibe(n)"),
    SPLASH("Schuss, Spur"),
    STICK("Stange(n)/Zweig(e)"),
    TABLESPOON("Esslöffel"),
    TEASPOON("Teelöffel"),
    ;

    private String text;

    Unit(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
