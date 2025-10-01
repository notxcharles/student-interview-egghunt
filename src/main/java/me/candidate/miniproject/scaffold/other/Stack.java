package me.candidate.miniproject.scaffold.other;

import com.google.common.collect.Lists;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Stack {

    private final ItemStack stack;

    public Stack(ItemStack stack) {
        this.stack = stack;
    }

    public static Stack of(ItemStack stack) {
        return new Stack(stack);
    }

    public static Stack of(Material material) {
        return new Stack(new ItemStack(material, 1));
    }

    public static Stack of(Material material, int amount) {
        return new Stack(new ItemStack(material, amount));
    }

    public Stack meta(Consumer<ItemMeta> metaConsumer) {
        ItemMeta meta = stack.getItemMeta();
        if(meta != null) {
            metaConsumer.accept(meta);
            stack.setItemMeta(meta);
        }
        return this;
    }

    public Stack name(Component component) {
        return meta(meta -> meta.displayName(component));
    }

    public Stack name(String name) {
        return meta(meta -> meta.displayName(parse(name)));
    }

    public Stack name(String name, Object... placeholders) {
        return meta(meta -> meta.displayName(parse(name, placeholders)));
    }

    public Stack lore(String lore) {
        return meta(meta -> meta.lore(Lists.newArrayList(parse(lore))));
    }

    public Stack lore(List<String> lore) {
        return meta(meta -> meta.lore(parse(lore)));
    }

    public Stack lore(List<String> lore, Object... placeholders) {
        return meta(meta -> meta.lore(parse(lore, placeholders)));
    }

    public Stack lore(String... lore) {
        return lore(Lists.newArrayList(lore));
    }

    public Stack lore(Component lore) {
        return meta(meta -> meta.lore(Lists.newArrayList(lore)));
    }

    public Stack setLore(List<Component> lore) {
        return meta(meta -> meta.lore(lore));
    }

    public Stack enchant(Enchantment enchantment, int level) {
        stack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public Stack enchant(Map<Enchantment, Integer> enchants) {
        stack.addUnsafeEnchantments(enchants);
        return this;
    }

    public Stack itemFlags(ItemFlag ... flags) {
        return meta(meta -> meta.addItemFlags(flags));
    }

    public Stack itemFlags(Collection<ItemFlag> flags) {
        return meta(meta -> meta.addItemFlags(flags.toArray(new ItemFlag[0])));
    }

    public Stack unbreakable() {
        return meta(meta -> meta.setUnbreakable(true));
    }

    public Stack unbreakable(boolean unbreakable) {
        return meta(meta -> meta.setUnbreakable(unbreakable));
    }

    public Stack customModelData(int i) {
        return meta(meta -> meta.setCustomModelData(i));
    }

    public <T, Z> Stack persistData(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
        return meta(meta -> meta.getPersistentDataContainer().set(key, type, value));
    }

    public Stack persistData(NamespacedKey key, String value) {
        return persistData(key, PersistentDataType.STRING, value);
    }

    public Stack persistData(NamespacedKey key, Byte value) {
        return persistData(key, PersistentDataType.BYTE, value);
    }

    public Stack persistData(NamespacedKey key, byte[] value) {
        return persistData(key, PersistentDataType.BYTE_ARRAY, value);
    }

    public Stack persistData(NamespacedKey key, double value) {
        return persistData(key, PersistentDataType.DOUBLE, value);
    }

    public Stack persistData(NamespacedKey key, float value) {
        return persistData(key, PersistentDataType.FLOAT, value);
    }

    public Stack persistData(NamespacedKey key, int value) {
        return persistData(key, PersistentDataType.INTEGER, value);
    }

    public Stack persistData(NamespacedKey key, int[] value) {
        return persistData(key, PersistentDataType.INTEGER_ARRAY, value);
    }

    public Stack persistData(NamespacedKey key, long value) {
        return persistData(key, PersistentDataType.LONG, value);
    }

    public Stack persistData(NamespacedKey key, long[] value) {
        return persistData(key, PersistentDataType.LONG_ARRAY, value);
    }

    public Stack persistData(NamespacedKey key, short value) {
        return persistData(key, PersistentDataType.SHORT, value);
    }

    public Stack persistData(NamespacedKey key, PersistentDataContainer value) {
        return persistData(key, PersistentDataType.TAG_CONTAINER, value);
    }

    public Stack persistData(NamespacedKey key, PersistentDataContainer[] value) {
        return persistData(key, PersistentDataType.TAG_CONTAINER_ARRAY, value);
    }

    public ItemStack getStack() {
        return this.stack;
    }

    private Component parse(String message, Object... placeholders) {
        return MessageHandler.parse(message, placeholders);
    }

    private List<Component> parse(List<String> list, Object... placeholders) {
        return MessageHandler.parse(list, placeholders);
    }

}