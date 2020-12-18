package aidensplugin.commands;

import aidensplugin.items.base.CustomMetaManager;
import aidensplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveFlynnStickCommand implements CommandExecutor {

    private Main plugin;

    public GiveFlynnStickCommand(Main plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(strings.length < 2) {
            if (strings.length == 1) {
                //Run command on non-self player
                Player player = Bukkit.getPlayer(strings[0]);
                if (player != null) {
                    giveStick(player);
                    return true;
                } else {
                    return false;
                }
            }
            if (!(sender instanceof Player)) {
                return false;
            }
            //Execute command on self -- Heal player
            Player player = (Player) sender;
            giveStick(player);
            return true;
        } else
        {
            return false;
        }
    }

    /**
     *  Gives player the thunder stick
     * @param player Player to give item to
     */
    private void giveStick(Player player)
    {
        //Create item
        ItemStack itemStack = CustomMetaManager.createFlynnStick();

        //give item to player
        player.getInventory().addItem(itemStack);
    }
}
