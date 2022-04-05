package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.UUID;

public class AntiPickup extends Command implements Listener {

    private static final ArrayList<UUID> players = new ArrayList<>();

    public AntiPickup() {
        super("AntiPickup", "AntiPickup <Player>", "Let someone not pickup items", CommandCategory.TROLLING, "AP");
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

        ChatUtils.sendMessage(p, "§a" + target.getName() + "§7 " + (players.contains(target.getUniqueId()) ? "can no longer pickup items" : "can now pickup items"));

    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {

        if(!players.contains(e.getPlayer().getUniqueId())) return;

        e.setCancelled(true);

        Vector playerYaw = e.getPlayer().getEyeLocation().getDirection();

        playerYaw.multiply(0.5);

        playerYaw.setY(0.5);

        e.getItem().setVelocity(playerYaw);

    }

}
