package de.impact.commands.griefing;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Kick extends Command {

    public Kick() {
        super("Kick", "Kick <Player> (Message)", "Kick someone", CommandCategory.GRIEFING, "K");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        var target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        var reason = new StringBuilder();

        if(aliases.length >= 2) {
            for(var i = 1; i < aliases.length; i++) {
                reason.append(ChatColor.translateAlternateColorCodes('&', aliases[i]).trim()).append(" ");
            }
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> target.kickPlayer(reason.toString()));

        ChatUtils.sendMessage(p, "Successfully kicked §a" + target.getName() + " for " + (reason.toString().isEmpty() ? "no reason" : reason.toString()));

    }

}
