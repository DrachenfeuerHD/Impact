package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TNTTroll extends Command {

    public TNTTroll() {
        super("TNTTroll", "TNTTroll <Player>", "Replace every near block of a player with fake-tnt", CommandCategory.TROLLING, "TTroll");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        var target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player could not be found");
            return;
        }

        for (var x = target.getLocation().getX() - 30; x <= target.getLocation().getX() + 30; ++x)
            for (var y = target.getLocation().getY() - 30; y <= target.getLocation().getY() + 30; ++y)
                for (var z = target.getLocation().getZ() - 30; z <= target.getLocation().getZ() + 30; ++z) {

                    var l = new Location(target.getWorld(), x, y, z);

                    if (l.getBlock().getType().equals(Material.AIR)) continue;

                    target.sendBlockChange(l, Material.TNT, (byte) 0);
                }

        ChatUtils.sendMessage(p, "This player now thinks every block is tnt");

    }

}
