package aidensplugin.items.base;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class MinersGemBlockWhiteList {

    private static final List<Material> whitelist = new ArrayList<>();

    public static void init()
    {
        whitelist.add(Material.DIAMOND_ORE);
        whitelist.add(Material.IRON_ORE);
        whitelist.add(Material.EMERALD_ORE);
        whitelist.add(Material.GOLD_ORE);
        whitelist.add(Material.LAPIS_ORE);
        whitelist.add(Material.NETHER_QUARTZ_ORE);
        whitelist.add(Material.COAL_ORE);
        whitelist.add(Material.REDSTONE_ORE);
        whitelist.add(Material.OAK_PLANKS);
        whitelist.add(Material.CHEST);
        whitelist.add(Material.SPAWNER);
        whitelist.add(Material.END_PORTAL_FRAME);
        whitelist.add(Material.BEDROCK);
        whitelist.add(Material.OBSIDIAN);
        whitelist.add(Material.WATER);
        whitelist.add(Material.LAVA);
        whitelist.add(Material.ANCIENT_DEBRIS);
    }

    public static List<Material> getWhitelist()
    {
        return whitelist;
    }
}
