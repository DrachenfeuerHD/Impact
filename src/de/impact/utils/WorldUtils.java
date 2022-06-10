package de.impact.utils;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldUtils {

    private WorldUtils() {
        throw new IllegalArgumentException("Utility Class");
    }

    public static List<Block> getBlocksAround(Location loc, int radius) {

        ArrayList<Block> blocks = new ArrayList<>();

        for(int x = -radius; x<radius; x++)
            for(int y = -radius; y<radius; y++)
                for(int z = -radius; z<radius; z++)
                    blocks.add(loc.getWorld().getBlockAt(loc.getBlockX() + x, loc.getBlockY() + y, loc.getBlockZ() + z));
        return blocks;
    }

    public static void setBlocksAround(Location loc, int radius, Material m) {

        for(Block b : getBlocksAround(loc, radius))
            b.setType(m);

    }

    public static void setBlocksAroundRandom(Location loc, int radius, Material[] m) {

        Random r = new Random();

        for(Block b : getBlocksAround(loc, radius))
            b.setType(m[r.nextInt(m.length)]);

    }

    public static NBTTagCompound getNBT(ItemStack item) {

        net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(item);

        return nms.hasTag() ? nms.getTag() : new NBTTagCompound();
    }

    public static ItemStack applyNBT(NBTTagCompound nbt, ItemStack item) {

        net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(item);

        nms.setTag(nbt);

        return CraftItemStack.asBukkitCopy(nms);
    }

}
