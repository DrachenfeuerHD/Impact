package de.impact.commands.plugin;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

public class Ranks extends Command {

    public Ranks() {
        super("Ranks", "Ranks", "Displays the available ranks", CommandCategory.PLUGIN, "R");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        ChatUtils.sendMessage(p, "§a");
        ChatUtils.sendMessage(p, "§m-----§a Ranks §7§m-----");
        ChatUtils.sendMessage(p, "§a");
        ChatUtils.sendMessage(p, "§7User");
        ChatUtils.sendMessage(p, "§aMore ranks & features in the paid version of Impact");

    }
}
