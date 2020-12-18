package aidensplugin.items.listener;

import aidensplugin.items.base.CustomMetaManager;
import aidensplugin.items.base.CustomWorldManager;
import aidensplugin.items.base.InventoryTools;
import aidensplugin.items.base.MinersGemBlockWhiteList;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class PlayerRightClickedListener implements Listener
{

    //Thunder stick attributes
    public static final int CHANCE_TO_DAMAGE = 22;
    private static final long THUNDER_STICK_DELAY = 5000;

    //Flynn stick delay
    private static final long FLYNN_STICK_DELAY = 7000;

    //Miners Gem stuff
    private static final int MINERS_GEM_DIST = 35;

    //Infinite hot snowball delay
    private static final long IHS_DELAY = 7500;

    //Escape flower snowball delay
    private static final long ESCAPE_FLOWER_DELAY = 300000;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR ||
                event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ItemStack handItem = player.getItemInHand();

            //TEST FOR THUNDER STICK CASE
            if (CustomMetaManager.hasTruthTag(handItem, "Thunder Stick", "True")) {
                long ptime = CustomMetaManager.getData(handItem, "Thunder Stick Timer");
                long systime = System.currentTimeMillis();
                if (systime - ptime > THUNDER_STICK_DELAY) {
                    //Debugging
                    //Bukkit.broadcastMessage("Systime: "+systime+"; PTime: "+ptime);

                    player.setItemInHand(CustomMetaManager.setData(handItem, "Thunder Stick Timer", systime));
                    World world = player.getWorld();
                    Location loc = player.getLocation();


                    //Do thunder stick event
                    double x = loc.getX();
                    double y = loc.getY();
                    double z = loc.getZ();

                    Random rand = new Random();
                    //Give resistance
                    if (rand.nextInt(100) > CHANCE_TO_DAMAGE) {
                        PotionEffectType effect = PotionEffectType.DAMAGE_RESISTANCE;
                        player.addPotionEffect(effect.createEffect(50, 4));
                    }

                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            CustomWorldManager.strikeLightning(world, x + i, y, z + j);
                        }
                    }


                    PotionEffectType effect2 = PotionEffectType.FIRE_RESISTANCE;
                    player.addPotionEffect(effect2.createEffect(250, 4));

                    player.damage(1);

                    player.setFireTicks(0);
                }
            }

            //TEST FOR FLYNN STICK CASE
            if (CustomMetaManager.hasTruthTag(handItem, "Flynn Stick", "True")) {
                long ptime = CustomMetaManager.getData(handItem, "Flynn Stick Timer");
                long systime = System.currentTimeMillis();
                if (systime - ptime > FLYNN_STICK_DELAY) {
                    player.setItemInHand(CustomMetaManager.setData(handItem, "Flynn Stick Timer", systime));

                    Player flynn = Bukkit.getPlayer("MooseGames23");
                    if (flynn != null) {
                        CustomWorldManager.strikeLightning(flynn);
                    }
                }
            }

            //TEST FOR MINERS GEM CASE
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK &&
                CustomMetaManager.hasTruthTag(handItem, "Miners Gem", "True")) {

                Block block = event.getClickedBlock();
                if (block != null) {
                    World world = block.getWorld();
                    BlockFace blockFace = event.getBlockFace();

                    List<Material> whitelist = MinersGemBlockWhiteList.getWhitelist();

                    for (int i = 0; i <= 1; i++) {
                        for (int j = 0; j < MINERS_GEM_DIST; j++) {
                            switch (blockFace) {
                                case NORTH:
                                    Block nBlock = world.getBlockAt(block.getLocation().add(0, -i, j));
                                    if (!whitelist.contains(nBlock.getType()))
                                        nBlock.breakNaturally();
                                    break;
                                case SOUTH:
                                    Block sBlock = world.getBlockAt(block.getLocation().add(0, -i, -j));
                                    if (!whitelist.contains(sBlock.getType()))
                                        sBlock.breakNaturally();
                                    break;
                                case EAST:
                                    Block eBlock = world.getBlockAt(block.getLocation().add(-j, -i, 0));
                                    if (!whitelist.contains(eBlock.getType()))
                                        eBlock.breakNaturally();
                                    break;
                                case WEST:
                                    Block wBlock = world.getBlockAt(block.getLocation().add(j, -i, 0));
                                    if (!whitelist.contains(wBlock.getType()))
                                        wBlock.breakNaturally();
                                    break;
                            }

                        }
                    }

                    if (player.getGameMode() != GameMode.CREATIVE)
                        InventoryTools.removeTagged(player.getInventory(), "Miners Gem", Material.DIAMOND, 1);
                }
            }

            //TEST FOR INFINITE HOT SNOWBALL CASE
            if (CustomMetaManager.hasTruthTag(handItem, "Infinite Hot Snowball", "True")) {
                long ptime = CustomMetaManager.getData(handItem, "IHS Timer");
                long systime = System.currentTimeMillis();
                if (systime - ptime > IHS_DELAY) {
                    player.setItemInHand(CustomMetaManager.setData(handItem, "IHS Timer", systime));

                    Location loc = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(1.5))
                            .toLocation(player.getWorld(), player.getLocation().getYaw(), player.getLocation().getPitch());
                    Fireball fireball = player.getWorld().spawn(loc, Fireball.class);
                }

                //----Always do this---------------------!!!
                event.setCancelled(true);
            }

            //TEST FOR ESCAPE FLOWER CASE
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK &&
                CustomMetaManager.hasTruthTag(handItem, "Caving Flower", "True")) {

                long ptime = CustomMetaManager.getData(handItem, "Caving Flower Timer");
                long systime = System.currentTimeMillis();
                if (systime - ptime > ESCAPE_FLOWER_DELAY) {
                    player.setItemInHand(CustomMetaManager.setData(handItem, "Caving Flower Timer", systime));

                    Block block = event.getClickedBlock();
                    World world = block.getWorld();

                    int yOff = 0;

                    while(true) {
                        Block ladderBlock = world.getBlockAt(block.getLocation().subtract(0, yOff, 0));
                        Block woodBlock = world.getBlockAt(block.getLocation().subtract(0, yOff, -1));

                        if(ladderBlock.getType() == Material.BEDROCK || woodBlock.getType() == Material.BEDROCK ||
                        yOff > 256)
                            break;

                        ladderBlock.setType(Material.LADDER);
                        woodBlock.setType(Material.OAK_PLANKS);

                        yOff++;
                    }
                }

                //----Always do this---------------------!!!
                event.setCancelled(true);
            }
        }
    }
}
