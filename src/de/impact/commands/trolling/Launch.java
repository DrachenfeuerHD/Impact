package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Launch extends Command {

    public Launch() {
        super("Launch", "Launch <Player>", "Launch a player up in the sky", CommandCategory.TROLLING);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        var target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player could not be found");
            return;
        }

        var direction = target.getLocation().getDirection();
        direction.setY(direction.getY() + 150.0D);
        target.setVelocity(direction);

        ChatUtils.sendMessage(p, "Successfully launched §a" + target.getName());

    }

}
