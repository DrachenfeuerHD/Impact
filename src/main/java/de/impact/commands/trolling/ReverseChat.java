package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.UUID;

public class ReverseChat extends Command implements Listener {

    private static final ArrayList<UUID> players = new ArrayList<>();

    public ReverseChat() {
        super("ReverseChat", "ReverseChat <Player>", "Let someone chat in reverse", CommandCategory.TROLLING, "ReverseC", "Reverse");
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

        ChatUtils.sendMessage(p, "§a" + target.getName() + "§7 " + (players.contains(target.getUniqueId()) ? "chats now in reverse" : "no longer chats in reverse"));

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        if(!players.contains(e.getPlayer().getUniqueId()) || e.getMessage().startsWith("/")) return;

        StringBuilder msg = new StringBuilder();
        int length = e.getMessage().length();

        for(int i = length - 1; i >= 0; i--)
            msg.append(e.getMessage().charAt(i));

        e.setMessage(msg.toString().trim());

    }

}
