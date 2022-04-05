package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Enderchest extends Command {

    public Enderchest() {
        super("Enderchest", "Enderchest (Player)", "View the enderchest of a player", CommandCategory.PLAYER, "Ec");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            p.openInventory(p.getEnderChest());
            ChatUtils.sendMessage(p, "You are now viewing your enderchest");
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        p.openInventory(target.getEnderChest());
        ChatUtils.sendMessage(p, "You are now viewing the enderchest of §a" + target.getName());

    }

}
