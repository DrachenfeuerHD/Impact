package de.impact.commands.plugin;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.UserUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Logout extends Command implements Listener {

    public Logout() {
        super("Logout", "Logout", "Logout yourself", CommandCategory.PLUGIN);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        UserUtils.logout(p.getUniqueId());

    }

}
