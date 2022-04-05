package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.commands.eventhandler.TickTimer;
import de.impact.utils.ChatUtils;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class BlackScreen extends Command implements TickTimer {

    private static final ArrayList<UUID> players = new ArrayList<>();

    public BlackScreen() {
        super("BlackScreen", "BlackScreen <Player>", "Send someone a blackscreen", CommandCategory.TROLLING, "Bs");
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

        if(players.contains(target.getUniqueId())) {
            players.remove(target.getUniqueId());
        } else {
            players.add(target.getUniqueId());
        }

        ChatUtils.sendMessage(p, "§a" + target.getName() + "§7 " + (players.contains(target.getUniqueId()) ? "now has a " : "no longer has a ") + "blackscreen");

    }

    public void onTick() {
        for(UUID p : players) {
            if(Bukkit.getPlayer(p) == null) return;

            ((CraftPlayer) Bukkit.getPlayer(p)).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(4, 1));
        }
    }

}
