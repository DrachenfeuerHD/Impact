package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class FailCommand extends Command {

    private static final ArrayList<UUID> players = new ArrayList<>();

    public FailCommand() {
        super("FailCommand", "FailCommand <Player>", "Let someone fail every command", CommandCategory.TROLLING, "FC");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player could not be found");
            return;
        }

        if(players.contains(target.getUniqueId())) {
            players.remove(target.getUniqueId());
        } else {
            players.add(target.getUniqueId());
        }

        ChatUtils.sendMessage(p, "§a"+target.getName() + " §7is " + (players.contains(target.getUniqueId()) ? "now" : "no longer") + " failing commands");

    }

    public static boolean shouldFail(UUID uuid) {
        return players.contains(uuid);
    }

}
