package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;
import java.util.UUID;

public class BlockPlacer extends Command implements Listener {

    private static final HashMap<UUID, Material> players = new HashMap<>();

    public BlockPlacer() {
        super("BlockPlacer", "BlockPlacer <Player> <Material>", "Every block the placer places will be your material", CommandCategory.TROLLING, "Bp");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        try {

            Player target = Bukkit.getPlayer(aliases[0]);

            if(players.containsKey(target.getUniqueId())) {
                ChatUtils.sendMessage(p, "§a" + target.getName() + " §7no longer can only place §a" + players.get(target.getUniqueId()).name());
                players.remove(target.getUniqueId());
                return;
            }

            Material mat = Material.valueOf(aliases[1].toUpperCase());

            players.put(target.getUniqueId(), mat);

            ChatUtils.sendMessage(p, "§a" + target.getName() + " §7can now only place §a" + mat.name());


        } catch(Exception ignored) {
            sendUsage(p);
        }

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {

        if(e.getPlayer() == null || !players.containsKey(e.getPlayer().getUniqueId())) return;

        e.getBlock().setType(players.get(e.getPlayer().getUniqueId()));

    }

}
