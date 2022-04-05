package de.impact.commands.plugin;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import de.impact.utils.Locks;
import org.bukkit.entity.Player;

public class Status extends Command {

    public Status() {
        super("Status", "Status", "Display the current plugin status", CommandCategory.PLUGIN, "Stats");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        ChatUtils.sendDefault(p, "§a");

        for(Locks s : Locks.values())
            ChatUtils.sendMessage(p, (s.name().charAt(0) + s.name().substring(1).toLowerCase()) + " locked: " + (s.isLocked() ? "§aYes" : "§cNo"));


    }

}
