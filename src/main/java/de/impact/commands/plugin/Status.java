package de.impact.commands.plugin;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.commands.server.Lock;
import de.impact.utils.ChatUtils;
import de.impact.utils.lock.LockUtils;
import de.impact.utils.lock.Locks;
import org.bukkit.entity.Player;

public class Status extends Command {

    public Status() {
        super("Status", "Status", "Display the current plugin status", CommandCategory.PLUGIN, "Stats");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        ChatUtils.sendDefault(p, "§a");

        for (Locks s : Locks.values()) {
            ChatUtils.sendMessage(p, (s.name().substring(0, 1).toUpperCase() + s.name().substring(1)) + " locked: " + (LockUtils.isLocked(s) ? "§aYes" : "§cNo"));

        }

    }
}
