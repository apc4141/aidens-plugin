package aidensplugin.items.base.attribute;

public enum AttributeOperation {
    ADDITIVE(0),
    MULTIPLY_BASE(1),
    MULTIPLY(2);

    private final int num;

    AttributeOperation(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
