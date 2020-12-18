package aidensplugin.commands;

import aidensplugin.items.base.InventoryTools;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.Map;

public class ExtractCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = Bukkit.getPlayer(commandSender.getName());
        if (player != null) {
            ItemStack handItem = player.getInventory().getItemInMainHand();

            if (handItem.getEnchantments().size() == 0) {
                player.sendMessage("Item in main hand must have enchantments on it");
                return true;
            }

            Map<Enchantment, Integer> enchantments = handItem.getEnchantments();
            for (Enchantment enchant : enchantments.keySet()) {
                ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, 1);
                EnchantmentStorageMeta enchantsMeta = (EnchantmentStorageMeta) book.getItemMeta();
                enchantsMeta.addStoredEnchant(enchant, enchantments.get(enchant), false);
                book.setItemMeta(enchantsMeta);

                InventoryTools.givePlayer(player, book);
            }

            player.getInventory().getItemInMainHand().setAmount(0);

            player.sendMessage("Enchantments extracted!");
        }
        return true;
    }
}

