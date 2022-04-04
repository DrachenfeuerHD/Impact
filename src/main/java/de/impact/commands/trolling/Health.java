package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Health extends Command {

    public Health() {
        super("Health", "Health <Player> <Number>", "Change the max. health of someone", CommandCategory.TROLLING, "SetHealth", "MaxHealth", "SetMaxHealth", "Sh", "Mh", "Smh");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 2) {
            sendUsage(p);
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);
        int healthNumber;
        try {

            healthNumber = Integer.parseInt(aliases[1]);

            if(healthNumber <= 0)
                throw new IllegalArgumentException();

        } catch(Exception ignored) {
            ChatUtils.sendMessage(p, "Please provide a value above 0");
            return;
        }

        if(target == null) {
            ChatUtils.sendMessage(p, "This player could not be found");
            return;
        }

        target.setMaxHealth(healthNumber);
        ChatUtils.sendMessage(p, "Successfully changed the max health of §a" + target.getName() + " §7to §a" + healthNumber);

    }

}
