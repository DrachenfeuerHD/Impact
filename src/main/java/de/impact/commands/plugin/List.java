package de.impact.commands.plugin;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import de.impact.utils.VanishUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class List extends Command {

    public List() {
        super("List", "List", "Shows who is currently on the server", CommandCategory.PLUGIN, "ListUsers");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        StringBuilder allPlayers = new StringBuilder();

        for(int i=0;i<Bukkit.getOnlinePlayers().size();i++) {

            Player player = (Player) Bukkit.getOnlinePlayers().toArray()[i];

            if(player == null) continue;

            if(i==Bukkit.getOnlinePlayers().size()-1) {
                allPlayers.append("§a").append(player.getName()).append(VanishUtils.isVanished(player.getUniqueId()) ? " §cVANISHED" : "");
            } else {
                allPlayers.append("§a").append(player.getName()).append(VanishUtils.isVanished(player.getUniqueId()) ? " §cVANISHED" : "").append("§7, ");
            }

        }

        ChatUtils.sendMessage(p, "Online Players [§a"+Bukkit.getOnlinePlayers().size()+"§7] "+ allPlayers);

    }

}
