package aidensplugin.items.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileShotListener implements Listener {

    @EventHandler
    public void onProjectileShot(ProjectileLaunchEvent event)
    {
        Entity entity = event.getEntity();
        if(entity instanceof Snowball)
        {
            Snowball snowball = (Snowball) entity;
            Entity shooter = (Entity) snowball.getShooter();
            if(shooter instanceof Player)
            {
                Player player = (Player) shooter;

                //Do stuff maybe
            }
        }
    }
}
