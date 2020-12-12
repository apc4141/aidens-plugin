package aidensplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class EnderBowEvents implements Listener {

    public static final double ARROW_SPEED_MULTIPLIER = 0.65D;

    private final Plugin main;

    public EnderBowEvents(Plugin plugin)
    {
        main = plugin;
    }

    @EventHandler
    public void onPlayerShoot(EntityShootBowEvent event)
    {
        Player player = (Player) event.getEntity();
        Arrow arrow = (Arrow) event.getProjectile();
        ItemStack bow = event.getBow();

        Bukkit.broadcastMessage("Bow: "+bow.toString()+"; Projectile: "+arrow.toString());

        //Check if bow is ender bow
        if(CustomMetaManager.hasTag(bow, "Ender Bow", "True"))
        {
            Bukkit.broadcastMessage("Testing 2");

            arrow.setGravity(false);
            arrow.setVelocity(arrow.getVelocity().multiply(ARROW_SPEED_MULTIPLIER));

            //Start an arrow task that checks if arrow is in block
            new EnderArrowTask(arrow, player).runTaskTimer(main, 0, 1);
        }
    }
}
