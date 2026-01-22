package it.ymbogdan.ultimateGiveaways.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MiniMessageUtil {

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static Component parse(String text) {
        if (text == null || text.isEmpty()) {
            return Component.empty();
        }
        return miniMessage.deserialize(text);
    }
}

