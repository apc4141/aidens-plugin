package aidensplugin;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;

public class TripleJumper implements Listener {

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event)
    {
        Player player = event.getPlayer();
        ItemStack playerBoots = player.getInventory().getBoots();
        if(CustomMetaManager.hasTag(playerBoots, "Mechanical Boots", "True")) {
            if (player.getGameMode() == GameMode.CREATIVE)
                return;
            event.setCancelled(true);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.setVelocity(player.getLocation().getDirection().setY(.7));
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ItemStack playerBoots = player.getInventory().getBoots();
        if((player.getGameMode() != GameMode.CREATIVE)) {
            if (CustomMetaManager.hasTag(playerBoots, "Mechanical Boots", "True")) {
                if ((player.getGameMode() != GameMode.CREATIVE) &&
                        (player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) &&
                        !player.isFlying()) {
                    player.setAllowFlight(true);
                }
            } else
            {
                player.setAllowFlight(false);
            }
        }
    }

}
