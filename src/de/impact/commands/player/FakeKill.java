package de.impact.commands.player;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.entity.Player;

public class FakeKill extends Command {

    public FakeKill() {
        super("FakeKill", "FakeKill <Player>", "Fake-Kill someone", CommandCategory.PLAYER);
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendUsage(p);
            return;
        }

        var target = Bukkit.getPlayer(aliases[0]);

        if(target == null) {
            ChatUtils.sendMessage(p, "This player is not online");
            return;
        }

        target.playEffect(EntityEffect.DEATH);
        ChatUtils.sendMessage(p, "Successfully fake-killed §a" + target.getName());

    }

}
