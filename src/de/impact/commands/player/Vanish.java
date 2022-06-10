package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import de.impact.utils.VanishUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Vanish extends Command {

    public Vanish() {
        super("Vanish", "Vanish (Player)", "Vanish someone", CommandCategory.PLAYER, "V");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            VanishUtils.setVanished(p.getUniqueId(), !VanishUtils.isVanished(p.getUniqueId()));
            ChatUtils.sendMessage(p, "You are §a" + (VanishUtils.isVanished(p.getUniqueId()) ? "now vanished" : "no longer vanished"));
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        VanishUtils.setVanished(target.getUniqueId(), !VanishUtils.isVanished(target.getUniqueId()));
        ChatUtils.sendMessage(p, "§a" + target.getName() + " §7is " + (VanishUtils.isVanished(target.getUniqueId()) ? "now vanished" : "no longer vanished"));

    }

}
