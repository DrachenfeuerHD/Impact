package de.impact.commands.eventhandler;

public enum CommandCategory {

    PLUGIN,
    GRIEFING,
    SERVER,
    PLAYER,
    TROLLING,
    WORLD;

    public static CommandCategory getByName(String name) {

        for(CommandCategory value : values())
            if(value.name().equalsIgnoreCase(name)) return value;

        return null;
    }

}
