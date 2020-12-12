package aidensplugin;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EnderArrowTask extends BukkitRunnable {

    private final Arrow arrow;
    private final Player player;

    public EnderArrowTask(Arrow arrow, Player player) {
        this.arrow = arrow;
        this.player = player;
    }

    @Override
    public void run() {
        if(arrow.isInBlock() || arrow.isOnGround()) {
            Location arrowLoc = arrow.getLocation();
            player.teleport(arrowLoc);
            //Play ender sounds
            this.cancel();
        }
    }
}
