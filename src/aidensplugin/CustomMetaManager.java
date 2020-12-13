package aidensplugin;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomMetaManager {

    public static void init() { }

    public static ItemStack createEnchantedGem(int amount)
    {
        ItemStack itemStack = new ItemStack(Material.EMERALD, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Enchanted Gem");
        List<String> lore = new ArrayList<>();
        lore.add("An enchanted gem that can capture a monster's energy within itself and duplicate it's dna."+
                "When broken it can create an exact duplicate of the monster clicked on.");
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_12_R1.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Enchanted Gem", new NBTTagString("True"));
        stickCompound.setInt("HideFlags", 1);

        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static ItemStack createFlynnStick()
    {
        ItemStack itemStack = new ItemStack(Material.STICK, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Flynn Revenge Stick");
        List<String> lore = new ArrayList<>();
        lore.add("This is the second legendary rod forged by Zerius the Great Blacksmith. "+
                "Imbued with electrical ability, the rod seems to be attracted to MooseGames23's"+
                " built-up electric energy.");
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_12_R1.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Flynn Stick", new NBTTagString("True"));
        stickCompound.set("Flynn Stick Timer", new NBTTagLong(0));
        stickCompound.setInt("HideFlags", 1);


        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static ItemStack createEnderBow()
    {
        ItemStack itemStack = new ItemStack(Material.BOW, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Ender Bow");
        List<String> lore = new ArrayList<>();
        lore.add("A bow made from crystallized ender pearls, this bow can teleport the user wherever their arrow lands."+
                "It takes a large amount of energy and converts gold into ender power."+
                "It was painted to look like wood by old travelling merchants in order to keep away thieves.");
        itemMeta.setLore(lore);

        itemMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
        itemMeta.setUnbreakable(true);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_12_R1.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Ender Bow", new NBTTagString("True"));
        stickCompound.setInt("HideFlags", 1);

        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static ItemStack createMechanicalBoots()
    {
        ItemStack itemStack = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Mechanical Boots");
        List<String> lore = new ArrayList<>();
        lore.add("These Mechanical Boots seem extremely unsafe providing basically no protection at all."+
                " They seem to have high velocity jet spring embeedded in the heels allowing for a quick thrust upward from any state.");
        itemMeta.setLore(lore);

        itemMeta.addEnchant(Enchantment.PROTECTION_FALL, 2, true);
        itemMeta.setUnbreakable(true);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_12_R1.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Mechanical Boots", new NBTTagString("True"));
        stickCompound.setInt("HideFlags", 1);

        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static ItemStack createThunderStick()
    {
        ItemStack itemStack = new ItemStack(Material.STICK, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("Unstable Thunder Wand");
        List<String> lore = new ArrayList<>();
        lore.add("This seemingly unstable wand is the Thunder Wand forged by Zerius the Great Blacksmith. "+
                "Imbued with electrical ability, the user is said to be able to attract lightning to their body while upping their resistance momentarily to survive the strike "+
                "at will. But unfortunately time seems to have worn it away. It seems quite unstable now...");
        itemMeta.setLore(lore);

        itemMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);

        itemStack.setItemMeta(itemMeta);

        net.minecraft.server.v1_12_R1.ItemStack nmsStick = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound stickCompound = (nmsStick.hasTag() ? nmsStick.getTag() : new NBTTagCompound());

        assert stickCompound != null;
        stickCompound.set("Thunder Stick", new NBTTagString("True"));
        stickCompound.set("Thunder Stick Timer", new NBTTagLong(0));
        stickCompound.setInt("HideFlags", 1);


        nmsStick.setTag(stickCompound);
        itemStack = CraftItemStack.asBukkitCopy(nmsStick);

        return itemStack;
    }

    public static boolean hasTag(ItemStack itemStack, String tag, String data)
    {
        //Grab nbt data of item stack
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemCompound = (nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());

        assert itemCompound != null;
        String itemData = itemCompound.getString(tag);

        //Compare the two
        return itemData.equals(data);
    }

    public static ItemStack setData(ItemStack itemStack, String tag, long data)
    {
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemCompound = (nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());

        assert itemCompound != null;
        itemCompound.setLong(tag, data);

        nmsItem.setTag(itemCompound);

        itemStack = CraftItemStack.asBukkitCopy(nmsItem);


        return itemStack;
    }

    public static long getData(ItemStack itemStack, String tag)
    {
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemCompound = (nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());

        assert itemCompound != null;
        return itemCompound.getLong(tag);
    }

    public static ItemStack setDataString(ItemStack itemStack, String tag, String data)
    {
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemCompound = (nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());

        assert itemCompound != null;
        itemCompound.setString(tag, data);

        nmsItem.setTag(itemCompound);

        itemStack = CraftItemStack.asBukkitCopy(nmsItem);


        return itemStack;
    }

    public static String getDataString(ItemStack itemStack, String tag)
    {
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound itemCompound = (nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound());

        assert itemCompound != null;
        return itemCompound.getString(tag);
    }

//    public static Entity setDataString(Entity entity, String tag, String data)
//    {
//        NBTCompound comp = NBTInjector.getNbtData(entity);
//        if(comp!=null) {
//            comp.setString(tag, data);
//        }
//
//        return entity;
//    }
//
//    public static String getDataString(Entity entity, String tag)
//    {
//        NBTEntity nbtent = new NBTEntity(entity);
//        String data = nbtent.getString(tag);
//
//        return data;
//    }
}
