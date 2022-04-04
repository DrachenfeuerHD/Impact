package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

public class DeletePlugin extends Command {

    public DeletePlugin() {
        super("DeletePlugin", "DeletePlugin <Plugin>", "Deletes a plugin from the server", CommandCategory.SERVER, "DelPL");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        ChatUtils.sendMessage(p, "If you want to use this command, you need the Premium version of Impact");

    }

}
