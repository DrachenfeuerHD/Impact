package de.impact.commands.world;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class RegenerateChunks extends Command {

    public RegenerateChunks() {
        super("RegenerateChunks", "RegenerateChunks", "Regenerates all loaded chunks", CommandCategory.WORLD, "RegenC", "Regen", "RC");
    }

    @Override
    public void onChat(String[] aliases, Player p) {


        Bukkit.getScheduler().runTask(Main.getPlugin(), () -> Arrays.stream(p.getWorld().getLoadedChunks()).forEach(chunk -> {
            chunk.unload();
            p.getWorld().regenerateChunk(chunk.getX(), chunk.getZ());
            chunk.load();

        }));

    }

}
