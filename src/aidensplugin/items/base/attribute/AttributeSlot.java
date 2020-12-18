package aidensplugin.items.base.attribute;

public enum AttributeSlot {
    ANY("any"),
    MAINHAND("mainhand"),
    OFFHAND("offhand"),
    FEET("feet"),
    LEGS("legs"),
    CHEST("chest"),
    HEAD("head");

    private final String name;

    AttributeSlot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
