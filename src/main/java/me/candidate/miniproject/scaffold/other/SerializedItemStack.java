package me.candidate.miniproject.scaffold.other;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.io.Serializable;
import java.util.List;

@ConfigSerializable
public class SerializedItemStack implements Serializable {

    private Material material;
    private int amount;
    private String name;
    private List<String> lore;
    private List<ItemFlag> itemFlags;
    private Boolean unbreakable;

    public SerializedItemStack(@NotNull Material material, int amount, String name, List<String> lore, List<ItemFlag> itemFlags, Boolean unbreakable) {
        this.material = material;
        this.amount = amount;
        this.name = name;
        this.lore = lore;
        this.itemFlags = itemFlags;
        this.unbreakable = unbreakable;
    }

    public SerializedItemStack() {

    }

    public static SerializedItemStack of(Material material) {
        return of(material, 1, null, null, null, null);
    }

    public static SerializedItemStack of(Material material, int amount) {
        return of(material, amount, null, null, null, null);
    }

    public static SerializedItemStack of(Material material, int amount, String name) {
        return of(material, amount, name, null, null, null);
    }

    public static SerializedItemStack of(Material material, int amount, String name, List<String> lore) {
        return of(material, amount, name, lore, null, null);
    }

    public static SerializedItemStack of(Material material, int amount, String name, List<String> lore, List<ItemFlag> itemFlags) {
        return of(material, amount, name, lore, itemFlags, null);
    }

    public static SerializedItemStack of(Material material, int amount, String name, List<String> lore, List<ItemFlag> itemFlags, Boolean unbreakable) {
        return new SerializedItemStack(material, amount, name, lore, itemFlags, unbreakable);
    }

    public ItemStack getItemStack(Object... placeholders) {

        Stack stack = Stack.of(this.material, this.amount);

        if(this.name != null) {
            stack.name(MessageHandler.parse(this.name, placeholders));
        }

        if(this.lore != null) {
            stack.setLore(MessageHandler.parse(this.lore, placeholders));
        }

        if(this.itemFlags != null) {
            stack.itemFlags(this.itemFlags);
        }

        if(this.unbreakable != null) {
            stack.unbreakable(this.unbreakable);
        }

        return stack.getStack();
    }

}