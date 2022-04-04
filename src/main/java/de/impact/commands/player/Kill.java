package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Kill extends Command {

    public Kill() {
        super("Kill", "Kill (Player)", "Kill someone", CommandCategory.PLAYER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> p.setHealth(0));
            ChatUtils.sendMessage(p, "Successfully killed you");
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> target.setHealth(0));
        ChatUtils.sendMessage(p, "Successfully killed §a" + target.getName());

    }

}
