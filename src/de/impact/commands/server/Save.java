package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Save extends Command {

    public Save() {
        super("Save", "Save", "Saves the server and world", CommandCategory.SERVER, "s");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        Bukkit.getServer().savePlayers();

        Bukkit.getWorlds().forEach(World::save);

        ChatUtils.sendMessage(p, "Successfully saved everything");

    }

}
