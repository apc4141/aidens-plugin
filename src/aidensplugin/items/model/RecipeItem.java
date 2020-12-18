package aidensplugin.items.model;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;

import java.util.HashMap;

public class RecipeItem {
    private static final HashMap<String, Material> RECIPE_ITEMS = new HashMap<>();

    public Material material;
    public int amount;
    public boolean requireTag;
    public String tag;

    public RecipeItem(Material material, int amount) {
        this.material = material;
        this.amount = amount;
        requireTag = false;
        tag = null;
    }

    public RecipeItem requireTag(String tag) {
        if (tag == null) {
            requireTag = false;
            this.tag = null;
        } else {
            requireTag = true;
            this.tag = tag;
        }
        return this;
    }


    public static void addItem(String item, Material material) {
        RECIPE_ITEMS.put(item, material);
    }

    public static RecipeItem createItem(String item, int amount) {
        return new RecipeItem(RECIPE_ITEMS.get(item), amount).requireTag(item);
    }
}
