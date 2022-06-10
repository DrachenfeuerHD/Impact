package de.impact.commands.griefing;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import de.impact.utils.UserUtils;
import de.impact.utils.WorldUtils;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

public class Bind extends Command implements Listener {

    private static final String BRUSH_NAME = "Brush";
    private static final String COMMAND_NAME= "Command";

    public Bind() {
        super("Bind", "Bind <Material | Command | ClearBinds | ShowBinds | ClearCommand | ClearBrush> (Radius)", "Bind a command or brush to an item", CommandCategory.GRIEFING);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        if(p.getItemInHand() == null || p.getItemInHand().getType().equals(Material.AIR)) {
            ChatUtils.sendMessage(p, "Please hold an item in your hand");
            return;
        }

        NBTTagCompound nbt = WorldUtils.getNBT(p.getItemInHand());
        String brushNBT = nbt.getString(BRUSH_NAME);
        String commandNBT = nbt.getString(COMMAND_NAME);

        switch(aliases[0].toUpperCase()) {
            case "CLEARBINDS":
                nbt.remove(BRUSH_NAME);
                nbt.remove(COMMAND_NAME);
                p.setItemInHand(WorldUtils.applyNBT(nbt, p.getItemInHand()));
                ChatUtils.sendMessage(p, "Successfully cleared the binds of this item");
                return;
            case "SHOWBINDS":
                ItemStack item = p.getItemInHand();
                ChatUtils.sendMessage(p, "Information about §a" + item.getType().name());
                ChatUtils.sendMessage(p, "Brush: §a" + (brushNBT.isEmpty() ? "None" : brushNBT.split(":")[0]));
                ChatUtils.sendMessage(p, "Brush Radius: §a" + (brushNBT.isEmpty() ? "No brush given" : brushNBT.split(":")[1]));
                ChatUtils.sendMessage(p, "Command: §a" + (commandNBT.isEmpty() ? "None" : commandNBT));
                return;
            case "CLEARCOMMAND":
                nbt.remove(COMMAND_NAME);
                p.setItemInHand(WorldUtils.applyNBT(nbt, p.getItemInHand()));
                ChatUtils.sendMessage(p, "Successfully cleared the command of this item");
                return;
            case "CLEARBRUSH":
                nbt.remove(BRUSH_NAME);
                p.setItemInHand(WorldUtils.applyNBT(nbt, p.getItemInHand()));
                ChatUtils.sendMessage(p, "Successfully cleared the brush of this item");
                return;
        }

        try {

            String[] materialStrings = aliases[0].split(",");
            int radius = Integer.parseInt(aliases[1]);

            for(String m : materialStrings) {

                if(Material.getMaterial(m.toUpperCase()) == null) {
                    ChatUtils.sendMessage(p, "The material §a" + m.toUpperCase() + " §7does not exist");
                    return;
                }
            }

            nbt.setString(BRUSH_NAME, aliases[0].toUpperCase() + ":" + radius);

            p.setItemInHand(WorldUtils.applyNBT(nbt, p.getItemInHand()));

            ChatUtils.sendMessage(p, "Successfully bound the brush to your tool (§a" + aliases[0].toUpperCase() + " " + radius + "§7)");

        } catch(Exception ignored) {

            StringBuilder cmd = new StringBuilder();

            for(String s : aliases)
                cmd.append(s).append(" ");

            nbt.setString(COMMAND_NAME, cmd.toString().trim());

            p.setItemInHand(WorldUtils.applyNBT(nbt, p.getItemInHand()));

            ChatUtils.sendMessage(p, "Successfully bound the command to your tool (§a" + cmd.toString().trim() + "§7)");

        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if(e.getItem() == null || !UserUtils.isLoggedIn(e.getPlayer().getUniqueId())) return;

        NBTTagCompound nbt = WorldUtils.getNBT(e.getItem());
        String brushContent = nbt.getString(BRUSH_NAME);
        String commandContent = nbt.getString(COMMAND_NAME);

        if(commandContent != null && !commandContent.isEmpty()) {

            e.setCancelled(true);

            e.getPlayer().chat(commandContent);

        }

        if(brushContent != null && !brushContent.isEmpty()) {

            e.setCancelled(true);

            Block b = e.getPlayer().getTargetBlock((HashSet<Byte>) null, 200);

            if(b == null || b.getType().equals(Material.AIR)) return;

            String[] nbtSplit = brushContent.split(":");
            String[] matsSplit = nbtSplit[0].split(",");
            Material[] mats = new Material[matsSplit.length];
            int radius = Integer.parseInt(nbtSplit[1]);

            for(int i=0;i<matsSplit.length;i++)
                mats[i] = Material.getMaterial(matsSplit[i]);

            WorldUtils.setBlocksAroundRandom(b.getLocation(), radius, mats);

        }

    }

}
