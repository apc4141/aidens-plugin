package aidensplugin.items.listener;

import aidensplugin.items.base.CustomMetaManager;
import aidensplugin.items.base.InventoryTools;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.SpawnEgg;

public class EnchantedGemListener implements Listener {

    @EventHandler
    public void onPlayerClick(PlayerInteractEntityEvent event) {

        if(event.getHand() == EquipmentSlot.HAND) {
            Player player = event.getPlayer();
            ItemStack mainHand = player.getInventory().getItemInMainHand();

            if (CustomMetaManager.hasTruthTag(mainHand, "Enchanted Gem", "True")) {
                Entity entityClicked = event.getRightClicked();
                ItemStack egg = toItemStack(1, entityClicked.getType());
                InventoryTools.givePlayer(player, egg);

                //Remove enchanted gem
                if(player.getGameMode() != GameMode.CREATIVE)
                    InventoryTools.removeTagged(player.getInventory(), "Enchanted Gem", Material.EMERALD, 1);
            }
        }
    }

    public ItemStack toItemStack(int amount, EntityType type) {
        SpawnEgg spawnEgg = new SpawnEgg(type);
        return spawnEgg.toItemStack(amount);
    }
}
