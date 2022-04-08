package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Fly extends Command {

    public Fly() {
        super("Fly", "Fly (Player)", "Enable or disable someones ability fly", CommandCategory.PLAYER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                p.setAllowFlight(!p.getAllowFlight());
                ChatUtils.sendMessage(p, "§aYou §7" + (p.getAllowFlight() ? "can fly now" : "can no longer fly"));
            });
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
            target.setAllowFlight(!target.getAllowFlight());
            ChatUtils.sendMessage(p, "§a"+target.getName() + " §7" + (target.getAllowFlight() ? "can fly now" : "can no longer fly"));
        });

    }

}
