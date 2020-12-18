package aidensplugin.items.base.attribute;

public enum AttributeType {
    ATTACK_DAMAGE("generic.attackDamage"),
    ATTACK_SPEED("generic.attackSpeed"),
    ATTACK_KNOCKBACK("generic.attackKnockback"),
    MAX_HEALTH("generic.maxHealth"),
    KNOCKBACK_RESISTANCE("generic.knockbackResistance"),
    MOVEMENT_SPEED("generic.movementSpeed"),
    ARMOR("generic.armor"),
    ARMOR_TOUGHNESS("generic.armorToughness"),
    LUCK("generic.luck");

    private final String name;

    AttributeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
