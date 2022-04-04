package de.impact.commands.griefing;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.commands.eventhandler.TickTimer;
import de.impact.utils.ChatUtils;
import de.impact.utils.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Nuker extends Command implements TickTimer {

    private static final ArrayList<UUID> players = new ArrayList<>();

    public Nuker() {
        super("Nuker", "Nuker <Player>", "Let someone nuke everything around him", CommandCategory.GRIEFING,  "Nuke");
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

        if(players.contains(target.getUniqueId())) {
            players.remove(target.getUniqueId());
        } else {
            players.add(target.getUniqueId());
        }

        ChatUtils.sendMessage(p, "§a"+target.getName() + " §7is " + (players.contains(target.getUniqueId()) ? "now" : "no longer") + " nuking");

    }

    public void onTick() {
        for(var p : players) {
            if(Bukkit.getPlayer(p) == null) return;

            WorldUtils.setBlocksAround(Bukkit.getPlayer(p).getLocation(), 3, Material.AIR);
        }
    }

}
