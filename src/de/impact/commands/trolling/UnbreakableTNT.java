package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class UnbreakableTNT extends Command implements Listener {

    private static boolean isUnbreakable = false;

    public UnbreakableTNT() {
        super("UnbreakableTNT", "UnbreakableTNT", "Breaking tnt makes it even bigger", CommandCategory.TROLLING, "Utnt");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        isUnbreakable = !isUnbreakable;

        ChatUtils.sendMessage(p, "Breaking tnt does §a" + (isUnbreakable ? "make it bigger now" : "no longer makes it bigger"));

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        if(isUnbreakable && e.getBlock().getType().equals(Material.TNT)) {
            e.setCancelled(true);

            for(double x = e.getBlock().getX() - 1; x <= e.getBlock().getX() + 1; x++)
                for(double y = e.getBlock().getY() - 1; y <= e.getBlock().getY() + 1; y++)
                    for(double z = e.getBlock().getZ() - 1; z <= e.getBlock().getZ() + 1; z++)
                        new Location(e.getBlock().getWorld(), x, y, z).getBlock().setType(Material.TNT);
        }

    }

}
