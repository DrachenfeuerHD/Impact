package de.impact.commands.world;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

public class Night extends Command {

    public Night() {
        super("Night", "Night", "Set the time to night", CommandCategory.WORLD);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        p.getWorld().setTime(18000);
        ChatUtils.sendMessage(p, "Successfully changed the time to night");

    }

}
