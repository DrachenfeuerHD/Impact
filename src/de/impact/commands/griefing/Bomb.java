package de.impact.commands.griefing;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import de.impact.utils.UserUtils;
import de.impact.utils.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class Bomb extends Command implements Listener {

    private static final String BOMB_NAME = "§c§kX§a Bomb §c§kX";

    public Bomb() {
        super("Bomb", "Bomb", "Gives yourself a bomb", CommandCategory.GRIEFING);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        ItemStack stack = new ItemStack(Material.TNT);
        ItemMeta meta = stack.getItemMeta();

        meta.setDisplayName(BOMB_NAME);
        stack.setItemMeta(meta);

        p.getInventory().addItem(stack);

        ChatUtils.sendMessage(p, "Successfully gave you a bomb");

    }

    @EventHandler
    public void onEntityExplode(EntityDamageEvent e) {
        if(e.getEntity() == null || e.getEntity().getCustomName() == null || !e.getEntity().getCustomName().equals("IMPACT_BOMB")) return;

        e.setDamage(0);

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if(e.getItem() == null || !e.getItem().getType().equals(Material.TNT) || e.getItem().getItemMeta().getDisplayName() == null || !e.getItem().getItemMeta().getDisplayName().equals(BOMB_NAME) || !UserUtils.isLoggedIn(e.getPlayer().getUniqueId())) return;

        e.setCancelled(true);

        ItemStack stack = new ItemStack(Material.TNT);
        ItemMeta meta = stack.getItemMeta();

        meta.setDisplayName(new Random().nextLong() + "");
        stack.setItemMeta(meta);

        Item bomb = e.getPlayer().getWorld().dropItem(e.getPlayer().getEyeLocation(), stack);

        bomb.setCustomName("IMPACT_BOMB");
        bomb.setFireTicks(Integer.MAX_VALUE);
        bomb.setVelocity(e.getPlayer().getEyeLocation().getDirection());

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {

            if(!e.getPlayer().getWorld().getEntities().contains(bomb))
                return;

            for(Block b : WorldUtils.getBlocksAround(bomb.getLocation(), 1)) {

                if(b.getType().equals(Material.AIR))
                    continue;

                bomb.remove();
                e.getPlayer().getWorld().createExplosion(bomb.getLocation(), 10, true);
                break;
            }

        }, 0L, 1L);
    }

}
