package aidensplugin.items.base;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class MaterialHelper {

    private static final HashMap<Material, String> names = new HashMap<Material, String>();

    public static void init() {
        names.put(Material.BONE, "Bone");
        names.put(Material.ROTTEN_FLESH, "Rotten Flesh");
        names.put(Material.GUNPOWDER, "Gunpowder");
        names.put(Material.SPIDER_EYE, "Spider Eye");
        names.put(Material.GOLDEN_APPLE, "Golden Apple");
        names.put(Material.ENDER_PEARL, "Ender Pearl");
        names.put(Material.BLAZE_ROD, "Blaze Rod");
        names.put(Material.QUARTZ, "Nether Quartz");
        names.put(Material.IRON_INGOT, "Iron Ingot");
        names.put(Material.GOLD_INGOT, "Gold Ingot");
        names.put(Material.DIAMOND, "Diamond");
        names.put(Material.EMERALD, "Emerald");
        names.put(Material.EGG, "Egg");
        names.put(Material.SPIDER_SPAWN_EGG, "Spider Spawn Egg");
        names.put(Material.STRING, "String");

        //Tools
        names.put(Material.WOODEN_SWORD, "Wooden Sword");
        names.put(Material.STONE_SWORD, "Stone Sword");
        names.put(Material.IRON_SWORD, "Iron Sword");
        names.put(Material.GOLDEN_SWORD, "Golden Sword");
        names.put(Material.DIAMOND_SWORD, "Diamond Sword");
        names.put(Material.NETHERITE_SWORD, "Netherite Sword");
        names.put(Material.WOODEN_PICKAXE, "Wooden Pickaxe");
        names.put(Material.STONE_PICKAXE, "Stone Pickaxe");
        names.put(Material.IRON_PICKAXE, "Iron Pickaxe");
        names.put(Material.GOLDEN_PICKAXE, "Golden Pickaxe");
        names.put(Material.DIAMOND_PICKAXE, "Diamond Pickaxe");
        names.put(Material.NETHERITE_PICKAXE, "Netherite Pickaxe");
        names.put(Material.WOODEN_SHOVEL, "Wooden Shovel"); //WOODEN_SHOVEL
        names.put(Material.STONE_SHOVEL, "Stone Shovel");
        names.put(Material.IRON_SHOVEL, "Iron Shovel");
        names.put(Material.GOLDEN_SHOVEL, "Golden Shovel");
        names.put(Material.DIAMOND_SHOVEL, "Diamond Shovel");
        names.put(Material.NETHERITE_SHOVEL, "Netherite Shovel");
        names.put(Material.WOODEN_HOE, "Wooden Hoe");
        names.put(Material.STONE_HOE, "Stone Hoe");
        names.put(Material.IRON_HOE, "Iron Hoe");
        names.put(Material.GOLDEN_HOE, "Golden Hoe");
        names.put(Material.DIAMOND_HOE, "Diamond Hoe");
        names.put(Material.NETHERITE_HOE, "Netherite Hoe");
        names.put(Material.WOODEN_AXE, "Wooden Axe");
        names.put(Material.STONE_AXE, "Stone Axe");
        names.put(Material.IRON_AXE, "Iron Axe");
        names.put(Material.GOLDEN_AXE, "Golden Axe");
        names.put(Material.DIAMOND_AXE, "Diamond Axe");
        names.put(Material.NETHERITE_AXE, "Netherite Axe");
        names.put(Material.SHEARS, "Shears");
        names.put(Material.BOW, "Bow");
        names.put(Material.CROSSBOW, "Crossbow");

        //Armor
        names.put(Material.LEATHER_HELMET, "Leather Helmet");
        names.put(Material.CHAINMAIL_HELMET, "Chainmail Helmet");
        names.put(Material.IRON_HELMET, "Iron Helmet");
        names.put(Material.GOLDEN_HELMET, "Golden Helmet");
        names.put(Material.DIAMOND_HELMET, "Diamond Helmet");
        names.put(Material.NETHERITE_HELMET, "Netherite Helmet");
        names.put(Material.TURTLE_HELMET, "Turtle Helmet");
        names.put(Material.LEATHER_CHESTPLATE, "Leather Chestplate");
        names.put(Material.CHAINMAIL_CHESTPLATE, "Chainmail Chestplate");
        names.put(Material.IRON_CHESTPLATE, "Iron Chestplate");
        names.put(Material.GOLDEN_CHESTPLATE, "Golden Chestplate");
        names.put(Material.DIAMOND_CHESTPLATE, "Diamond Chestplate");
        names.put(Material.NETHERITE_CHESTPLATE, "Netherite Chestplate");
        names.put(Material.LEATHER_LEGGINGS, "Leather Leggings");
        names.put(Material.CHAINMAIL_LEGGINGS, "Chainmail Leggings");
        names.put(Material.IRON_LEGGINGS, "Iron Leggings");
        names.put(Material.GOLDEN_LEGGINGS, "Golden Leggings");
        names.put(Material.DIAMOND_LEGGINGS, "Diamond Leggings");
        names.put(Material.NETHERITE_LEGGINGS, "Netherite Leggings");
        names.put(Material.LEATHER_BOOTS, "Leather Boots");
        names.put(Material.CHAINMAIL_BOOTS, "Chainmail Boots");
        names.put(Material.IRON_BOOTS, "Iron Boots");
        names.put(Material.GOLDEN_BOOTS, "Golden Boots");
        names.put(Material.DIAMOND_BOOTS, "Diamond Boots");
        names.put(Material.NETHERITE_BOOTS, "Netherite Boots");
        names.put(Material.SHIELD, "Shield");
        names.put(Material.FISHING_ROD, "Fishing Rod");
        names.put(Material.FLINT_AND_STEEL, "Flint and Steel");
    }

    public static boolean isTool(ItemStack itemStack) {
        if(itemStack==null)
            return false;
        String[] endsWith = {"pickaxe", "sword", "shovel", "hoe", "shears", "axe", "bow", "shield", "fishing rod", "flint and steel"};

        for (String s : endsWith) {
            if (getName(itemStack.getType()).toLowerCase().endsWith(s.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isArmor(ItemStack itemStack) {
        if(itemStack==null)
            return false;
        String[] endsWith = {"helmet", "chestplate", "leggings", "boots"};

        for (String s : endsWith) {
            if (getName(itemStack.getType()).toLowerCase().endsWith(s.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static String getName(Material material) {
        String base = material.name();
        String[] splitted = base.split("_");
        StringBuilder newName = new StringBuilder();
        for (String s : splitted)
            newName.append(s.substring(0, 1).toUpperCase()).append(s.substring(1).toLowerCase());
        newName = new StringBuilder(newName.substring(0, newName.length()));
        return newName.toString();
    }
}
