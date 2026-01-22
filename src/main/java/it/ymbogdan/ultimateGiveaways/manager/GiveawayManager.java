package it.ymbogdan.ultimateGiveaways.manager;

import it.ymbogdan.ultimateGiveaways.util.TitleUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.util.Ticks;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GiveawayManager {

    private final JavaPlugin plugin;
    private final Random random;
    private boolean giveawayRunning;
    private BukkitTask rouletteTask;

    public GiveawayManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.random = new Random();
        this.giveawayRunning = false;
    }

    public void startGiveaway(Player starter) {
        if (giveawayRunning) {
            starter.sendMessage("§cA giveaway is already underway!");
            return;
        }

        ItemStack item = starter.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            starter.sendMessage("§cYou must hold an item in your hand to start a giveaway!");
            return;
        }

        final ItemStack prize = item.clone();
        giveawayRunning = true;

        starter.getInventory().setItemInMainHand(null);

        String itemName;
        if (prize.hasItemMeta() && prize.getItemMeta().hasDisplayName()) {
            net.kyori.adventure.text.Component displayName = prize.getItemMeta().displayName();
            if (displayName != null) {
                String serialized = net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().serialize(displayName);
                itemName = serialized.replaceAll("§[0-9a-fk-or]", "").replaceAll("&[0-9a-fk-or]", "");
                if (itemName == null || itemName.isEmpty()) {
                    itemName = prize.getType().name();
                }
            } else {
                itemName = prize.getType().name();
            }
        } else {
            itemName = prize.getType().name();
        }
        
        final String finalItemName = itemName;

        Component startTitle = TitleUtil.getTitle("start", "title", finalItemName, null, null, starter.getName());
        Component startSubtitle = TitleUtil.getTitle("start", "subtitle", finalItemName, null, null, starter.getName());

        Title startTitleObj = Title.title(
                startTitle,
                startSubtitle,
                Title.Times.times(Ticks.duration(10), Ticks.duration(60), Ticks.duration(10))
        );

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.showTitle(startTitleObj);
            onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        }

        final List<Player> participants = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (!onlinePlayer.hasPermission("ultimate.giveaway.excluded")) {
                participants.add(onlinePlayer);
            }
        }
        if (participants.isEmpty()) {
            giveawayRunning = false;
            starter.sendMessage("§cThere are no players online for the giveaway!");
            return;
        }

        final int[] tickCount = {0};
        final int[] lastChangeTick = {0};

        rouletteTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            tickCount[0]++;

            if (tickCount[0] < 60) {
                return;
            }

            int rouletteTick = tickCount[0] - 60;

            if (rouletteTick >= 180) {
                Player winner = selectWinner(participants);
                if (winner != null) {
                    Component winnerTitle = TitleUtil.getTitle("winner", "title", finalItemName, winner.getName(), null, null);
                    Component winnerSubtitle = TitleUtil.getTitle("winner", "subtitle", finalItemName, winner.getName(), null, null);

                    Title winnerTitleObj = Title.title(
                            winnerTitle,
                            winnerSubtitle,
                            Title.Times.times(Ticks.duration(10), Ticks.duration(60), Ticks.duration(10))
                    );

                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.showTitle(winnerTitleObj);
                        onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 1.0f);
                    }

                    winner.getInventory().addItem(prize);
                    winner.sendMessage("§aYou won the giveaway!");
                }

                giveawayRunning = false;
                if (rouletteTask != null) {
                    rouletteTask.cancel();
                }
                return;
            }

            int ticksSinceLastChange = rouletteTick - lastChangeTick[0];
            int changeInterval = 4 + random.nextInt(3);

            if (ticksSinceLastChange >= changeInterval) {
                List<Player> onlineParticipants = new ArrayList<>();
                for (Player p : participants) {
                    if (p.isOnline() && !p.hasPermission("ultimate.giveaway.excluded")) {
                        onlineParticipants.add(p);
                    }
                }

                if (!onlineParticipants.isEmpty()) {
                    Player currentPlayer = onlineParticipants.get(random.nextInt(onlineParticipants.size()));

                    Component rouletteTitle = TitleUtil.getTitle("roulette", "title", finalItemName, null, currentPlayer.getName(), null);
                    Component rouletteSubtitle = TitleUtil.getTitle("roulette", "subtitle", finalItemName, null, currentPlayer.getName(), null);

                    Title rouletteTitleObj = Title.title(
                            rouletteTitle,
                            rouletteSubtitle,
                            Title.Times.times(Ticks.duration(5), Ticks.duration(20), Ticks.duration(5))
                    );

                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.showTitle(rouletteTitleObj);
                        onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.UI_BUTTON_CLICK, 0.5f, 1.2f);
                    }

                    lastChangeTick[0] = rouletteTick;
                }
            }
        }, 0L, 1L);
    }

    private Player selectWinner(List<Player> participants) {
        List<Player> onlineParticipants = new ArrayList<>();
        for (Player p : participants) {
            if (p.isOnline() && !p.hasPermission("ultimate.giveaway.excluded")) {
                onlineParticipants.add(p);
            }
        }

        if (onlineParticipants.isEmpty()) {
            return null;
        }

        return onlineParticipants.get(random.nextInt(onlineParticipants.size()));
    }

    public void cancelGiveaway() {
        if (rouletteTask != null) {
            rouletteTask.cancel();
            rouletteTask = null;
        }
        giveawayRunning = false;
    }

    public boolean isGiveawayRunning() {
        return giveawayRunning;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}

