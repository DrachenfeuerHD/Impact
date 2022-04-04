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

public class Freeze extends Command implements Listener {

    private static final ArrayList<UUID> players = new ArrayList<>();

    public Freeze() {
        super("Freeze", "Freeze <Player>", "Freeze someone", CommandCategory.TROLLING);
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

        ChatUtils.sendMessage(p, "§a"+target.getName() + " §7is " + (players.contains(target.getUniqueId()) ? "now" : "no longer") + " frozen");

    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        if(players.contains(e.getPlayer().getUniqueId()))
            e.setTo(e.getPlayer().getLocation());

    }

}
