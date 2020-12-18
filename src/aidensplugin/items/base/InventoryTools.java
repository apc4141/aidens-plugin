package aidensplugin.items.base;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class InventoryTools {


    public static int remove(Inventory inventory, Material mat, int amount, short damage)
    {
        ItemStack[] contents = inventory.getContents();
        int removed = 0;
        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];

            if (item == null || !item.getType().equals(mat)) {
                continue;
            }

            if (damage != (short) -1 && item.getDurability() != damage) {
                continue;
            }

            //Check for custom name
            if(!Objects.requireNonNull(item.getItemMeta()).getDisplayName().equals(""))
                continue;

            int remove = item.getAmount() - amount - removed;

            if (removed > 0) {
                removed = 0;
            }

            if (remove <= 0) {
                removed += Math.abs(remove);
                inventory.remove(item);
                contents[i] = null;
            } else {
                item.setAmount(remove);
                return removed;
            }
        }
        return removed;
    }

    public static int removeTagged(Inventory inventory, String tag, Material mat, int amount)
    {
        ItemStack[] contents = inventory.getContents();
        int removed = 0;
        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];

            if (item == null) {
                continue;
            }

            if (!CustomMetaManager.hasTruthTag(item, tag, "True")) {
                continue;
            }

            if (item.getType().compareTo(mat) != 0) {
                continue;
            }

            int remove = item.getAmount() - amount - removed;

            if (removed > 0) {
                removed = 0;
            }

            if (remove <= 0) {
                removed += Math.abs(remove);
                inventory.remove(item);
                contents[i] = null;
            } else {
                item.setAmount(remove);
                return removed;
            }
        }
        return removed;
    }

    public static int contains(Inventory inventory, Material mat, int amount, short damage)
    {
        ItemStack[] contents = inventory.getContents();
        int searchAmount = 0;
        for (ItemStack item : contents) {
            if(item == null)
                continue;

            if (item.getType().compareTo(mat) != 0) {
                continue;
            }

            if (damage != -1 && item.getDurability() == damage) {
                continue;
            }

            //Check for custom name
            if(!Objects.requireNonNull(item.getItemMeta()).getDisplayName().equals(""))
                continue;


            searchAmount += item.getAmount();
        }
        return searchAmount - amount;
    }

    public static int containsTagged(Inventory inventory, String tag, Material mat, int amount)
    {
        ItemStack[] contents = inventory.getContents();
        int searchAmount = 0;
        for (ItemStack item : contents) {
            if(item == null)
                continue;

            if (item.getType().compareTo(mat) != 0) {
                continue;
            }

            if (!CustomMetaManager.hasTruthTag(item, tag, "True")) {
                continue;
            }

            searchAmount += item.getAmount();
        }
        return searchAmount - amount;
    }


    public static void givePlayer(Player player, ItemStack item)
    {
        if(player.getInventory().firstEmpty() != -1)
            player.getInventory().addItem(item);
        else
            player.getWorld().dropItem(player.getEyeLocation(), item);
    }
}
