package de.impact.commands.eventhandler;

import de.impact.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    String cmd;
    String usage;
    String description;
    CommandCategory category;
    String[] aliases;

    protected Command(String cmd, String usage, String description, CommandCategory category, String... aliases) {

        this.cmd = cmd;
        this.usage = usage;
        this.description = description;
        this.aliases = aliases;
        this.category = category;

    }

    protected void sendUsage(Player p) {
        ChatUtils.sendMessage(p, "Please use " + getUsage());
    }

    protected void onChat(String[] aliases, Player p) { /* Chat Event called by CommandEvents */ }

    public String isCommandValid(String command) {

        for (String alias : aliases)
            if (command.split(" ")[0].equalsIgnoreCase(alias)) return alias;

        if(command.split(" ")[0].equalsIgnoreCase(cmd)) return cmd;

        return null;
    }

    public static List<Command> getAllCommands() {

        ArrayList<Command> cmds = new ArrayList<>();

        for(CommandCategory cat : CommandCategory.values()) {
            new Reflections("de.impact.commands." + cat.name().toLowerCase()).getSubTypesOf(Command.class).forEach(set -> {
                try {
                    cmds.add(set.newInstance());
                } catch (Exception ignored) {}
            });
        }

        return cmds;
    }

    public static List<Command> getAllCommandsByCategory(CommandCategory cat) {

        ArrayList<Command> list = new ArrayList<>();

        for(Command cmd : getAllCommands()) {
            if(cmd.getCategory().equals(cat))
                list.add(cmd);
        }

        return list;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public String getCommand() {
        return this.cmd;
    }

    public String getUsage() {
        return this.usage;
    }

    public String getDescription() {
        return this.description;
    }

    public CommandCategory getCategory() {
        return this.category;
    }

}
