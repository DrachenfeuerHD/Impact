package de.impact.commands.plugin;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import de.impact.utils.UserUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerChat extends Command {

    public ServerChat() {
        super("ServerChat", "ServerChat <Message>", "Send a message to other signed in users", CommandCategory.PLUGIN, "Sc");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        var chatMessage = new StringBuilder();

        for (var s : aliases)
            chatMessage.append(s).append(" ");

        if (chatMessage.toString().equals("")) return;

        for(var uuid : UserUtils.getLoggedInUsers())
            Bukkit.getPlayer(uuid).sendMessage(ChatUtils.getServerChatPrefix() + "§7User " + p.getName() + " §7❯ §a" + chatMessage.toString().trim());
    }

}
