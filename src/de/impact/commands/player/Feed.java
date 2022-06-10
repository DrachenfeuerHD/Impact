package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Feed extends Command {

    public Feed() {
        super("Feed", "Feed (Player)", "Feed someone", CommandCategory.PLAYER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            p.setFoodLevel(20);
            ChatUtils.sendMessage(p, "§aYou §7have been fed");
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        target.setFoodLevel(20);
        ChatUtils.sendMessage(p, "§a"+target.getName() + " §7has been fed");

    }

}
