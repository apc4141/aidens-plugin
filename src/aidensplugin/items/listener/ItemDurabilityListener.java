package aidensplugin.items.listener;

import aidensplugin.items.base.CustomMetaManager;
import aidensplugin.items.base.MaterialHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemDurabilityListener implements Listener {

    @EventHandler
    public void onItemTakeDamage(PlayerItemDamageEvent event) {
        ItemStack original = event.getItem();
        ItemStack itemStack = original.clone();
        Player player = event.getPlayer();
        int damage = event.getDamage();

        boolean breakItem = false;

        //First time -- create nbt tags
        if (!CustomMetaManager.hasTag(itemStack, "Max Durability")) {
            short maxDur = itemStack.getType().getMaxDurability();
            long newDur = maxDur-damage;
            double num1 = ((double)newDur/(double)maxDur); //---------------------\/-------------------
            short num2 = itemStack.getType().getMaxDurability(); //---------------\/-------------------
            short setDur = (short) Math.max(0, Math.ceil(num1*num2)); //Bring it to hardcoded durability range
            short num3 = (short) (num2 - setDur);
            itemStack.setDurability(num3);
            setDurabilityLore(itemStack, maxDur, newDur);

            //Set create
            itemStack = CustomMetaManager.setData(itemStack, "Max Durability", maxDur);
            itemStack = CustomMetaManager.setData(itemStack, "Durability", newDur);
            if(setDur > 0)
                findSlotSetItem(player, itemStack, original);

            if(setDur <= 0)
                breakItem = true;
        } else { //Every time after just edit nbt tags
            long maxDur = CustomMetaManager.getData(itemStack, "Max Durability");
            long currDur = CustomMetaManager.getData(itemStack, "Durability");
            long newDur = currDur-damage;
            double num1 = ((double)newDur/(double)maxDur); //---------------------\/-------------------
            short num2 = itemStack.getType().getMaxDurability(); //---------------\/-------------------
            short setDur = (short) Math.max(0, Math.ceil(num1*num2)); //Bring it to hardcoded durability range
            short num3 = (short) (num2 - setDur);
            itemStack.setDurability(num3);
            setDurabilityLore(itemStack, (short) maxDur, newDur);

            //edit
            itemStack = CustomMetaManager.setData(itemStack, "Max Durability", maxDur);
            itemStack = CustomMetaManager.setData(itemStack, "Durability", newDur);
            if(setDur > 0)
                findSlotSetItem(player, itemStack, original);

            if(setDur <= 0)
                breakItem = true;
        }

        if(breakItem) {
            itemStack.setDurability((short) (itemStack.getType().getMaxDurability()-1));
            event.setDamage(itemStack.getType().getMaxDurability());
        }
        event.setCancelled(!breakItem);
    }

    private void findSlotSetItem(Player player, ItemStack itemStack, ItemStack original) {
        //Find slot in which item is in and replace item with new item with nbt tags
        int slot = player.getInventory().first(original);
        if(slot != -1) {
            ItemStack[] contents = player.getInventory().getContents();
            //Edit contents
            contents[slot] = itemStack;
            player.getInventory().setContents(contents);
            return;
        }

        ItemStack[] armors = player.getInventory().getArmorContents();
        for(int i = 0; i < armors.length; i++) {
            if(armors[i] == null) {
                continue;
            }
            if(armors[i].equals(original)) {
                armors[i] = itemStack;
                return;
            }
        }
        player.getInventory().setArmorContents(armors);

        if(player.getInventory().getItemInOffHand().equals(original)) {
            player.getInventory().setItemInOffHand(itemStack);
        }
    }

    private void setDurabilityLore(ItemStack itemStack, short maxDur, long newDur) {
        //Output durability on item
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = meta.getLore();
        if(lore == null || lore.size()==0) {
            lore = new ArrayList<>();
            lore.add("Durability: "+ newDur +"/"+ maxDur);
        } else {
            lore.set(lore.size()-1, "Durability: "+ newDur +"/"+ maxDur);
        }
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
    }
}
