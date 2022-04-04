package de.impact.utils;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;

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

        for (var uuid : UserUtils.getLoggedInUsers()) {

            var p = Bukkit.getPlayer(uuid);

            if (p == null) continue;

            ChatUtils.sendMessage(p, "§c§lWARNING: §cThe plugin has been disabled!");

        }

        var write = new StringBuilder();

        for (var uuid : UserUtils.getLoggedInUsers())
            write.append(Base64.getEncoder().encodeToString((uuid.toString() + ":" + VanishUtils.isVanished(uuid)).getBytes())).append(" ");

        if(write.toString().isEmpty())
            return;

        var writer = new BufferedWriter(new FileWriter(PATH, false));

        writer.write(write.toString());

        writer.flush();
        writer.close();
    }

    private static void saveToggles() throws IOException {

        var writer = new BufferedWriter(new FileWriter(PATH_SECOND, false));
        var data = new StringBuilder();

        LockUtils.getLockEntrySet().forEach(s -> data.append(s.getKey()).append("-").append(s.getValue().toString()).append(":"));

        writer.write(Base64.getEncoder().encodeToString(data.toString().getBytes()));

        writer.flush();
        writer.close();
    }

    private static void loginSavedUsers() throws IOException {

        if(!new File(PATH).exists())
            return;

        var inputStream = new FileInputStream(PATH);
        var inputStreamSplit = IOUtils.toString(inputStream).split(" ");

        for(var s : inputStreamSplit) {

            if(s.isEmpty())
                continue;

            var uuidString = new String(Base64.getDecoder().decode(s.getBytes()));
            var shouldVanish = uuidString.split(":")[1];
            var uuid = UUID.fromString(uuidString.split(":")[0]);

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

        var inputStream = new FileInputStream(PATH_SECOND);
        var is = IOUtils.toString(inputStream);
        var inputStreamSplit = new String(Base64.getDecoder().decode(is.getBytes())).split(":");

        for(var s : inputStreamSplit) {

            if(s.split("-")[0] == null || s.split("-")[1] == null) continue;

            LockUtils.setLocked(s.split("-")[0], Boolean.parseBoolean(s.split("-")[1]));
        }

        inputStream.close();
        new File(PATH_SECOND).delete();

    }

}
