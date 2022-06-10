package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class IP extends Command {

    public IP() {
        super("IP", "IP <Player>", "Shows someones ip to you", CommandCategory.PLAYER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        ChatUtils.sendMessage(p, "The IP of §a" + target.getName() + " §7is §a" + target.getAddress().toString().replaceFirst("/", "").split(":")[0]);

    }

}
