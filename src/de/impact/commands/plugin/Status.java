package de.impact.commands.plugin;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import de.impact.utils.LockUtils;
import org.bukkit.entity.Player;

public class Status extends Command {

    public Status() {
        super("Status", "Status", "Display the current plugin status", CommandCategory.PLUGIN, "Stats");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        ChatUtils.sendDefault(p, "§a");

        for(var s : LockUtils.getLocks())
            ChatUtils.sendMessage(p, (s.substring(0,1).toUpperCase() + s.substring(1)) + " locked: " + (LockUtils.isLocked(s) ? "§aYes" : "§cNo"));

    }

}
