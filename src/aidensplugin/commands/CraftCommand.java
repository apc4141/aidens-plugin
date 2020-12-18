package aidensplugin.commands;

import aidensplugin.items.base.CustomMetaManager;
import aidensplugin.items.base.InventoryTools;
import aidensplugin.items.base.MaterialHelper;
import aidensplugin.items.model.CustomItem;
import aidensplugin.items.model.RecipeItem;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CraftCommand implements CommandExecutor {

    public static HashMap<String, String> items = new HashMap<>();
    public static HashMap<String, ItemStack> craftItems = new HashMap<>();
    public static HashMap<String, ArrayList<RecipeItem>> recipes = new HashMap<>();

    public static void init()
    {
        //Enchanted emerald crafting recipe
        String egName = "enchantedgem";
        items.put(egName, "Enchanted Gem");
        craftItems.put(egName, CustomMetaManager.createEnchantedGem(1));

        ArrayList<RecipeItem> eg = new ArrayList<>();
        eg.add(new RecipeItem(Material.EMERALD, 5));
        eg.add(new RecipeItem(Material.GOLD_INGOT, 20));
        eg.add(new RecipeItem(Material.EGG, 5));
        recipes.put(egName, eg);

        //Miners gem crafting recipe
        String mgName = "minersgem";
        items.put(mgName, "Miners Gem");
        craftItems.put(mgName, CustomMetaManager.createMinersGem(1));

        ArrayList<RecipeItem> mg = new ArrayList<>();
        mg.add(new RecipeItem(Material.IRON_INGOT, 15));
        mg.add(new RecipeItem(Material.BONE, 16));
        mg.add(new RecipeItem(Material.ROTTEN_FLESH, 16));
        recipes.put(mgName, mg);

        //Miners gem crafting recipe
        String fbName = "flyboots";
        items.put(fbName, "Fly Boots");
        craftItems.put(fbName, CustomMetaManager.createFlyBoots());

        ArrayList<RecipeItem> fb = new ArrayList<>();
        fb.add(new RecipeItem(Material.GOLDEN_APPLE, 32));
        fb.add(new RecipeItem(Material.ENDER_PEARL, 64));
        fb.add(new RecipeItem(Material.DIAMOND, 32));
        fb.add(new RecipeItem(Material.QUARTZ, 64));
        fb.add(new RecipeItem(Material.GUNPOWDER, 16).requireTag("Incendiary Powder"));
        fb.add(new RecipeItem(Material.BONE, 16).requireTag("Condensed Bone"));
        recipes.put(fbName, fb);

        HashMap<String, CustomItem> custItems = CustomMetaManager.getItemDats();
        for(CustomItem customItem : custItems.values()) {
            String name = customItem.dataName;
            items.put(name, customItem.name);
            craftItems.put(name, CustomMetaManager.createCustomItem(name));
            recipes.put(name, customItem.recipe);
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = Bukkit.getPlayer(commandSender.getName());
        if(player != null) {
            if (!(strings.length == 2 || strings.length == 1)) {
                return false;
            }

            String modfString = strings[0].replace("_", "").toLowerCase();

            boolean containsKey = false;
            for(String key : items.keySet()) {
                if(key.equalsIgnoreCase(modfString)) {
                    containsKey = true;
                    break;
                }
            }

            if (!containsKey) {
                return false;
            }

            ArrayList<RecipeItem> recipe = recipes.get(modfString);
            ItemStack item = craftItems.get(modfString);

            int amount;
            try {
                if(strings.length == 2)
                    amount = Integer.parseInt(strings[1]);
                else
                    amount = 1;
            } catch (NumberFormatException e) {
                return false;
            }

            if(player.getGameMode() != GameMode.CREATIVE) {
                boolean missing = false;
                for (RecipeItem rItem : recipe) {
                    int amountMissing;

                    //Check if requires tag to be on item
                    if(rItem.requireTag) {
                        amountMissing  = -InventoryTools.containsTagged(player.getInventory(), rItem.tag,
                                rItem.material, rItem.amount * amount);
                    } else {
                       amountMissing  = -InventoryTools.contains(player.getInventory(),
                                rItem.material, rItem.amount * amount, (short) -1);
                    }

                    //Amount was not there
                    if (amountMissing > 0) {
                        if(rItem.requireTag) {
                            player.sendMessage("Missing: " + rItem.tag + " x" + amountMissing);
                            missing = true;
                        } else {
                            player.sendMessage("Missing: " + MaterialHelper.getName(rItem.material) + " x" + amountMissing);
                            missing = true;
                        }

                    }
                }

                if (missing)
                    return true;
            }

            for(RecipeItem rItem : recipe) {

                //Check if requires a tag to be on item
                if(rItem.requireTag) {
                    int amountMissing = InventoryTools.removeTagged(player.getInventory(), rItem.tag,
                            rItem.material, rItem.amount * amount);
                } else {
                    int amountMissing = InventoryTools.remove(player.getInventory(),
                            rItem.material, rItem.amount * amount, (short) -1);
                }
            }

            //Add crafted item to inventory
            ItemStack craftItem = craftItems.get(modfString);
            int amtPer = craftItem.getAmount();
            for(int i = 0; i < amount; i++)
                InventoryTools.givePlayer(player, craftItem);

            player.sendMessage("Crafted "+items.get(modfString)+" x"+(amtPer*amount));
        }
        return true;
    }

    public static List<String> getCraftItems()
    {
        return new ArrayList<>(items.keySet());
    }
}
