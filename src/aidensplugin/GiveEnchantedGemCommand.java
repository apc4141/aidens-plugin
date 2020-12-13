package aidensplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveEnchantedGemCommand implements CommandExecutor {

    private Main plugin;

    public GiveEnchantedGemCommand(Main plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(strings.length < 3) {
            if (strings.length == 2) {

                int amt = Integer.parseInt(strings[1]);

                //Run command on non-self player
                Player player = Bukkit.getPlayer(strings[0]);
                if (player != null) {
                    giveStick(player, amt);
                    return true;
                } else {
                    return false;
                }
            }
            if (!(sender instanceof Player)) {
                return false;
            }
            if(strings.length == 1) {

                int amt = Integer.parseInt(strings[0]);

                //Execute command on self -- Heal player
                Player player = (Player) sender;
                giveStick(player, amt);
                return true;
            }
        }
        return false;
    }

    /**
     *  Gives player the thunder stick
     * @param player Player to give item to
     */
    private void giveStick(Player player, int amount)
    {
        //Create item
        ItemStack itemStack = CustomMetaManager.createEnchantedGem(amount);

        //give item to player
        player.getInventory().addItem(itemStack);
    }
}
