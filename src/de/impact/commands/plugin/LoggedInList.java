package de.impact.commands.plugin;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import de.impact.utils.UserUtils;
import de.impact.utils.VanishUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LoggedInList extends Command {

    public LoggedInList() {
        super("LoggedInList", "LoggedInList", "Shows who is currently logged in", CommandCategory.PLUGIN, "Lil", "PList", "LoggedIns", "LoggedIn");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        var msg = new StringBuilder();

        for(var uuid : UserUtils.getLoggedInUsers()) {

            msg.append("§7").append(Bukkit.getPlayer(uuid).getName()).append(VanishUtils.isVanished(uuid) ? " §cVANISHED" : "").append(" ");

        }

        ChatUtils.sendDefault(p, "§a");
        ChatUtils.sendMessage(p, "§m-----§7 User §7§m-----");
        ChatUtils.sendMessage(p, "§a");
        ChatUtils.sendMessage(p, msg.toString());

    }

}
