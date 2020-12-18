package aidensplugin.items.base;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class CustomWorldManager {

    public static void strikeLightning(Player player)
    {
        World world = player.getWorld();
        world.strikeLightning(player.getLocation());
    }

    public static void strikeLightning(World world, double x, double y, double z)
    {
        world.strikeLightning(new Location(world, x, y, z));
    }

    public static boolean playerInWater(Player player)
    {
        World world = player.getWorld();
        Block bottomBlock = world.getBlockAt(player.getLocation());
        Block topBlock = world.getBlockAt(player.getEyeLocation());

        return bottomBlock.getType() == Material.WATER || topBlock.getType() == Material.WATER;
    }

    public static boolean playerUnderWater(Player player)
    {
        World world = player.getWorld();
        Block topBlock = world.getBlockAt(player.getEyeLocation());

        return topBlock.getType() == Material.WATER;
    }
}
