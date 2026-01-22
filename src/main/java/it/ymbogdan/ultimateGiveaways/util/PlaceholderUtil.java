package it.ymbogdan.ultimateGiveaways.util;

public class PlaceholderUtil {

    public static String replacePlaceholders(String text, String item, String winner, String player, String starter) {
        if (text == null) {
            return "";
        }

        String result = text;

        if (item != null) {
            result = result.replace("{item}", item);
        }

        if (winner != null) {
            result = result.replace("{winner}", winner);
        }

        if (player != null) {
            result = result.replace("{player}", player);
        }

        if (starter != null) {
            result = result.replace("{starter}", starter);
        }

        return result;
    }
}

