package de.impact.utils;

import de.impact.commands.eventhandler.TickTimer;
import de.impact.main.Main;
import org.bukkit.Bukkit;
import org.reflections.Reflections;

import java.util.Set;

public class TimerUtils {

    private TimerUtils() {
        throw new IllegalStateException("Utility Class");
    }

    public static void startTickTimer() {

        Reflections reflections = new Reflections("de.impact.commands");
        Set<Class<? extends TickTimer>> classes = reflections.getSubTypesOf(TickTimer.class);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {
            classes.forEach(clazz -> {
                try {
                    clazz.newInstance().onTick();
                } catch (Exception ignored) {}
            });
        }, 0L, 1L);

    }

}
