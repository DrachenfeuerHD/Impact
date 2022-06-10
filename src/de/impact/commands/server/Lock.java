package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import de.impact.utils.Locks;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Lock extends Command {

    private static String keys;

    static {
        keys = Arrays.stream(Locks.values()).map(s -> s.name().charAt(0) + s.name().substring(1).toLowerCase()).collect(Collectors.joining(", "));
        keys = keys.replace(", ", " | ");
    }

    public Lock() {
        super("Lock", "Lock <" + keys + ">", "Lock some features", CommandCategory.SERVER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1 || !Locks.containsLock(aliases[0].toUpperCase())) {
            sendUsage(p);
            return;
        }

        Locks lockRequest = Locks.valueOf(aliases[0].toUpperCase());

        lockRequest.setLocked(!lockRequest.isLocked());

        ChatUtils.sendMessage(p, "You've toggled §a" + (lockRequest.name().charAt(0) + lockRequest.name().substring(1).toLowerCase()) + " §7to " + (lockRequest.isLocked() ? "§alocked" : "§cunlocked"));

    }

}
