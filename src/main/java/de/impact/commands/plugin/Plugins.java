package de.impact.commands.plugin;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class Plugins extends Command {

    public Plugins() {
        super("Plugins", "Plugins", "Shows a plugin list", CommandCategory.PLUGIN, "Pl");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        Plugin[] installedPlugins = Bukkit.getPluginManager().getPlugins();
        int enabledPlugins = (int) Arrays.stream(installedPlugins).filter(Plugin::isEnabled).count();
        StringBuilder message = new StringBuilder();

        message.append("§7Plugins (§b").append(installedPlugins.length).append("§7/§a").append(enabledPlugins).append("§7/§c").append(installedPlugins.length - enabledPlugins).append("§7) ");

        for(int i=0;i<installedPlugins.length;i++) {

            Plugin pl = installedPlugins[i];

            if(i==installedPlugins.length-1) {
                message.append(pl.isEnabled() ? "§a" : "§c").append(pl.getName()).append(" §2").append(pl.getClass().getProtectionDomain().getCodeSource().getLocation().getFile().split("plugins/")[1]);
                continue;
            }

            message.append(pl.isEnabled() ? "§a" : "§c").append(pl.getName()).append(" §2").append(pl.getClass().getProtectionDomain().getCodeSource().getLocation().getFile().split("plugins/")[1]).append("§7, ");

        }

        ChatUtils.sendMessage(p, message.toString());

    }

}
