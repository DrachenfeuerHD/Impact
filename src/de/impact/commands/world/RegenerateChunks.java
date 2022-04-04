package de.impact.commands.world;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

public class RegenerateChunks extends Command {

    public RegenerateChunks() {
        super("RegenerateChunks", "RegenerateChunks", "Regenerates all loaded chunks", CommandCategory.WORLD, "RegenC", "Regen", "RC");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        ChatUtils.sendMessage(p, "If you want to use this command, you need the Premium version of Impact");

    }

}
