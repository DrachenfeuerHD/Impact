package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Contrast extends Command {

    public Contrast() {
        super("Contrast", "Contrast <Player>", "Change the game contrast for someone", CommandCategory.TROLLING);
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

        ((CraftPlayer) target).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(7, 12.0F));

        ChatUtils.sendMessage(p, "Successfully changed the contrast for §a" + target.getName());

    }

}
