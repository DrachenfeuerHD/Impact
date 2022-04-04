package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import de.impact.utils.lock.LockUtils;
import de.impact.utils.lock.Locks;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Lock extends Command {

    private static String keys;

    static {
        keys = String.valueOf(Arrays.stream(Locks.values()).map(s -> s.name().substring(0, 1).toUpperCase() + s.name().substring(1)).collect(Collectors.joining(", ")));
    }

    public Lock() {
        super("Lock", "Lock <" + keys + ">", "Lock some features", CommandCategory.SERVER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1 || !LockUtils.containsLock(Locks.valueOf(aliases[0].toLowerCase()))) {
            sendUsage(p);
            return;
        }

        String lockRequest = aliases[0].toLowerCase();

        LockUtils.setLocked(Locks.valueOf(lockRequest), !LockUtils.isLocked(Locks.valueOf(lockRequest)));

        ChatUtils.sendMessage(p, "You've toggled §a" + (lockRequest.substring(0,1).toUpperCase() + lockRequest.substring(1)) + " §7to " + (LockUtils.isLocked(Locks.valueOf(lockRequest)) ? "§alocked" : "§cunlocked"));

    }

}
