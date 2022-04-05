package de.impact.commands.griefing;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Random;

public class TNTRain extends Command {

    public TNTRain() {
        super("TNTRain", "TNTRain <Amount> <Radius>", "Summon a custom amount of tnt above you", CommandCategory.GRIEFING, "RainTNT");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 2) {
            sendUsage(p);
            return;
        }

        try {

            int amount = Integer.parseInt(aliases[0]);
            int radius = Integer.parseInt(aliases[1]);
            Random random = new Random();
            Location playerLoc = p.getLocation();

            if(radius < 1 || amount < 1)
                throw new IllegalArgumentException();

            for(int i=0;i<amount;i++)
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> p.getWorld().spawnEntity(new Location(p.getWorld(), playerLoc.getX() + random.nextInt(radius), playerLoc.getY() + 30, playerLoc.getZ() + random.nextInt(radius)), EntityType.PRIMED_TNT));

            ChatUtils.sendMessage(p, "§a" + amount + " §7TNT in a radius of§a " + radius + " §7has been spawned above you");

        } catch(Exception e) {
            sendUsage(p);
        }

    }

}
