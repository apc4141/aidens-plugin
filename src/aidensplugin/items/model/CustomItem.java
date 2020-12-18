package aidensplugin.items.model;

import aidensplugin.items.base.attribute.AttributeOperation;
import aidensplugin.items.base.attribute.AttributeSlot;
import aidensplugin.items.base.attribute.AttributeType;
import com.sun.istack.internal.NotNull;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CustomItem {

    public String name;
    public String dataName;
    public Material material;
    public ArrayList<RecipeItem> recipe;

    public boolean hasLore;
    public ArrayList<String> lore;

    public boolean hasTwinkle;

    public boolean hasAttributes;
    public NBTTagList modifiers;

    public HashMap<Enchantment, Integer> enchants;

    public CustomItem(String name, String dataName, Material material, List<RecipeItem> recipe) {
        this.name = name;
        this.dataName = dataName;
        this.material = material;
        this.recipe = (ArrayList<RecipeItem>) recipe;
        hasLore = false;
        lore = null;
        hasAttributes = false;
        modifiers = new NBTTagList();
        enchants = new HashMap<>();
    }

    @NotNull
    public CustomItem setLore(ArrayList<String> lore) {
        if(lore == null) {
            hasLore = false;
            this.lore = null;
        } else {
            hasLore = true;
            this.lore = lore;
        }
        return this;
    }

    @NotNull
    public CustomItem setLore(String lore) {
        if(lore == null) {
            hasLore = false;
            this.lore = null;
        } else {
            hasLore = true;
            ArrayList<String> newLore = new ArrayList<>();
            newLore.add(lore);
            this.lore = newLore;
        }
        return this;
    }

    @NotNull
    public CustomItem setTwinkle(boolean twinkle) {
        hasTwinkle = twinkle;
        return this;
    }

    @NotNull
    public CustomItem addAttribute(AttributeType type, AttributeSlot slot, AttributeOperation operation, float amount) {
        NBTTagCompound attribute = new NBTTagCompound();
        attribute.set("AttributeName", NBTTagString.a(type.getName()));
        if(slot != AttributeSlot.ANY)
            attribute.set("Slot", NBTTagString.a(slot.getName()));
        attribute.set("Name", NBTTagString.a(type.getName()));
        attribute.set("Amount", NBTTagFloat.a(amount));
        attribute.set("Operation", NBTTagInt.a(operation.getNum()));

        //!=========Note: Generate completely unique uuids for every item====================!
        Random rand = new Random();
        attribute.set("UUIDLeast", NBTTagInt.a(rand.nextInt()));
        attribute.set("UUIDMost", NBTTagInt.a(rand.nextInt()));

        modifiers.add(attribute);

        hasAttributes = !modifiers.isEmpty();

        return this;
    }

    @NotNull
    public CustomItem addEnchant(Enchantment enchant, int level) {
        enchants.put(enchant, level);
        return this;
    }
}
