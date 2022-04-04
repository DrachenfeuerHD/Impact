package de.impact.commands.griefing;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Ban extends Command {

    public Ban() {
        super("Ban", "Ban <Player>", "Ban someone", CommandCategory.GRIEFING);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        try {

            var target = Bukkit.getPlayer(aliases[0]);
            var offlineTarget = Bukkit.getOfflinePlayer(aliases[0]);

            if(target != null) {

                if(target.isBanned()) {
                    ChatUtils.sendMessage(p, "This player is already banned");
                    return;
                }

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                    target.setBanned(true);
                    target.kickPlayer("You are banned from this server.");
                });
                ChatUtils.sendMessage(p, "Successfully banned §a" + target.getName());

            } else if(offlineTarget != null) {

                if(offlineTarget.isBanned()) {
                    ChatUtils.sendMessage(p, "This player is already banned");
                    return;
                }

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> offlineTarget.setBanned(true));
                ChatUtils.sendMessage(p, "Successfully banned §a" + offlineTarget.getName());

            } else {
                ChatUtils.sendMessage(p, "This player could not be found");
            }

        } catch(Exception ignored) {
            sendUsage(p);
        }

    }

}
