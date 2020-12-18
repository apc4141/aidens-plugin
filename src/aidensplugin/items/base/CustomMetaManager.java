package aidensplugin.items.base;

import aidensplugin.items.base.attribute.AttributeOperation;
import aidensplugin.items.base.attribute.AttributeSlot;
import aidensplugin.items.base.attribute.AttributeType;
import aidensplugin.items.model.CustomColorArmor;
import aidensplugin.items.model.CustomDurableItem;
import aidensplugin.items.model.CustomItem;
import aidensplugin.items.model.RecipeItem;
import net.minecraft.server.v1_16_R3.*;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagString;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CustomMetaManager {

    private static Plugin main;
    private static final HashMap<String, CustomItem> itemDats = new HashMap<>();

    public static void init(Plugin plugin) {
        main = plugin;

        addBasicItems();
        addSpiderSet();
    }

    public static HashMap<String, CustomItem> getItemDats() {
        return itemDats;
    }

    public static ItemStack createCustomItem(String dataName) {
        CustomItem cItem = itemDats.get(dataName);
        ItemStack item = new ItemStack(cItem.material, 1);
        ItemMeta itemMeta = item.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName(cItem.name);
        if(cItem.hasLore)
            itemMeta.setLore(cItem.lore);

        if(cItem.hasTwinkle) {
            itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        for(Enchantment enchant : cItem.enchants.keySet()) {
            int level = cItem.enchants.get(enchant);
            itemMeta.addEnchant(enchant, level, level > enchant.getMaxLevel());
        }

        if(cItem instanceof CustomColorArmor && itemMeta instanceof LeatherArmorMeta) {
            Color color = ((CustomColorArmor) cItem).color;
            ((LeatherArmorMeta) itemMeta).setColor(color);
        }

        item.setItemMeta(itemMeta);

        net.minecraft.server.v1_16_R3.ItemStack nmsStick = CraftItemStack.asNMSCopy(item);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set(cItem.name, NBTTagString.a("True"));

        if(cItem.hasAttributes) {
            NBTTagList oldModifiers = stickCompound.getList("AttributeModifiers", 10);
            oldModifiers.addAll(cItem.modifiers);
            stickCompound.set("AttributeModifiers", oldModifiers);
        }

        if(cItem instanceof CustomDurableItem) {
            int maxDur = ((CustomDurableItem) cItem).maxDurability;
            stickCompound.set("Max Durability", NBTTagLong.a(maxDur));
            stickCompound.set("Durability", NBTTagLong.a(maxDur));
        }

        nmsStick.setTag(stickCompound);
        item = CraftItemStack.asBukkitCopy(nmsStick);

        return item;
    }

    public static ItemStack createFlyBoots()
    {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName("Fly Boots");
        List<String> lore = new ArrayList<>();
        lore.add("Allows the user to fly and at good speeds!!!");
        itemMeta.setLore(lore);

        itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_16_R3.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Fly Boots", NBTTagString.a("True"));

        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static ItemStack createFishHelm()
    {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName("Fish Helm");
        List<String> lore = new ArrayList<>();
        lore.add("This helm is a helm imbued with the power of a thousand fish and also made out of compressed fish."+
                " Basically, it allows you the attributes of a fish, breathing-wise...");
        itemMeta.setLore(lore);

        itemMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
        itemMeta.setUnbreakable(true);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_16_R3.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Fish Helm", NBTTagString.a("True"));
        stickCompound.setInt("HideFlags", 1);

        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static ItemStack createEscapeFlower()
    {
        ItemStack itemStack = new ItemStack(Material.DANDELION, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName("Caving Flower");
        List<String> lore = new ArrayList<>();
        lore.add("A flower perfect for caving. When used it generates a perfect path downwards straight to bedrock."+
                " It then must recharge its energy for 5 minutes before use again.");
        itemMeta.setLore(lore);

        itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_16_R3.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Caving Flower", NBTTagString.a("True"));
        stickCompound.set("Caving Flower Timer", NBTTagLong.a(0));
        stickCompound.setInt("HideFlags", 1);

        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static ItemStack createInfiniteHotSnowball()
    {
        ItemStack itemStack = new ItemStack(Material.SNOWBALL, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName("Infinite Hot Snowball");
        List<String> lore = new ArrayList<>();
        lore.add("A ball molten lava somehow cold on the outside looks like a snowball but when thrown "+
                "breaks open it's snow shell and turns into a fiery ball of molten lava. It's energy seems "+
                "to be limitless! As soon as you use it, it seems to regain it's initial state.");
        itemMeta.setLore(lore);

        itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_16_R3.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Infinite Hot Snowball", NBTTagString.a("True"));
        stickCompound.set("IHS Timer", NBTTagLong.a(0));
        stickCompound.setInt("HideFlags", 1);

        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static ItemStack createMinersGem(int amount)
    {
        ItemStack itemStack = new ItemStack(Material.DIAMOND, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName("Miners Gem");
        List<String> lore = new ArrayList<>();
        lore.add("A gem used by a small group of miners. Little miners know about this gem due "+
                " to the complexity of its molecular make up. It's made by condensing many bones around a diamond."+
                " Cracking the gem against a surface "+
                "will destroy all block within it's path leaving behind only valuable ores!");
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_16_R3.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Miners Gem", NBTTagString.a("True"));
        stickCompound.setInt("HideFlags", 1);

        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static ItemStack createEnchantedGem(int amount)
    {
        ItemStack itemStack = new ItemStack(Material.EMERALD, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName("Enchanted Gem");
        List<String> lore = new ArrayList<>();
        lore.add("An enchanted gem that can capture a monster's energy within itself and duplicate it's dna."+
                "When broken it can create an exact duplicate of the monster clicked on.");
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_16_R3.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Enchanted Gem", NBTTagString.a("True"));
        stickCompound.setInt("HideFlags", 1);

        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static ItemStack createFlynnStick()
    {
        ItemStack itemStack = new ItemStack(Material.STICK, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName("Flynn Revenge Stick");
        List<String> lore = new ArrayList<>();
        lore.add("This is the second legendary rod forged by Zerius the Great Blacksmith. "+
                "Imbued with electrical ability, the rod seems to be attracted to MooseGames23's"+
                " built-up electric energy.");
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_16_R3.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Flynn Stick", NBTTagString.a("True"));
        stickCompound.set("Flynn Stick Timer", NBTTagLong.a(0));
        stickCompound.setInt("HideFlags", 1);


        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static ItemStack createEnderBow()
    {
        ItemStack itemStack = new ItemStack(Material.BOW, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName("Ender Bow");
        List<String> lore = new ArrayList<>();
        lore.add("A bow made from crystallized ender pearls, this bow can teleport the user wherever their arrow lands."+
                "It takes a large amount of energy and converts diamond and human energy into ender power."+
                "It was painted to look like wood by old travelling merchants in order to keep away thieves.");
        itemMeta.setLore(lore);

        itemMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
        itemMeta.setUnbreakable(true);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_16_R3.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Ender Bow", NBTTagString.a("True"));
        stickCompound.setInt("HideFlags", 1);

        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static ItemStack createMechanicalBoots()
    {
        ItemStack itemStack = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName("Mechanical Boots");
        List<String> lore = new ArrayList<>();
        lore.add("These Mechanical Boots seem extremely unsafe providing basically no protection at all."+
                " They seem to have high velocity jet spring embeedded in the heels allowing for a quick thrust upward from any state.");
        itemMeta.setLore(lore);

        itemMeta.addEnchant(Enchantment.PROTECTION_FALL, 2, true);
        itemMeta.setUnbreakable(true);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_16_R3.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Mechanical Boots", NBTTagString.a("True"));
        stickCompound.setInt("HideFlags", 1);

        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static ItemStack createThunderStick()
    {
        ItemStack itemStack = new ItemStack(Material.STICK, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName("Unstable Thunder Wand");
        List<String> lore = new ArrayList<>();
        lore.add("This seemingly unstable wand is the Thunder Wand forged by Zerius the Great Blacksmith. "+
                "Imbued with electrical ability, the user is said to be able to attract lightning to their body while upping their resistance momentarily to survive the strike "+
                "at will. But unfortunately time seems to have worn it away. It seems quite unstable now...");
        itemMeta.setLore(lore);

        itemMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_16_R3.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Thunder Stick", NBTTagString.a("True"));
        stickCompound.set("Thunder Stick Timer", NBTTagLong.a(0));
        stickCompound.setInt("HideFlags", 1);


        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static boolean hasTruthTag(ItemStack itemStack, String tag, String data)
    {
        //Grab nbt data of item stack
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemCompound = (nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());

        assert itemCompound != null;
        String itemData = itemCompound.getString(tag);

        //Compare the two
        return itemData.equals(data);
    }

    public static boolean hasTag(ItemStack itemStack, String tag)
    {
        //Grab nbt data of item stack
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemCompound = (nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());

        assert itemCompound != null;

        Set<String> keys = itemCompound.getKeys();

        return keys.contains(tag);
    }

    public static ItemStack setData(ItemStack itemStack, String tag, long data)
    {
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemCompound = (nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());

        assert itemCompound != null;
        itemCompound.set(tag, NBTTagLong.a(data));

        nmsItem.setTag(itemCompound);

        itemStack = CraftItemStack.asBukkitCopy(nmsItem);

        return itemStack;
    }

    public static long getData(ItemStack itemStack, String tag)
    {
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemCompound = (nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());

        assert itemCompound != null;
        return itemCompound.getLong(tag);
    }

    public static void setDataString(ItemStack itemStack, String tag, String data)
    {
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemCompound = (nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());

        assert itemCompound != null;
        itemCompound.setString(tag, data);

        nmsItem.setTag(itemCompound);

        itemStack = CraftItemStack.asBukkitCopy(nmsItem);


    }

    public static String getDataString(ItemStack itemStack, String tag)
    {
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemCompound = (nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());

        assert itemCompound != null;
        return itemCompound.getString(tag);
    }

    private static void addBasicItems() {
        ArrayList<RecipeItem> cb = new ArrayList<>();
        cb.add(new RecipeItem(Material.BONE, 64));
        itemDats.put("condensedbone", new CustomItem("Condensed Bone", "condensedbone", Material.BONE, cb)
                .setLore("Really tightly condensed bone").setTwinkle(true));
        RecipeItem.addItem("Condensed Bone", Material.BONE);

        ArrayList<RecipeItem> cf = new ArrayList<>();
        cf.add(new RecipeItem(Material.ROTTEN_FLESH, 64));
        itemDats.put("compactedflesh", new CustomItem("Compacted Flesh", "compactedflesh", Material.ROTTEN_FLESH, cf)
                .setLore("A hard as a rock, compacted chunk of flesh").setTwinkle(true));
        RecipeItem.addItem("Compacted Flesh", Material.ROTTEN_FLESH);

        ArrayList<RecipeItem> cse = new ArrayList<>();
        cse.add(new RecipeItem(Material.SPIDER_EYE, 64));
        itemDats.put("congealedeye", new CustomItem("Congealed Spider Eye", "congealedeye", Material.SPIDER_EYE, cse)
                .setLore("It's still pretty squishy...").setTwinkle(true));
        RecipeItem.addItem("Congealed Spider Eye", Material.SPIDER_EYE);

        ArrayList<RecipeItem> cg = new ArrayList<>();
        cg.add(new RecipeItem(Material.GUNPOWDER, 64));
        itemDats.put("incendiarypowder", new CustomItem("Incendiary Powder", "incendiarypowder", Material.GUNPOWDER, cg)
                .setLore("This stuff is just barely safe...").setTwinkle(true));
        RecipeItem.addItem("Incendiary Powder", Material.GUNPOWDER);
    }

    private static void addSpiderSet() {
        //===========================================Spider Helm========================================================
        short lhdur = Material.LEATHER_HELMET.getMaxDurability();
        ArrayList<RecipeItem> ssh = new ArrayList<>();
        ssh.add(new RecipeItem(Material.SPIDER_EYE, 5));
        ssh.add(new RecipeItem(Material.STRING, 6));
        itemDats.put("spiderhelm", new CustomColorArmor("Spider Helm", "spiderhelm", Material.LEATHER_HELMET, lhdur*3,
                Color.fromRGB(23, 14, 6), ssh)
                .setLore("A helmet made from congealed spider eyes")
                .addAttribute(AttributeType.MOVEMENT_SPEED, AttributeSlot.HEAD, AttributeOperation.MULTIPLY_BASE, 0.07f)
                .addAttribute(AttributeType.ARMOR, AttributeSlot.HEAD, AttributeOperation.ADDITIVE, 3)
                .addAttribute(AttributeType.ARMOR_TOUGHNESS, AttributeSlot.HEAD, AttributeOperation.ADDITIVE, 2));
        RecipeItem.addItem("Spider Helm", Material.LEATHER_HELMET);


        //===========================================Spider Breastplate=================================================
        short lcdur = Material.LEATHER_CHESTPLATE.getMaxDurability();
        ArrayList<RecipeItem> ssc = new ArrayList<>();
        ssc.add(new RecipeItem(Material.SPIDER_EYE, 8));
        ssc.add(new RecipeItem(Material.STRING, 12));
        ssc.add(new RecipeItem(Material.SPIDER_SPAWN_EGG, 1));
        itemDats.put("spiderbreastplate", new CustomColorArmor("Spider Breastplate", "spiderbreastplate", Material.LEATHER_CHESTPLATE, lcdur*3,
                Color.fromRGB(23, 14, 6), ssc)
                .setLore("A breastplate made from congealed spider eyes")
                .addAttribute(AttributeType.MOVEMENT_SPEED, AttributeSlot.CHEST, AttributeOperation.MULTIPLY_BASE, 0.15f)
                .addAttribute(AttributeType.ARMOR, AttributeSlot.CHEST, AttributeOperation.ADDITIVE, 8)
                .addAttribute(AttributeType.ARMOR_TOUGHNESS, AttributeSlot.CHEST, AttributeOperation.ADDITIVE, 2));
        RecipeItem.addItem("Spider Breastplate", Material.LEATHER_CHESTPLATE);


        //==========================================Spider Leggings=====================================================
        short lldur = Material.LEATHER_LEGGINGS.getMaxDurability();
        ArrayList<RecipeItem> ssl = new ArrayList<>();
        ssl.add(new RecipeItem(Material.SPIDER_EYE, 7));
        ssl.add(new RecipeItem(Material.STRING, 10));
        itemDats.put("spiderleggings", new CustomColorArmor("Spider Leggings", "spiderleggings", Material.LEATHER_LEGGINGS, lldur*3,
                Color.fromRGB(23, 14, 6), ssl)
                .setLore("Leggings made from congealed spider eyes")
                .addAttribute(AttributeType.MOVEMENT_SPEED, AttributeSlot.LEGS, AttributeOperation.MULTIPLY_BASE, 0.13f)
                .addAttribute(AttributeType.ARMOR, AttributeSlot.LEGS, AttributeOperation.ADDITIVE, 6)
                .addAttribute(AttributeType.ARMOR_TOUGHNESS, AttributeSlot.HEAD, AttributeOperation.ADDITIVE, 2));
        RecipeItem.addItem("Spider Leggings", Material.LEATHER_LEGGINGS);


        //===========================================Spider Boots=======================================================
        short lbdur = Material.LEATHER_BOOTS.getMaxDurability();
        ArrayList<RecipeItem> ssb = new ArrayList<>();
        ssb.add(new RecipeItem(Material.SPIDER_EYE, 4));
        ssb.add(new RecipeItem(Material.STRING, 3));
        itemDats.put("spiderboots", new CustomColorArmor("Spider Boots", "spiderboots", Material.LEATHER_BOOTS, lbdur*3,
                Color.fromRGB(23, 14, 6), ssb)
                .setLore("Boots made from congealed spider eyes")
                .addAttribute(AttributeType.MOVEMENT_SPEED, AttributeSlot.FEET, AttributeOperation.MULTIPLY_BASE, 0.05f)
                .addAttribute(AttributeType.ARMOR, AttributeSlot.FEET, AttributeOperation.ADDITIVE, 3)
                .addAttribute(AttributeType.ARMOR_TOUGHNESS, AttributeSlot.HEAD, AttributeOperation.ADDITIVE, 2));
        RecipeItem.addItem("Spider Boots", Material.LEATHER_BOOTS);
    }
}
