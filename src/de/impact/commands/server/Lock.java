package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import de.impact.utils.LockUtils;
import org.bukkit.entity.Player;

public class Lock extends Command {

    private static String keys;

    static {
        keys = String.valueOf(LockUtils.getLocks().stream().map(s -> s.substring(0, 1).toUpperCase() + s.substring(1)).toList());
        keys = keys.replace(", ", " | ");
        keys = keys.substring(1, keys.length() - 2);
    }

    public Lock() {
        super("Lock", "Lock <" + keys + ">", "Lock some features", CommandCategory.SERVER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1 || !LockUtils.containsLock(aliases[0].toLowerCase())) {
            sendUsage(p);
            return;
        }

        var lockRequest = aliases[0].toLowerCase();

        LockUtils.setLocked(lockRequest, !LockUtils.isLocked(lockRequest));

        ChatUtils.sendMessage(p, "You've toggled §a" + (lockRequest.substring(0,1).toUpperCase() + lockRequest.substring(1)) + " §7to " + (LockUtils.isLocked(lockRequest) ? "§alocked" : "§cunlocked"));

    }

}
