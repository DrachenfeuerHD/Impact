package de.impact.listeners;

import de.impact.utils.lock.LockUtils;
import de.impact.utils.lock.Locks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        if(LockUtils.isLocked(Locks.CHAT))
            e.setCancelled(true);

    }
}
