package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Broadcast extends Command {

    public Broadcast() {
        super("Broadcast", "Broadcast <Message>", "Broadcast a message", CommandCategory.SERVER, "Bc");
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

        ChatUtils.broadcastDefault(chatMessage.toString().trim());

    }

}
