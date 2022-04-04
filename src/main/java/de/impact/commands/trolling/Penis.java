package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Penis extends Command {

    public Penis() {
        super("Penis", "Penis <Player>", "Spawn a giant fake-block penis above someone", CommandCategory.TROLLING);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player could not be found");
            return;
        }

        // Bottom
        for(double x = target.getLocation().getX() - 4; x <= target.getLocation().getX() + 4; x++)
            for(double y = target.getLocation().getY() + 4; y <= target.getLocation().getY() + 6; y++)
                for(double z = target.getLocation().getZ(); z <= target.getLocation().getZ() + 2; z++)
                    target.sendBlockChange(new Location(target.getWorld(), x, y, z), Material.WOOL, (byte) 0);

        // Stem
        for(double x = target.getLocation().getX() - 1; x <= target.getLocation().getX() + 1; x++)
            for(double y = target.getLocation().getY() + 6; y <= target.getLocation().getY() + 12; y++)
                for(double z = target.getLocation().getZ(); z <= target.getLocation().getZ() + 2; z++)
                    target.sendBlockChange(new Location(target.getWorld(),x,y,z), Material.WOOL, (byte) 0);

        // Top
        for(double x = target.getLocation().getX() - 1; x <= target.getLocation().getX() + 1; x++)
            for(double y = target.getLocation().getY() + 12; y <= target.getLocation().getY() + 14; y++)
                for(double z = target.getLocation().getZ(); z <= target.getLocation().getZ() + 2; z++)
                    target.sendBlockChange(new Location(target.getWorld(),x,y,z), Material.WOOL, (byte)6);

        ChatUtils.sendMessage(p, "Successfully spawned a fake penis above §a" + target.getName());

    }

}
