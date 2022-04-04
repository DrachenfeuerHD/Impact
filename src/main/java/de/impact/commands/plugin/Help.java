package de.impact.commands.plugin;

import de.impact.commands.eventhandler.Command;
import de.impact.commands.eventhandler.CommandCategory;
import de.impact.utils.ChatUtils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        CommandCategory cat = CommandCategory.getByName(aliases[0]);

        if(cat != null) {
            sendCommandsOfCategory(p, cat);
            return;
        }

        for(Command c : Command.getAllCommands()) {

            String cmd = c.isCommandValid(aliases[0]);

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

        List baseC = Arrays.stream(TextComponent.fromLegacyText(ChatUtils.getPluginPrefix())).collect(Collectors.toList());

        for(CommandCategory cat : CommandCategory.values()) {

            String catName = cat.name().charAt(0) + cat.name().substring(1).toLowerCase();
            ComponentBuilder message = new ComponentBuilder("§a" + catName + " ");
            BaseComponent[] msg = message
                    .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "help " + cat.name()))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Click for all commands of §a" + catName)))
                    .create();

            baseC.addAll(Arrays.stream(msg).collect(Collectors.toList()));
        }

        BaseComponent[] componentArray = new BaseComponent[baseC.size()];

        for(int i=0;i<baseC.size();i++)
            componentArray[i] = (BaseComponent) baseC.get(i);

        p.spigot().sendMessage(componentArray);
    }

    private void sendCommandsOfCategory(Player p, CommandCategory cat) {

        List cmdList = Arrays.stream(TextComponent.fromLegacyText(ChatUtils.getPluginPrefix())).collect(Collectors.toList());

        Command.getAllCommandsByCategory(cat).forEach(cmd ->{
            ComponentBuilder message = new ComponentBuilder("§7" + cmd.getCommand() + " ");
            BaseComponent[] msg = message
                    .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "help " + cmd.getCommand()))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Click for more information about §a" + cmd.getCommand())))
                    .create();
            cmdList.addAll(Arrays.stream(msg).collect(Collectors.toList()));
        });

        ChatUtils.sendDefault(p, "§a");
        ChatUtils.sendMessage(p, "§m-----§a " + (cat.name().charAt(0) + cat.name().substring(1).toLowerCase()) + " §7§m-----");
        ChatUtils.sendMessage(p, "§a");

        BaseComponent[] componentArray = new BaseComponent[cmdList.size()];

        for(int i=0;i<cmdList.size();i++)
            componentArray[i] = (BaseComponent) cmdList.get(i);

        p.spigot().sendMessage(componentArray);

    }

    private String formatAliases(String[] aliases) {

        StringBuilder s = new StringBuilder();

        for(int i=0;i<aliases.length;i++)
            s.append(aliases[i]).append(i == aliases.length -1 ? "" : ", ");

        return s.toString();

    }
}
