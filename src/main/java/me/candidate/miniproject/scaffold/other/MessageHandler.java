package me.candidate.miniproject.scaffold.other;

import com.google.common.collect.Lists;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.util.HSVLike;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.List;

/**
 * A simple utility class for quickly
 */
public class MessageHandler {

    /**
     * Deserialize a MiniMessage message
     * Serialized Messages use MiniMessage
     * @param message MiniMessage serialized message
     * @return {@link Component}
     */
    public static Component deserialize(String message) {
        return MiniMessage.miniMessage().deserialize(message).decoration(TextDecoration.ITALIC, false);
    }

    /**
     * Send a Component to a Players' Chat
     * @param player Player
     * @param message Chat Component
     */
    public static void message(Audience player, Component message) {
        player.sendMessage(message);
    }

    /**
     * Send a Component to a Players' Chat
     * This exists to prevent breaking ABI
     * @param player Player
     * @param message Chat Component
     */
    public static void message(Player player, Component message) {
        message((Audience) player, message);
    }

    /**
     * Send a Component to a Players' Chat
     * @param sender Player
     * @param message Chat Component
     */
    public static void message(CommandSender sender, Component message) {
        sender.sendMessage(message);
    }

    /**
     * Send a Component to every player on the server
     * @param message Chat Component
     */
    public static void broadcast(Component message) {
        Bukkit.broadcast(message, "");
    }

    /**
     * Send a MiniMessage serialized message to a player
     * @param player Player
     * @param message MiniMessage serialized message
     */
    public static void message(Player player, String message) {
        player.sendMessage(deserialize(message));
    }

    /**
     * Send a MiniMessage serialized message to a player
     * @param player Player
     * @param placeholders Varargs for MiniMessage parse replacements
     * @param message MiniMessage serialized message
     */
    public static void message(Player player, String message, Object... placeholders) {
        player.sendMessage(parse(message, placeholders));
    }

    /**
     * Send a MiniMessage serialized message to a player
     * @param sender Player
     * @param message MiniMessage serialized message
     */
    public static void message(CommandSender sender, String message) {
        sender.sendMessage(deserialize(message));
    }

    /**
     * Send a MiniMessage serialized message to a player
     * @param sender CommandSender
     * @param placeholders Varargs for MiniMessage parse replacements
     * @param message MiniMessage serialized message
     */
    public static void message(CommandSender sender, String message, Object... placeholders) {
        sender.sendMessage(parse(message, placeholders));
    }

    /**
     * Send a MiniMessage serialized message to the entire server
     * @param message Serialized Message
     */
    public static void broadcast(String message) {
        Bukkit.broadcast(deserialize(message), "");
    }

    public static Title buildTitle(String title, String subtitle) {
        return buildTitle(
                parse(title),
                parse(subtitle)
        );
    }

    /**
     * Build a title.
     *
     * @param title        The title of the title
     * @param subtitle     The subtitle of the title
     * @param placeholders Placeholders to be replaced in the title/subtitle
     * @return The built title.
     */
    public static Title buildTitle(String title, String subtitle, Object... placeholders) {
        return buildTitle(
                parse(title, placeholders),
                parse(subtitle, placeholders),
                5,
                40,
                5
        );
    }

    /**
     * Build a title.
     *
     * @param title        The title of the title
     * @param subtitle     The subtitle of the title
     * @param fadeInTicks  How long the title will take to fade in in ticks
     * @param stayTicks    How long the title should stay in ticks
     * @param fadeOutTicks How long the title will take to fade out in ticks
     * @param placeholders Placeholders to be replaced in the title/subtitle
     * @return The built title.
     */
    public static Title buildTitle(String title, String subtitle, long fadeInTicks, long stayTicks, long fadeOutTicks, Object... placeholders) {
        return buildTitle(
                parse(title, placeholders),
                parse(subtitle, placeholders),
                fadeInTicks,
                stayTicks,
                fadeOutTicks
        );
    }

    /**
     * Build a title.
     *
     * @param title    The title of the title
     * @param subtitle The subtitle of the title
     * @return The built title
     */
    public static Title buildTitle(Component title, Component subtitle) {
        return buildTitle(title, subtitle, 5, 40, 5);
    }

    /**
     * Build a title.
     *
     * @param title        The title of the title
     * @param subtitle     The subtitle of the title
     * @param fadeInTicks  How long (in ticks) to
     * @param stayTicks    How long the title should stay in ticks
     * @param fadeOutTicks How long the title will take to fade out in ticks
     * @return The built title
     */
    public static Title buildTitle(Component title, Component subtitle, long fadeInTicks, long stayTicks, long fadeOutTicks) {
        return Title.title(title, subtitle, Title.Times.times(
                Duration.ofMillis(fadeInTicks * 50),
                Duration.ofMillis(stayTicks * 50),
                Duration.ofMillis(fadeOutTicks * 50)
        ));
    }

    /**
     * Parse MiniMessage markdown into a component. This should be used rather than whenever
     * there are placeholders which are of non-String types. This allows cleaner code in implementation,
     * as it removes the need for all values to be a String.
     *
     * @param text         The MiniMessage syntax
     * @param placeholders A key, value array of placeholders.
     * @return The formatted component
     */
    public static Component parse(String text, @Nullable Object... placeholders) {
        if (placeholders.length > 0) {

            if(placeholders.length % 2 != 0) {
                throw new IllegalStateException("Placeholders Must be in a key: replacement order, found missing value!");
            }

            final TagResolver.Builder TAG_BUILDER = TagResolver.builder();

            for(int i = 0; i < placeholders.length; i += 2) {

                String key = String.valueOf(placeholders[i]);
                Component value = deserialize(String.valueOf(placeholders[i + 1]));

                TAG_BUILDER.tag(key, Tag.selfClosingInserting(value));

            }

            return MiniMessage.miniMessage().deserialize(text, TAG_BUILDER.build()).decoration(TextDecoration.ITALIC, false);
        }

        return MiniMessage.miniMessage().deserialize(text).decoration(TextDecoration.ITALIC, false);
    }

    /**
     * Makes a cool gradient using HSV - Allowing for unsaturated rainbows
     * @param h1 Hue of the first color
     * @param h2 Hue of the second color
     * @param saturation Saturation of the gradient
     * @param string The string to be colored
     * @return The colored Component
     */
    public static Component gradient(float h1, float h2, float saturation, String string) {
        TextComponent.Builder c = Component.text("").toBuilder();
        char[] chars = string.toCharArray();
        float h = h1, step = h2 / chars.length;
        for (char a : chars) {
            c.append(Component.text(String.valueOf(a)).color(TextColor.color(HSVLike.hsvLike(h / 360, saturation, 1.0F))));
            h += step;
            if (h > h2) h = h1;
        }
        return c.build();
    }

    public static List<Component> parse(List<String> text, @Nullable Object... placeholders) {
        List<Component> result = Lists.newArrayList();
        for(String str : text) {
            result.add(parse(str, placeholders));
        }
        return result;
    }

    public static Component parse(String text) {
        return deserialize(text);
    }

    /**
     * Deserialize a List of Strings to Kyori Components
     * @param lore Serialized Lore
     * @return List of Components
     */
    public static List<Component> deserialize(List<String> lore) {
        List<Component> list = Lists.newArrayList();

        for(String s : lore) {
            list.add(deserialize(s));
        }

        return list;
    }


}