package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GlassBox extends Command {

    public GlassBox() {
        super("GlassBox", "GlassBox <Player>", "Trap someone in a fake-glass box", CommandCategory.TROLLING);
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

        for (var x = target.getLocation().getX() - 5.0D; x <= target.getLocation().getX() + 5.0D; ++x)
            for (var y = target.getLocation().getY() - 5.0D; y <= target.getLocation().getY() + 5.0D; ++y)
                for (var z = target.getLocation().getZ() - 5.0D; z <= target.getLocation().getZ() + 5.0D; ++z)
                    target.sendBlockChange(new Location(target.getWorld(), x, y, z), Material.GLASS, (byte) 0);

        for (var x = target.getLocation().getX() - 4.0D; x <= target.getLocation().getX() + 4.0D; ++x)
            for (var y = target.getLocation().getY() - 4.0D; y <= target.getLocation().getY() + 4.0D; ++y)
                for (var z = target.getLocation().getZ() - 4.0D; z <= target.getLocation().getZ() + 4.0D; ++z)
                    target.sendBlockChange(new Location(target.getWorld(), x, y, z), Material.AIR, (byte) 0);

        ChatUtils.sendMessage(p, "Successfully fake-trapped §a" + target.getName());

    }

}
