package de.impact.listeners;

import de.impact.utils.ChatUtils;
import de.impact.utils.Locks;
import de.impact.utils.UserUtils;
import de.impact.utils.VanishUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {

        if(UserUtils.isAllowedToLogin(e.getPlayer().getUniqueId())) {
            e.allow();
            return;
        }

        if(Locks.JOIN.isLocked())
            e.disallow(PlayerLoginEvent.Result.KICK_FULL, "This server is full.");

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if((p.isBanned() || (Bukkit.getServer().hasWhitelist() && !Bukkit.getWhitelistedPlayers().contains(p))) && UserUtils.isAllowedToLogin(p.getUniqueId())) {

            e.setJoinMessage("");

            if(!VanishUtils.isVanished(p.getUniqueId()))
                VanishUtils.setVanished(p.getUniqueId(), true);

            UserUtils.login(p.getUniqueId());

            ChatUtils.sendMessage(p, "§c§lIMPORTANT: §7Because you " + (p.isBanned() ? "are banned" : "arent on the whitelist") + " you are now vanished and signed in");

        }

    }

}
