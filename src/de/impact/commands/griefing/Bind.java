package de.impact.commands.griefing;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Bind extends Command implements Listener {

    public Bind() {
        super("Bind", "Bind <Material | Command | ClearBinds | ShowBinds | ClearCommand | ClearBrush> (Radius)", "Bind a command or brush to an item", CommandCategory.GRIEFING);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        ChatUtils.sendMessage(p, "If you want to use this command, you need the Premium version of Impact");

    }

}
