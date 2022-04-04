package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class BlockScreen extends Command {

    private static final ArrayList<UUID> players = new ArrayList<>();

    public BlockScreen() {
        super("BlockScreen", "BlockScreen <Player>", "Block someone from opening inventory's", CommandCategory.TROLLING, "Bi", "BlockInventory");
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

        ChatUtils.sendMessage(p, "§a"+target.getName() + " §7can " + (players.contains(target.getUniqueId()) ? "no longer" : "now") + " open inventory's");

    }

    public static void onTick() {
        for(var p : players) {
            if(Bukkit.getPlayer(p) == null) return;

            Bukkit.getPlayer(p).closeInventory();
        }
    }

}
