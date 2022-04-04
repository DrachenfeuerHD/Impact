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

        List<Block> blocks = new ArrayList<Block>();

        for(double x = -radius; x<radius; x++)
            for(double y = -radius; y<radius; y++)
                for(double z = -radius; z<radius; z++)
                    blocks.add(loc.getWorld().getBlockAt((int) (loc.getBlockX() + x), (int) (loc.getBlockY() + y), (int) (loc.getBlockZ() + z)));
        return blocks;
    }

    public static void setBlocksAround(Location loc, int radius, Material m) {

        for(Block b : getBlocksAround(loc, radius))
            b.setType(m);

    }

}
