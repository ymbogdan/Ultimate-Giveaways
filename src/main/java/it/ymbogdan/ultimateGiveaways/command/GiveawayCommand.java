package it.ymbogdan.ultimateGiveaways.command;

import it.ymbogdan.ultimateGiveaways.manager.GiveawayManager;
import it.ymbogdan.ultimateGiveaways.util.TitleUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveawayCommand implements CommandExecutor {

    private final GiveawayManager giveawayManager;

    public GiveawayCommand(GiveawayManager giveawayManager) {
        this.giveawayManager = giveawayManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be executed by one player!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("§cUsage: /giveaway <start|reload>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "start":
                if (!player.hasPermission("ultimate.giveaway.start")) {
                    player.sendMessage("§cYou do not have permission to execute this command!");
                    return true;
                }
                giveawayManager.startGiveaway(player);
                break;
            case "reload":
                if (!player.hasPermission("ultimate.giveaway.reload")) {
                    player.sendMessage("§cYou do not have permission to execute this command!");
                    return true;
                }
                giveawayManager.getPlugin().reloadConfig();
                TitleUtil.reloadCache();
                player.sendMessage("§aConfig reloaded successfully!");
                break;
            default:
                player.sendMessage("§cUsage: /giveaway <start|reload>");
                break;
        }

        return true;
    }
}

