package aidensplugin.items.model;

import org.bukkit.Material;

import java.util.List;

public class CustomDurableItem extends CustomItem {

    public int maxDurability;

    public CustomDurableItem(String name, String dataName, Material material, int maxDurability,  List<RecipeItem> recipe) {
        super(name, dataName, material, recipe);
        this.maxDurability = maxDurability;
    }
}
