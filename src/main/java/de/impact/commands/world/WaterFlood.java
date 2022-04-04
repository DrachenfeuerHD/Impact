package de.impact.commands.world;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class WaterFlood extends Command implements Listener {

    private static boolean isFlooding = false;

    public WaterFlood() {
        super("WaterFlood", "WaterFlood", "Flood everything with water", CommandCategory.WORLD, "wf");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        isFlooding = !isFlooding;

        if(isFlooding) {

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> p.getLocation().getBlock().setType(Material.WATER));
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> p.getLocation().getBlock().setBiome(Biome.SKY));

        }

        ChatUtils.sendMessage(p, "The water is §a" + (isFlooding ? "now flooding everything" : "no longer flooding everything"));

    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent e) {

        if(isFlooding && (e.getBlock().getType().equals(Material.WATER) || e.getBlock().getType().equals(Material.STATIONARY_WATER)) && e.getBlock().getBiome().equals(Biome.SKY)) {

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                for (double x = e.getBlock().getLocation().getX() - 1; x <= e.getBlock().getLocation().getX() + 1; x++)
                    for (double z = e.getBlock().getLocation().getZ() - 1; z <= e.getBlock().getLocation().getZ() + 1; z++) {

                        new Location(e.getBlock().getWorld(), x, e.getBlock().getLocation().getY() + 1, z).getBlock().setType(Material.WATER);
                        new Location(e.getBlock().getWorld(), x, e.getBlock().getLocation().getY() + 1, z).getBlock().setBiome(Biome.SKY);

                    }
            });
        }

    }

}
