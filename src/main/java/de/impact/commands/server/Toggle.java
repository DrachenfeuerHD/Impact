package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Toggle extends Command {

    public Toggle() {
        super("Toggle", "Toggle <Plugin>", "Toggles a plugin", CommandCategory.SERVER, "T");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        Plugin plugin = Bukkit.getPluginManager().getPlugin(aliases[0]);

        if(plugin == null) {
            ChatUtils.sendMessage(p, "This plugin does not exist");
            return;
        }

        if(plugin.isEnabled()) {

            Bukkit.getPluginManager().disablePlugin(plugin);
            ChatUtils.sendMessage(p, "Successfully disabled §a" + plugin.getName());
            return;

        }

        Bukkit.getPluginManager().enablePlugin(plugin);
        ChatUtils.sendMessage(p, "Successfully enabled §a" + plugin.getName());

    }

}
