package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;

public class ClearChat extends Command {

    public ClearChat() {
        super("ClearChat", "ClearChat", "Clears the chat", CommandCategory.SERVER, "Cc");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        for(var i=0;i<1000;i++)
            ChatUtils.broadcastDefault("§a");

        ChatUtils.sendMessage(p, "Successfully cleared the chat");

    }

}
