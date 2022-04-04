package de.impact.commands.world;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

public class Day extends Command {

    public Day() {
        super("Day", "Day", "Set the time to day", CommandCategory.WORLD);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        p.getWorld().setTime(6000);
        ChatUtils.sendMessage(p, "Successfully changed the time to day");

    }

}
