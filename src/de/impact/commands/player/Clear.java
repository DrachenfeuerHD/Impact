package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Clear extends Command {

    public Clear() {
        super("Clear", "Clear (Player)", "Clear someones inventory", CommandCategory.PLAYER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            p.getInventory().clear();
            ChatUtils.sendMessage(p, "Successfully cleared your inventory");
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        target.getInventory().clear();
        ChatUtils.sendMessage(p, "Successfully cleared the inventory of §a" + target.getName());

    }

}
