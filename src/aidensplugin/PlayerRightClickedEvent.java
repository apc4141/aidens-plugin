package aidensplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Random;

public class PlayerRightClickedEvent implements Listener
{

    //Thunder stick attributes
    public static final int CHANCE_TO_DAMAGE = 22;
    private static final long THUNDER_STICK_DELAY = 5000;

    //Flynn stick delay
    private static final long FLYNN_STICK_DELAY = 7000;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack handItem = player.getItemInHand();


        //TEST FOR THUNDER STICK CASE
        if (CustomMetaManager.hasTag(handItem, "Thunder Stick", "True")) {
            long ptime = CustomMetaManager.getData(handItem, "Thunder Stick Timer");
            long systime = System.currentTimeMillis();
            if(systime - ptime > THUNDER_STICK_DELAY) {
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
                if(rand.nextInt(100) > CHANCE_TO_DAMAGE) {
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
        if (CustomMetaManager.hasTag(handItem, "Flynn Stick", "True")) {
            long ptime = CustomMetaManager.getData(handItem, "Flynn Stick Timer");
            long systime = System.currentTimeMillis();
            if(systime - ptime > FLYNN_STICK_DELAY) {
                player.setItemInHand(CustomMetaManager.setData(handItem, "Flynn Stick Timer", systime));

                Player flynn = Bukkit.getPlayer("MooseGames23");
                if(flynn!=null){
                    CustomWorldManager.strikeLightning(flynn);
                }
            }
        }

    }
}
