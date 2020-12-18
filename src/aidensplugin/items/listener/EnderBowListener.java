package aidensplugin.items.listener;

import aidensplugin.items.base.CustomMetaManager;
import aidensplugin.items.base.InventoryTools;
import com.mysql.fabric.xmlrpc.base.Array;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class EnderBowListener implements Listener {

    public static final double ARROW_SPEED_MULTIPLIER = 0.35D;
    //public static final double CHANCE_TO_CONSUME_DIAMOND = 0.35D;

    private final Plugin main;

    public EnderBowListener(Plugin plugin)
    {
        main = plugin;
    }

    @EventHandler
    public void onShootBow(EntityShootBowEvent event)
    {
        Entity entity = event.getEntity();

        if(entity instanceof Player) {
            Player player = (Player) entity;
            Entity p = event.getProjectile();
            if(p instanceof Array) {
                Arrow arrow = (Arrow) p;
                ItemStack bow = player.getInventory().getItemInMainHand();

                boolean hasFuel = player.getInventory().contains(Material.IRON_INGOT);

                //          Check if bow is ender bow
                if (CustomMetaManager.hasTruthTag(bow, "Ender Bow", "True") && hasFuel) {
                    arrow.setVelocity(arrow.getVelocity().multiply(ARROW_SPEED_MULTIPLIER));
                    arrow.setGravity(false);

                    arrow.setCustomName("Ender Bow" + ";" + player.getDisplayName());
                    arrow.setCustomNameVisible(false);

                    consumeItems(player);

                    CustomMetaManager.setDataString(bow, "Ender Bow Player", player.getDisplayName());
                }
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
                if(player != null) {
                    ItemStack mainHand = player.getInventory().getItemInMainHand();
                    if (arrow.getCustomName() != null) {

                        String[] nameParsed = arrow.getCustomName().split(";");

                        if (nameParsed[0].equals("Ender Bow") && nameParsed[1].equals(player.getDisplayName())) {

                            //Detect what arrow hit and teleport player there
                            if (event.getHitEntity() == null) {
                                Block block = event.getHitBlock();
                                assert block != null;
                                Block blockUp = block.getRelative(0, 1, 0);

                                //Test if block above hit block is air
                                if (block.getRelative(0, 1, 0).getType() == Material.AIR) {
                                    //                                player.sendMessage("Block above is air");
                                    //                                player.sendMessage("Teleporting to "+blockUp.getLocation()
                                    //                                        .add(0.5d, 0, 0.5d).setDirection(
                                    //                                        player.getLocation().getDirection()));
                                    player.teleport(blockUp.getLocation()
                                            .add(0.5d, 0, 0.5d).setDirection(
                                                    player.getLocation().getDirection()));

                                } else {
                                    //                                player.sendMessage("Block above is not air");
                                    //                                player.sendMessage("Teleporting to "+arrow.getLocation().setDirection(
                                    //                                        player.getLocation().getDirection()));
                                    player.teleport(arrow.getLocation().setDirection(
                                            player.getLocation().getDirection()));

                                }

                            } else if (event.getHitBlock() == null) {
                                player.teleport(arrow.getLocation().setDirection(
                                        player.getLocation().getDirection()));
                            }
                        }
                    }
                }
            }
        }
    }

    private void consumeItems(Player player) {
        if(player.getGameMode() == GameMode.CREATIVE)
            return;

        PlayerInventory inv = player.getInventory();
        Random rand = new Random();

        float consChance = rand.nextFloat();
        //if(consChance < CHANCE_TO_CONSUME_DIAMOND) {
            //player.sendMessage("Ender Bow has consumed a diamond");
        InventoryTools.remove(inv, Material.IRON_INGOT, 1, (short) -1);
        //}

        int newFood = player.getFoodLevel()-1;
        newFood = Math.max(newFood, 0);
        player.setFoodLevel(newFood);
    }
}
