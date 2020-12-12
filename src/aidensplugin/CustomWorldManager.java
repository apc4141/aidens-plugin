package aidensplugin;

import org.bukkit.Location;
import org.bukkit.World;
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
}
