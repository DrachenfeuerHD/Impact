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

    private static final ArrayList<Player> players = new ArrayList<>();

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

        if(players.contains(target)) {
            players.remove(target);
        } else {
            players.add(target);
        }

        ChatUtils.sendMessage(p, "§a" + target.getName() + "§7 " + (players.contains(target) ? "now flys " : "no longer flys ") + "up with tnt");

    }

    public void onTick() {
        for(Player p : players) {
            p.setVelocity(new Vector(0.0D, 0.4D, 0.0D));
            p.getWorld().spawnEntity(p.getLocation(), EntityType.PRIMED_TNT);
        }
    }

}
