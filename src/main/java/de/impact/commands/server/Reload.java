package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import de.impact.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Reload extends Command {

    public Reload() {
        super("Reload", "Reload", "Reloads every plugin except Impact", CommandCategory.SERVER, "Rl");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        for(Plugin pl : Bukkit.getPluginManager().getPlugins()) {
            if(pl.equals(Main.getPlugin())) continue;

            PluginUtils.unload(pl);
            PluginUtils.load(pl.getName());

        }

        ChatUtils.sendMessage(p, "Successfully reloaded every plugin except Impact");

    }

}
