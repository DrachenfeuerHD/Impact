package de.impact.main;

import de.impact.commands.eventhandler.CommandCategory;
import de.impact.commands.eventhandler.CommandEvents;
import de.impact.utils.LockUtils;
import de.impact.utils.SaveUtils;
import de.impact.utils.TimerUtils;
import de.impact.utils.VanishUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

public class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {

        plugin = this;

        // Bukkit Listeners
        for(var clazz : new Reflections("de.impact.listeners").getSubTypesOf(Listener.class)) {

            try {

                var listener = (Listener) clazz.getDeclaredConstructor().newInstance();

                getServer().getPluginManager().registerEvents(listener, this);

            } catch (Exception ignored) {}

        }

        // Commands with own listeners
        for(var cat : CommandCategory.values()) {
            new Reflections("de.impact.commands." + cat.name().toLowerCase()).getSubTypesOf(Listener.class).forEach(clazz -> {
                try {

                    var listener = (Listener) clazz.getDeclaredConstructor().newInstance();

                    getServer().getPluginManager().registerEvents(listener, this);

                } catch (Exception ignored) {}
            });
        }

        // Impact Command Listeners
        getServer().getPluginManager().registerEvents(new CommandEvents(), this);

        // Utils with listeners
        Bukkit.getPluginManager().registerEvents(new VanishUtils(), this);

        // Adding the available locks to the list
        LockUtils.setupLocks();
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

        SaveUtils.save();
        CommandEvents.removePipelines();

    }

    public static Main getPlugin() {
        return plugin;
    }
}
