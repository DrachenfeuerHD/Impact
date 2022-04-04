package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Invsee extends Command {

    public Invsee() {
        super("Invsee", "Invsee <Player>", "Edit someones inventory", CommandCategory.PLAYER, "Inv", "ISee");
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

        p.openInventory(target.getInventory());
        ChatUtils.sendMessage(p, "You are now viewing the inventory of §a" + target.getName());

    }

}
