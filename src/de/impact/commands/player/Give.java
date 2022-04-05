package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Give extends Command {

    public Give() {
        super("Give", "Give <Item> (Amount)", "Give yourself a specific amount of any item", CommandCategory.PLAYER, "I", "G");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        Material mat = Material.getMaterial(aliases[0].toUpperCase());

        if(mat == null) {
            ChatUtils.sendMessage(p, "The material §a" + aliases[0].toUpperCase() + " §7does not exist");
            return;
        }

        try {

            int amount = Integer.parseInt(aliases[1]);

            p.getInventory().addItem(new ItemStack(mat, amount));

            ChatUtils.sendMessage(p, "Successfully gave you §a" + amount + " §7of §a" + mat.name());

        } catch(Exception ignored) {

            p.getInventory().addItem(new ItemStack(mat, 1));

            ChatUtils.sendMessage(p, "Successfully gave you §a1 §7of §a" + mat.name());

        }

    }

}
