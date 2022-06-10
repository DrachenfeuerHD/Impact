package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.UUID;

public class FakeCheater extends Command implements Listener {

    private static final ArrayList<UUID> players = new ArrayList<>();

    public FakeCheater() {
        super("FakeCheater", "FakeCheater <Player>", "Make someone look like he is cheating", CommandCategory.TROLLING, "FakeHacker", "FCheater", "FHacker");
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

        ChatUtils.sendMessage(p, "§a"+target.getName() + "§7 " + (players.contains(target.getUniqueId()) ? "now" : "no longer") + " looks like he is cheating");

    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        Player p = e.getPlayer();

        if(!players.contains(p.getUniqueId())) return;

        p.setAllowFlight(true);
        p.setVelocity(p.getLocation().getDirection().setZ(0.3D).setX(0.3D));
        p.setVelocity(p.getLocation().getDirection().setZ(-0.3D).setX(-0.3D));
        p.setVelocity(p.getLocation().getDirection().setY(-9));
        p.setAllowFlight(false);
        p.setHealth(20);
        p.setFoodLevel(20);

    }

}
