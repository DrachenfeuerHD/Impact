package de.impact.listeners;

import de.impact.utils.LockUtils;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventoryPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class ContainerListener implements Listener {

    @EventHandler
    public void onContainerOpen(InventoryOpenEvent e) {

        if(e.getInventory().getHolder() instanceof CraftInventoryPlayer || !LockUtils.isLocked("container")) return;

        e.setCancelled(true);

    }

}
