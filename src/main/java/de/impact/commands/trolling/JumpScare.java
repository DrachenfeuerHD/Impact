package de.impact.commands.trolling;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class JumpScare extends Command {

    public JumpScare() {
        super("JumpScare", "JumpScare <Player>", "Scare someone", CommandCategory.TROLLING, "Js");
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

        ((CraftPlayer) target).getHandle().playerConnection.sendPacket(new PacketPlayOutGameStateChange(10, 0.0F));

        for(int i = 0; i < 10; ++i) {
            target.playSound(target.getLocation(), Sound.GHAST_SCREAM, 1F, 2F);
            target.playSound(target.getLocation(), Sound.ANVIL_LAND, 2.14F, 0.2F);
            target.playSound(target.getLocation(), Sound.ARROW_HIT, 2.14F, 0.2F);
            target.playSound(target.getLocation(), Sound.AMBIENCE_THUNDER, 1.0F, 0.2F);
        }

        ChatUtils.sendMessage(p, "Successfully scared §a" + target.getName());

    }
}
