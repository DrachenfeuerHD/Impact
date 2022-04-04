package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Heal extends Command {

    public Heal() {
        super("Heal", "Heal (Player)", "Heal someone", CommandCategory.PLAYER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            p.setHealth(p.getMaxHealth());
            ChatUtils.sendMessage(p, "§aYou §7have been healed");
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        target.setHealth(target.getMaxHealth());
        ChatUtils.sendMessage(p, "§a"+target.getName() + " §7has been healed");

    }

}
