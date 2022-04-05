package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TPAll extends Command {

    public TPAll() {
        super("TPAll", "TPAll (Player)", "Teleport everyone to someone", CommandCategory.PLAYER, "TPAll");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            for(Player onlinePlayer : Bukkit.getOnlinePlayers())
                onlinePlayer.teleport(p);

            ChatUtils.sendMessage(p, "Successfully teleported everyone to §ayou");
            return;
        }

        Player target1 = Bukkit.getPlayer(aliases[0]);

        if(target1 == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        for(Player onlinePlayer : Bukkit.getOnlinePlayers())
            onlinePlayer.teleport(target1);

        ChatUtils.sendMessage(p, "Successfully teleported everyone to §a" + target1.getName());

    }

}
