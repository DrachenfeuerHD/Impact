package de.impact.main;

import de.impact.commands.eventhandler.CommandCategory;
import de.impact.commands.eventhandler.CommandEvents;
import de.impact.utils.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.io.File;

public class Main extends JavaPlugin {

    private static Main plugin;
    private static File file;

    @Override
    public void onEnable() {

        file = getFile();
        plugin = this;

        // Bukkit Listeners
        for(Class<? extends Listener> clazz : new Reflections("de.impact.listeners").getSubTypesOf(Listener.class)) {

            try {

                Listener listener = clazz.getDeclaredConstructor().newInstance();

                getServer().getPluginManager().registerEvents(listener, this);

            } catch (Exception ignored) {}

        }

        // Commands with own listeners
        for(CommandCategory cat : CommandCategory.values()) {
            new Reflections("de.impact.commands." + cat.name().toLowerCase()).getSubTypesOf(Listener.class).forEach(clazz -> {
                try {

                    Listener listener = clazz.getDeclaredConstructor().newInstance();

                    getServer().getPluginManager().registerEvents(listener, this);

                } catch (Exception ignored) {}
            });
        }

        // Impact Command Listeners
        getServer().getPluginManager().registerEvents(new CommandEvents(), this);

        // Utils with listeners
        getServer().getPluginManager().registerEvents(new VanishUtils(), this);

        // Registering the pipelines used for commands
        CommandEvents.registerPipelines();
        // Loading logged-in users / locks
        SaveUtils.load();
        // Sending the action bar messages to users
        VanishUtils.sendActionBarMessage();
        // Starting the timer
        TimerUtils.startTickTimer();

    }

    @Override
    public void onDisable() {

        CommandEvents.removePipelines();
        SaveUtils.save();

    }

    public static Main getPlugin() {
        return plugin;
    }

    public static File getPluginFile() {
        return file;
    }
}
