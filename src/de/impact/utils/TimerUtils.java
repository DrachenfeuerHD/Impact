package de.impact.utils;

import de.impact.commands.griefing.Nuker;
import de.impact.commands.griefing.TNTFly;
import de.impact.commands.trolling.BlackScreen;
import de.impact.commands.trolling.BlockScreen;
import de.impact.commands.trolling.LookNuke;
import de.impact.commands.trolling.Spin;
import de.impact.main.Main;
import org.bukkit.Bukkit;

public class TimerUtils {

    private TimerUtils() {
        throw new IllegalStateException("Utility Class");
    }

    public static void startTickTimer() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {
            BlackScreen.onTick();
            BlockScreen.onTick();
            LookNuke.onTick();
            TNTFly.onTick();
            Nuker.onTick();
            Spin.onTick();
        }, 0L, 1L);

    }

}
