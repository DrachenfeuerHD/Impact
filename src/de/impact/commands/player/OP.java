package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OP extends Command {

    public OP() {
        super("OP", "OP (Player)", "Give or remove someone op", CommandCategory.PLAYER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            p.setOp(!p.isOp());
            ChatUtils.sendMessage(p, "§aYou §7are §a" + (p.isOp() ? "now a operator" : "no longer a operator"));
            return;
        }

        var target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        target.setOp(!target.isOp());

        ChatUtils.sendMessage(p, "§a" + target.getName() + " §7is §a" + (target.isOp() ? "now a operator" : "no longer a operator"));

    }
}
