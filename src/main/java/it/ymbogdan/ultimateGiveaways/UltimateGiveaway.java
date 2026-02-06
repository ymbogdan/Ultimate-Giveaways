package it.ymbogdan.ultimateGiveaways;

import it.ymbogdan.ultimateGiveaways.command.GiveawayCommand;
import it.ymbogdan.ultimateGiveaways.manager.GiveawayManager;
import it.ymbogdan.ultimateGiveaways.util.TitleUtil;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.plugin.java.JavaPlugin;

public final class UltimateGiveaway extends JavaPlugin {

    private GiveawayManager giveawayManager;

    @Override
    public void onEnable() {
        int pluginId = 29360;
        Metrics metrics = new Metrics(this, pluginId);

        metrics.addCustomChart(
                new SimplePie("chart_id", () -> "My value")
        );

        saveDefaultConfig();
        TitleUtil.initialize(this);
        giveawayManager = new GiveawayManager(this);
        getCommand("giveaway").setExecutor(new GiveawayCommand(giveawayManager));
    }

    @Override
    public void onDisable() {
        if (giveawayManager != null) {
            giveawayManager.cancelGiveaway();
        }
    }

    public GiveawayManager getGiveawayManager() {
        return giveawayManager;
    }
}

