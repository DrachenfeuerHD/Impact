package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OwnWorld extends Command {

    public OwnWorld() {
        super("OwnWorld", "OwnWorld <Player>", "Send someone to his own world", CommandCategory.TROLLING, "Autism", "Ow");
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

        target.remove();
        ChatUtils.sendMessage(p, "Successfully sent §a" + target.getName() + " §7to his own world");

    }

}
