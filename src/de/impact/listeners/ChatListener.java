package de.impact.listeners;

import de.impact.utils.Locks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        if(Locks.valueOf("CHAT").isLocked())
            e.setCancelled(true);

    }
}
