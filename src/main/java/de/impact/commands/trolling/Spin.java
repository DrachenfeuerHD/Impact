package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.commands.eventhandler.TickTimer;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Spin extends Command implements TickTimer {

    private static final ArrayList<UUID> players = new ArrayList<>();

    public Spin() {
        super("Spin", "Spin <Player>", "Let someone spin", CommandCategory.TROLLING);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player could not be found");
            return;
        }

        if(players.contains(target.getUniqueId())) {
            players.remove(target.getUniqueId());
        } else {
            players.add(target.getUniqueId());
        }

        ChatUtils.sendMessage(p, "§a" + target.getName() + "§7 " + (players.contains(target.getUniqueId()) ? "is now spinning" : "no longer spins"));

    }

    public void onTick() {
        for(UUID p : players) {

            Player player = Bukkit.getPlayer(p);

            if(player == null) return;

            Location loc = player.getLocation();

            loc.setYaw(loc.getYaw() + 10);
            loc.setPitch(-90);

            player.teleport(loc);

        }
    }

}
