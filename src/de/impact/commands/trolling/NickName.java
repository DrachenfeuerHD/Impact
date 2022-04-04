package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class NickName extends Command {

    public NickName() {
        super("NickName", "NickName <Player> <Name>", "Change the nick-name of a player", CommandCategory.TROLLING, "nn");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 2) {
            sendUsage(p);
            return;
        }

        var name = new StringBuilder();

        for(var i=1;i<aliases.length;i++)
            name.append(aliases[i]).append(" ");

        var target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player could not be found");
            return;
        }

        var nick = (ChatColor.translateAlternateColorCodes('&', name.toString().trim()));

        target.setDisplayName(nick);
        target.setCustomName(nick);
        target.setCustomNameVisible(true);
        target.setPlayerListName(nick);

        ChatUtils.sendMessage(p, "Successfully changed the nick-name of §a" + target.getName());
    }

}
