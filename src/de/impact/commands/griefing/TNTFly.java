package de.impact.commands.griefing;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.commands.eventhandler.TickTimer;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.UUID;

public class TNTFly extends Command implements TickTimer {

    private static final ArrayList<UUID> players = new ArrayList<>();

    public TNTFly() {
        super("TNTFly", "TNTFly <Player>", "Let someone fly up with TNT", CommandCategory.GRIEFING, "TFly");
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

        ChatUtils.sendMessage(p, "§a" + target.getName() + "§7 " + (players.contains(target.getUniqueId()) ? "now flys " : "no longer flys ") + "up with tnt");

    }

    public void onTick() {
        for(UUID p : players) {

            if(Bukkit.getPlayer(p) == null) return;

            Bukkit.getPlayer(p).setVelocity(new Vector(0.0D, 0.4D, 0.0D));
            Bukkit.getPlayer(p).getWorld().spawnEntity(Bukkit.getPlayer(p).getLocation(), EntityType.PRIMED_TNT);
        }
    }

}
