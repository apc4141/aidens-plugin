package aidensplugin;

import net.minecraft.server.v1_12_R1.ItemBow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Random;

public class EnderBowEvents implements Listener {

    public static final double ARROW_SPEED_MULTIPLIER = 0.60D;
    public static final double CHANCE_TO_CONSUME_DIAMOND = 0.15D;

    private final Plugin main;

    public EnderBowEvents(Plugin plugin)
    {
        main = plugin;
    }

    @EventHandler
    public void onShootBow(EntityShootBowEvent event)
    {
        Entity entity = event.getEntity();

        if(entity instanceof Player) {
            Player player = (Player) entity;
            Arrow arrow = (Arrow) event.getProjectile();
            ItemStack bow = player.getInventory().getItemInMainHand();

            boolean hasFuel = player.getInventory().contains(Material.DIAMOND);

//          Check if bow is ender bow
            if(CustomMetaManager.hasTag(bow, "Ender Bow", "True") && hasFuel)
            {
                arrow.setVelocity(arrow.getVelocity().multiply(ARROW_SPEED_MULTIPLIER));
                arrow.setCustomName("Ender Bow"+";"+player.getDisplayName());
                arrow.setCustomNameVisible(false);

                consumeItems(player);

                CustomMetaManager.setDataString(bow, "Ender Bow Player", player.getDisplayName());
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event)
    {
        if(event.getEntity().getShooter() instanceof Player) {
            Projectile p = event.getEntity();


            if(p instanceof Arrow) {
                Arrow arrow = (Arrow) p;
                Player player = (Player) event.getEntity().getShooter();
                ItemStack mainHand = player.getInventory().getItemInMainHand();
                if(arrow.getCustomName() != null) {

                    String[] nameParsed = arrow.getCustomName().split(";");

                    if (nameParsed[0].equals("Ender Bow") && nameParsed[1].equals(player.getDisplayName())){

                        //Detect what arrow hit and teleport player there
                        if (event.getHitBlock() == null) {
                            player.teleport(arrow);

                        } else if (event.getHitEntity() == null) {
                            player.teleport(arrow.getLocation().setDirection(
                                    player.getLocation().getDirection()));

                        }
                    }
                }
            }
        }
    }

    private void consumeItems(Player player) {
        PlayerInventory inv = player.getInventory();
        inv.remove(Material.DIAMOND);
        Random rand = new Random();
        int randInt = rand.nextInt(3)+1;
        int newFood = player.getFoodLevel()-randInt;
        newFood = Math.max(newFood, 0);
        player.setFoodLevel(newFood);
    }
}
