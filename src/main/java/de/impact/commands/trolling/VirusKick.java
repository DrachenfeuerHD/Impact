package de.impact.commands.trolling;

import com.google.common.base.Strings;
import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.main.Main;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VirusKick extends Command {

    public VirusKick() {
        super("VirusKick", "VirusKick <Player>", "Kick someone with a un-closeable virus warning", CommandCategory.TROLLING, "Vk");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        Player target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        String symbol = Strings.repeat("\n", 1000);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> target.kickPlayer(symbol + "§cMinecraft warning\n\n§fDon't close this window or shut down your computer.\n§fIf you shutdown your computer, or close this window, your hard-drive will be §4damaged§f!\n\n§4Please start a anti-virus scan and check your hard-drive" + symbol));

        ChatUtils.sendMessage(p, "Successfully kicked §a" + target.getName() + " §7with a virus warning");

    }

}
