package aidensplugin.items.listener;

import aidensplugin.items.base.CustomMetaManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event)
    {
        Player player = event.getPlayer();
        ItemStack playerBoots = player.getInventory().getBoots();
        if(CustomMetaManager.hasTruthTag(playerBoots, "Mechanical Boots", "True")) {
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
        if((player.getGameMode() != GameMode.CREATIVE) && (player.getGameMode() != GameMode.SPECTATOR)) {
            if (CustomMetaManager.hasTruthTag(playerBoots, "Mechanical Boots", "True")) {
                if ((player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) &&
                        !player.isFlying()) {
                    player.setAllowFlight(true);
                }
            } else if (CustomMetaManager.hasTruthTag(playerBoots, "Fly Boots", "True")) {
                if ((player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) &&
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
