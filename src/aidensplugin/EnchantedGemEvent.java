package aidensplugin;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.SpawnEgg;

public class EnchantedGemEvent implements Listener {

    @EventHandler
    public void PlayerRightClick(PlayerInteractEntityEvent event, PlayerInteractEvent airevent) {

        Player player = event.getPlayer();
        ItemStack mainHand = player.getInventory().getItemInMainHand();

        Bukkit.broadcastMessage("Test 1");

        if(CustomMetaManager.hasTag(mainHand, "Enchanted Gem", "True")) {
            Bukkit.broadcastMessage("Test 2");

            Entity entityClicked = event.getRightClicked();
            ItemStack egg = toItemStack(1, entityClicked.getType());
            player.getInventory().addItem(egg);
        }
    }

    public ItemStack toItemStack(int amount, EntityType type) {
        ItemStack item = new ItemStack(Material.MONSTER_EGG, amount);
        net.minecraft.server.v1_12_R1.ItemStack stack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tagCompound = stack.getTag();
        if(tagCompound == null){
            tagCompound = new NBTTagCompound();
        }
        NBTTagCompound id = new NBTTagCompound();
        id.setString("id", type.getName());
        tagCompound.set("EntityTag", id);
        stack.setTag(tagCompound);
        return CraftItemStack.asBukkitCopy(stack);
    }
}
