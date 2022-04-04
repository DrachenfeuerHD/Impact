package de.impact.commands.griefing;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Unban extends Command {

    public Unban() {
        super("Unban", "Unban <Player>", "Unban someone", CommandCategory.GRIEFING,"pardon");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player could not be found");
            return;
        }

        if(!target.isBanned()) {
            ChatUtils.sendMessage(p, "This player is not banned");
            return;
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> target.setBanned(false));

        ChatUtils.sendMessage(p, "Successfully unbanned §a" + target.getName());

    }

}
