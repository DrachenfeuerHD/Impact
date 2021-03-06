package de.impact.listeners;

import de.impact.utils.Locks;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventoryPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class ContainerListener implements Listener {

    @EventHandler
    public void onContainerOpen(InventoryOpenEvent e) {

        if(e.getInventory().getHolder() instanceof CraftInventoryPlayer || !Locks.CONTAINER.isLocked()) return;

        e.setCancelled(true);

    }

}
