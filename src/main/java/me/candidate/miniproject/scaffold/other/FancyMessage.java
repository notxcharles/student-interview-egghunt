package me.candidate.miniproject.scaffold.other;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.intellij.lang.annotations.Subst;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.io.Serializable;

/**
 * A Configuration File for FancyMessage
 */
@ConfigSerializable
public class FancyMessage implements Serializable {

    public String message = "";
    @Subst("minecraft:empty")
    public String sound;
    public Sound.Source soundsource;
    public String actionbar;
    public String titleTitle;
    public String titleSubtitle;

    public FancyMessage(String message) {
        this.message = message;
    }

    public FancyMessage() {
    }

    public void send(Audience player) {
        if (!message.equals("")) {
            MessageHandler.message(player, MessageHandler.parse(message));
        }

        if (titleTitle != null) {
            Component titleText = MessageHandler.parse(titleTitle);
            Component subtitleText = Component.empty();

            if (titleSubtitle != null) {
                subtitleText = MessageHandler.parse(titleSubtitle);
            }

            player.showTitle(Title.title(titleText, subtitleText));
        }

        if (actionbar != null) {
            player.sendActionBar(MessageHandler.parse(actionbar));
        }

        if (sound != null && soundsource != null) {
            player.playSound(Sound.sound(Key.key(sound), soundsource, 1f, 1f));
        }
    }

    public void send(Audience player, String... placeholders) {
        if (!message.equals("")) {
            MessageHandler.message(player, MessageHandler.parse(message, (Object[]) placeholders));
        }

        if (titleTitle != null) {
            Component titleText = MessageHandler.parse(titleTitle, (Object[]) placeholders);
            Component subtitleText = Component.empty();

            if (titleSubtitle != null) {
                subtitleText = MessageHandler.parse(titleSubtitle, (Object[]) placeholders);
            }

            player.showTitle(Title.title(titleText, subtitleText));
        }

        if (actionbar != null) {
            player.sendActionBar(MessageHandler.parse(actionbar, (Object[]) placeholders));
        }

        if (sound != null && soundsource != null) {
            player.playSound(Sound.sound(Key.key(sound), soundsource, 1f, 1f));
        }
    }
}