package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Spam extends Command {

    public Spam() {
        super("Spam", "Spam <Message>", "Spams a broadcast 50 times", CommandCategory.SERVER, "SBc");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if (aliases.length < 1) {
            sendUsage(p);
            return;
        }

        var chatMessage = new StringBuilder();

        for(var s : aliases)
            chatMessage.append(ChatColor.translateAlternateColorCodes('&', s)).append(" ");

        if(chatMessage.toString().equals("")) return;

        for(var i=0;i<50;i++)
            ChatUtils.broadcastDefault(chatMessage.toString().trim());

    }

}
