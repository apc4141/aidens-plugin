package aidensplugin.items.model;

import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

public class CustomColorArmor extends CustomDurableItem {

    public Color color;

    public CustomColorArmor(String name, String dataName, Material material, int maxDurability, Color color, List<RecipeItem> recipe) {
        super(name, dataName, material, maxDurability, recipe);
        this.color = color;
    }

}
