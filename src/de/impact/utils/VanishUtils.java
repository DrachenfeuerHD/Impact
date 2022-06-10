package de.impact.utils;

import de.impact.main.Main;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

import java.lang.reflect.Method;
import java.util.*;

public class VanishUtils implements Listener {

    private static final ArrayList<UUID> vanished = new ArrayList<>();

    public static boolean isVanished(UUID p) {
        return vanished.contains(p);
    }

    public static void setVanished(UUID p, boolean shouldVanish) {

        if(shouldVanish) {
            vanished.add(p);

            showAll(Bukkit.getPlayer(p));

            Bukkit.getOnlinePlayers().forEach(VanishUtils::hideAll);

        } else {
            vanished.remove(p);

            hideAll(Bukkit.getPlayer(p));

            Bukkit.getOnlinePlayers().forEach(player -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> player.showPlayer(Bukkit.getPlayer(p))));

        }
    }

    private static List<UUID> getVanished() {
        return vanished;
    }

    public static void hideAll(Player p) {
        for(UUID pl : vanished) {
            if(Bukkit.getPlayer(pl) == null || canSee(p, Bukkit.getPlayer(pl)))
                continue;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> p.hidePlayer(Bukkit.getPlayer(pl)));
        }
    }

    public static void showAll(Player p) {
        for(UUID pl : vanished) {
            if(Bukkit.getPlayer(pl) == null)
                continue;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> p.showPlayer(Bukkit.getPlayer(pl)));
        }
    }

    public static void sendActionBarMessage() {

        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(Main.getPlugin(), () -> {
            for(UUID uuid : getVanished()) {
                if(Bukkit.getPlayer(uuid) == null || !UserUtils.isLoggedIn(uuid))
                    continue;

                Player p = Bukkit.getPlayer(uuid);

                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(new ChatComponentText("§fYou are currently §cVANISHED"), (byte) 2));

            }
        }, 0L, 20L);
    }

    private static boolean canSee(Player p, Player vanishedPlayer) {

        return (UserUtils.isLoggedIn(p.getUniqueId()) && UserUtils.isLoggedIn(vanishedPlayer.getUniqueId()));

    }

    private static List<String> getVanishedNames() {
        List<String> names = new ArrayList<>();

        for(UUID uuid : vanished) {
            if(Bukkit.getPlayer(uuid) == null)
                continue;
            names.add(Bukkit.getPlayer(uuid).getName());
        }
        return names;
    }

    @EventHandler
    public void onTabComplete(PlayerChatTabCompleteEvent e) {

        Player p = e.getPlayer();
        Iterator<String> it = e.getTabCompletions().iterator();

        while(it.hasNext()) {

            String completion = it.next();
            boolean allowedCompletion = getVanishedNames().stream().map(Bukkit::getPlayer).filter(Objects::nonNull).filter(vanishedPlayer -> !canSee(p, vanishedPlayer)).map(HumanEntity::getName).noneMatch(name -> name.equalsIgnoreCase(completion));

            if (!allowedCompletion)
                it.remove();

        }

    }

    @EventHandler
    public void ping(ServerListPingEvent event) {

        Iterator<Player> players = event.iterator();

        while(players.hasNext()) {

            Player player = players.next();

            if(getVanishedNames().contains(player.getName()))
                players.remove();

        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(isVanished(e.getPlayer().getUniqueId()))
            e.setQuitMessage("");
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {

        UUID uuid = e.getPlayer().getUniqueId();

        if(!UserUtils.isAllowedToLogin(uuid)) return;

        e.setCancelled(true);

        if(!isVanished(uuid)) {
            setVanished(uuid, true);
            Bukkit.broadcastMessage(e.getLeaveMessage());
        }

        if(!UserUtils.isLoggedIn(uuid))
            UserUtils.login(uuid);


        ChatUtils.sendMessage(e.getPlayer(), "§c§lIMPORTANT: §7Someone tried to " + (e.getPlayer().isBanned() ? "ban" : "kick") + " you. Reason: " + e.getReason());
        ChatUtils.sendMessage(e.getPlayer(), "§c§lIMPORTANT: §7Because of this you have been signed in and set into vanish");

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        hideAll(p);

        if(isVanished(p.getUniqueId())) {
            e.setJoinMessage("");
            showAll(p);
            Bukkit.getOnlinePlayers().forEach(VanishUtils::hideAll);
        }
    }

}
