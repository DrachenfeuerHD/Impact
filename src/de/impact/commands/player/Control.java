package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.commands.eventhandler.TickTimer;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import de.impact.utils.VanishUtils;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Control extends Command implements Listener, TickTimer {

    private static final Map<UUID, UUID> controlled = new HashMap<>();

    public Control() {
        super("Control", "Control <Player>", "Control someone", CommandCategory.PLAYER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(controlled.containsKey(p.getUniqueId())) {
            ChatUtils.sendMessage(p, "You are no longer controlling a player");

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {

                Player target = Bukkit.getPlayer(controlled.get(p.getUniqueId()));

                if(target != null) p.showPlayer(target);

                p.getInventory().clear();
                p.getInventory().setArmorContents(new ItemStack[p.getInventory().getArmorContents().length]);

                controlled.remove(p.getUniqueId());
            });
            return;
        }

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        if(!VanishUtils.isVanished(p.getUniqueId()))
            VanishUtils.setVanished(p.getUniqueId(), true);

        controlled.put(p.getUniqueId(), target.getUniqueId());

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
            p.teleport(target);
            p.hidePlayer(target);
            p.getInventory().setArmorContents(target.getInventory().getArmorContents());
            p.getInventory().setContents(target.getInventory().getContents());
            p.setGameMode(target.getGameMode());
            p.getInventory().setHeldItemSlot(target.getInventory().getHeldItemSlot());
            p.setAllowFlight(target.getAllowFlight());
            p.setFlying(target.isFlying());
        });

        ChatUtils.sendMessage(p, "You are now controlling §a" + target.getName());

    }

    public void onTick() {

        for(Map.Entry<UUID, UUID> set : controlled.entrySet()) {

            Player p = Bukkit.getPlayer(set.getKey());
            Player t = Bukkit.getPlayer(set.getValue());

            if(p == null || t == null) continue;

            t.teleport(p);
            p.hidePlayer(t);
            t.getInventory().setArmorContents(p.getInventory().getArmorContents());

            for(int i = 0; i < p.getInventory().getContents().length; i++)
                t.getInventory().setItem(i, p.getInventory().getContents()[i]);

            t.setGameMode(p.getGameMode());
            t.getInventory().setHeldItemSlot(p.getInventory().getHeldItemSlot());
            t.setAllowFlight(p.getAllowFlight());
            t.setFlying(p.isFlying());

            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(new ChatComponentText("§7You are currently controlling §a" + t.getName()), (byte) 2));

        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(controlled.containsValue(e.getPlayer().getUniqueId()))
            e.setCancelled(true);

    }

}
