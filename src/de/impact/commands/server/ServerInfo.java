package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

public class ServerInfo extends Command {

    public ServerInfo() {
        super("ServerInfo", "ServerInfo", "View detailed information about the server", CommandCategory.SERVER, "Si");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        ChatUtils.sendMessage(p, "If you want to use this command, you need the Premium version of Impact");

    }

}
