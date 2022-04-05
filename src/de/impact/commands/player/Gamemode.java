package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Gamemode extends Command {

    public Gamemode() {
        super("Gamemode", "Gamemode <0-3> (Player)", "Change someones gamemode", CommandCategory.PLAYER, "Gm");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        Player target = p;

        if(aliases.length >= 2) {
            if(Bukkit.getPlayer(aliases[1]) != null) {
                target = Bukkit.getPlayer(aliases[1]);
            } else {
                ChatUtils.sendMessage(p, "This player is not online");
                return;
            }
        }

        switch (aliases[0]) {
            case "0":
                changeGameMode(target, p, GameMode.SURVIVAL);
            case "1":
                changeGameMode(target, p, GameMode.CREATIVE);
            case "2":
                changeGameMode(target, p, GameMode.ADVENTURE);
            case "3":
                changeGameMode(target, p, GameMode.SPECTATOR);
            default:
                ChatUtils.sendMessage(p, "Please use " + getUsage());
        }
    }

    private void changeGameMode(Player target, Player author, GameMode gm) {

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> target.setGameMode(gm));
        ChatUtils.sendMessage(author, "Changed the gamemode of §a" + (target == author ? "you" : target.getName()) + " §7to §a" + (gm.name().charAt(0) + gm.name().substring(1).toLowerCase()));

    }

}
