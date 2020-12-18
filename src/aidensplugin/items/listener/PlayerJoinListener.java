package aidensplugin.items.listener;

import aidensplugin.items.base.PlayerEveryTickTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerJoinListener implements Listener {

    private final Plugin main;

    public PlayerJoinListener(Plugin plugin) {
        main = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player playerJoined = event.getPlayer();
        PlayerEveryTickTask task = new PlayerEveryTickTask(playerJoined);
        task.runTaskTimer(main, 0, 1);
    }
}
