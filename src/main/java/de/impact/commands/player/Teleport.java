package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Teleport extends Command {

    public Teleport() {
        super("Teleport", "Teleport <Player> (Player)", "Teleport someone to someone", CommandCategory.PLAYER,"TP");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        Player target1 = Bukkit.getPlayer(aliases[0]);

        if(target1 == null) {
            ChatUtils.sendMessage(p, "Player 1 is not online");
            return;
        }

        if(aliases.length == 1) {
            p.teleport(target1);
            ChatUtils.sendMessage(p, "Successfully teleported §ayou §7to §a" + target1.getName());
            return;
        }

        Player target2 = Bukkit.getPlayer(aliases[1]);

        if(target2 == null) {
            ChatUtils.sendMessage(p, "Player 2 is not online");
            return;
        }

        target1.teleport(target2);
        ChatUtils.sendMessage(p, "Successfully teleported §a" + target1.getName() + " §7to §a" + target2.getName());

    }

}
