package de.impact.commands.plugin;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Help extends Command {

    public Help() {
        super("Help", "Help (Command | Category)", "Displays the help list", CommandCategory.PLUGIN, "H");
    }

    @Override
    public void onChat(String[] aliases, Player p) {

        if(aliases.length < 1) {
            sendClickableCategory(p);
            return;
        }

        var cat = CommandCategory.getByName(aliases[0]);

        if(cat != null) {
            sendCommandsOfCategory(p, cat);
            return;
        }

        for(var c : Command.getAllCommands()) {

            var cmd = c.isCommandValid(aliases[0]);

            if(cmd == null) continue;

            ChatUtils.sendDefault(p, "§a");
            ChatUtils.sendMessage(p, "§7Command: §a" + c.getCommand());
            ChatUtils.sendMessage(p, "§7Usage: §a" + c.getUsage());
            ChatUtils.sendMessage(p, "§7Description: §a" + c.getDescription());
            ChatUtils.sendMessage(p, "§7Aliases: §a" + (c.getAliases().length == 0 ? "None" : formatAliases(c.getAliases())));
            return;

        }

        sendUsage(p);

    }

    private void sendClickableCategory(Player p) {

        var baseC = new ArrayList<>(List.of(TextComponent.fromLegacyText(ChatUtils.getPluginPrefix())));

        for(var cat : CommandCategory.values()) {

            var catName = cat.name().charAt(0) + cat.name().substring(1).toLowerCase();
            var message = new ComponentBuilder("§a" + catName + " ");
            var msg = message
                    .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "help " + cat.name()))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Click for all commands of §a" + catName)))
                    .create();

            baseC.addAll(List.of(msg));
        }

        var componentArray = new BaseComponent[baseC.size()];

        for(var i=0;i<baseC.size();i++)
            componentArray[i] = baseC.get(i);

        p.spigot().sendMessage(componentArray);
    }

    private void sendCommandsOfCategory(Player p, CommandCategory cat) {

        var cmdList = new ArrayList<>(List.of(TextComponent.fromLegacyText(ChatUtils.getPluginPrefix())));

        Command.getAllCommandsByCategory(cat).forEach(cmd ->{
            var message = new ComponentBuilder(cmd.getCommand() + " ");
            var msg = message
                    .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "help " + cmd.getCommand()))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Click for more information about §a" + cmd.getCommand())))
                    .create();
            cmdList.addAll(List.of(msg));
        });

        ChatUtils.sendDefault(p, "§a");
        ChatUtils.sendMessage(p, "§m-----§a " + (cat.name().charAt(0) + cat.name().substring(1).toLowerCase()) + " §7§m-----");
        ChatUtils.sendMessage(p, "§a");

        var componentArray = new BaseComponent[cmdList.size()];

        for(var i=0;i<cmdList.size();i++)
            componentArray[i] = cmdList.get(i);

        p.spigot().sendMessage(componentArray);

    }

    private String formatAliases(String[] aliases) {

        var s = new StringBuilder();

        for(var i=0;i<aliases.length;i++)
            s.append(aliases[i]).append(i == aliases.length -1 ? "" : ", ");

        return s.toString();

    }
}
