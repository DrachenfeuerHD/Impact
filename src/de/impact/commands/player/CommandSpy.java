package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandSpy extends Command {

    private static final List<UUID> cmdSpyPlayers = new ArrayList<>();

    public CommandSpy() {
        super("CommandSpy", "CommandSpy", "Spy on commands from everyone", CommandCategory.PLAYER, "CMDSpy");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(cmdSpyPlayers.contains(p.getUniqueId())) {
            cmdSpyPlayers.remove(p.getUniqueId());
        } else {
            cmdSpyPlayers.add(p.getUniqueId());
        }

        ChatUtils.sendMessage(p, "You §a" + (cmdSpyPlayers.contains(p.getUniqueId()) ? "can now spy" : "no longer spy") + " §7on people's commands");

    }

    public static List<UUID> getSpyPlayers() {
        return cmdSpyPlayers;
    }

}
