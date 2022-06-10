package de.impact.commands.world;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

public class RegenerateChunks extends Command {

    public RegenerateChunks() {
        super("RegenerateChunks", "RegenerateChunks", "Regenerates all loaded chunks", CommandCategory.WORLD, "RegenC", "Regen", "RC");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
            for(Chunk chunk : p.getWorld().getLoadedChunks()) {
                p.getWorld().regenerateChunk(chunk.getX(), chunk.getZ());
                p.getWorld().unloadChunk(chunk.getX(), chunk.getZ());
                p.getWorld().loadChunk(chunk.getX(), chunk.getZ());
            }

            ChatUtils.sendMessage(p, "Successfully regenerated the chunks");
        }, 0);

    }

}
