package it.ymbogdan.ultimateGiveaways.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class TitleUtil {

    private static final Map<String, String> titleCache = new HashMap<>();
    private static JavaPlugin plugin;
    private static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.legacyAmpersand();

    public static void initialize(JavaPlugin pluginInstance) {
        plugin = pluginInstance;
        reloadCache();
    }

    public static void reloadCache() {
        titleCache.clear();
        if (plugin == null) return;

        ConfigurationSection titlesSection = plugin.getConfig().getConfigurationSection("titles");
        if (titlesSection == null) return;

        for (String key : titlesSection.getKeys(false)) {
            ConfigurationSection section = titlesSection.getConfigurationSection(key);
            if (section != null) {
                String title = section.getString("title", "");
                String subtitle = section.getString("subtitle", "");
                titleCache.put(key + ".title", title);
                titleCache.put(key + ".subtitle", subtitle);
            }
        }
    }

    public static Component getTitle(String section, String type, String item, String winner, String player, String starter) {
        String key = section + "." + type;
        String template = titleCache.getOrDefault(key, "");

        if (template.isEmpty()) {
            return Component.empty();
        }

        String processed = PlaceholderUtil.replacePlaceholders(template, item, winner, player, starter);
        return LEGACY_SERIALIZER.deserialize(processed);
    }
}

