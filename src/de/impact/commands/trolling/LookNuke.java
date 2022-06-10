package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.commands.eventhandler.TickTimer;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class LookNuke extends Command implements TickTimer {

    private static final ArrayList<UUID> players = new ArrayList<>();

    public LookNuke() {
        super("LookNuke", "LookNuke <Player>", "Let someone delete every block they look at", CommandCategory.TROLLING, "Ln", "Looknuker");
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

        ChatUtils.sendMessage(p, "§a" + target.getName() + "§7 " + (players.contains(target.getUniqueId()) ? "now deletes " : "no longer deletes ") + "every block he looks at");

    }

    public void onTick() {
        for(UUID p : players) {
            if(Bukkit.getPlayer(p) == null) return;

            Bukkit.getPlayer(p).getTargetBlock((HashSet<Byte>) null, 200).getLocation().getBlock().setType(Material.AIR);
        }
    }

}
