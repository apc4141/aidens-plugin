package aidensplugin.commands;

import aidensplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    private Main plugin;

    public HealCommand(Main plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(strings.length < 2) {
            if (strings.length == 1) {
                //Non-self player
                Player player = Bukkit.getPlayer(strings[0]);
                if (player != null) {
                    player.setHealth(player.getMaxHealth());
                    return true;
                } else {
                    return false;
                }
            }
            if (!(sender instanceof Player)) {
                return false;
            }

            //Execute command -- Heal player
            Player player = (Player) sender;
            player.setHealth(player.getMaxHealth());
            return true;
        } else
        {
            return false;
        }
    }
}
