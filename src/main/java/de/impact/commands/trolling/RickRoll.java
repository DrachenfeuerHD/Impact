package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

public class RickRoll extends Command {

    public RickRoll() {
        super("RickRoll", "RickRoll <Player>", "RickRoll someone", CommandCategory.TROLLING, "RR");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        ChatUtils.sendMessage(p, "If you want to use this command, you need the Premium version of Impact");

    }

}
