package aidensplugin;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomMetaManager {

    public static void init() { }

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
}
