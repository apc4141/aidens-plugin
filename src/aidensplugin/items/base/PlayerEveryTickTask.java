package aidensplugin.items.base;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerEveryTickTask extends BukkitRunnable {

    private final String playerName;

    public PlayerEveryTickTask(Player player) {
        this.playerName = player.getDisplayName();
    }

    @Override
    public void run() {
        Player player = Bukkit.getPlayer(playerName);
        if(player == null) {
            cancel();
            return;
        }

        ItemStack playerHelm = player.getInventory().getHelmet();

        if((player.getGameMode() != GameMode.CREATIVE) && (player.getGameMode() != GameMode.SPECTATOR) && playerHelm != null) {
            if (CustomMetaManager.hasTruthTag(playerHelm, "Fish Helm", "True")) {
                if(CustomWorldManager.playerUnderWater(player))
                {
                    player.setRemainingAir(300);
                } else
                {
                    if(!player.hasPotionEffect(PotionEffectType.SLOW)) {
                        PotionEffect wither = PotionEffectType.SLOW.createEffect(500, 2);
                        player.addPotionEffect(wither);
                    }
                }
            }
        }
    }
}
