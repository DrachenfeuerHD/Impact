package de.impact.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class WorldUtils {

    private WorldUtils() {
        throw new IllegalArgumentException("Utility Class");
    }

    public static List<Block> getBlocksAround(Location loc, int radius) {

        var blocks = new ArrayList<Block>();

        for(var x = -radius; x<radius; x++)
            for(var y = -radius; y<radius; y++)
                for(var z = -radius; z<radius; z++)
                    blocks.add(loc.getWorld().getBlockAt(loc.getBlockX() + x, loc.getBlockY() + y, loc.getBlockZ() + z));
        return blocks;
    }

    public static void setBlocksAround(Location loc, int radius, Material m) {

        for(var b : getBlocksAround(loc, radius))
            b.setType(m);

    }

}
