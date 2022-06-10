package de.impact.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserUtils {

    private UserUtils() {
        throw new IllegalStateException("Utility Class");
    }

    private static final ArrayList<UUID> loggedInUsers = new ArrayList<>();

    public static boolean isLoggedIn(UUID uuid) {
        return loggedInUsers.contains(uuid);
    }

    public static void login(UUID uuid) {

        Player p = Bukkit.getPlayer(uuid);

        loggedInUsers.add(uuid);

        for(UUID loggedIn : getLoggedInUsers()) {

            Player loggedInPlayer = Bukkit.getPlayer(loggedIn);

            if(loggedInPlayer == null || loggedInPlayer == p) continue;

            ChatUtils.sendMessage(loggedInPlayer, "The User " + p.getName() + " has logged in");

        }

        VanishUtils.showAll(p);
        ChatUtils.sendMessage(p, "Successfully logged in as User");
    }

    public static void loginSilent(UUID uniqueId) {
        loggedInUsers.add(uniqueId);
        VanishUtils.showAll(Bukkit.getPlayer(uniqueId));
    }

    public static void logout(UUID uuid) {

        Player p = Bukkit.getPlayer(uuid);


        for(UUID loggedIn : getLoggedInUsers()) {

            Player loggedInPlayer = Bukkit.getPlayer(loggedIn);

            if(loggedInPlayer == null || loggedInPlayer == p) continue;

            ChatUtils.sendMessage(loggedInPlayer, "The User " + p.getName() + " has logged out");

        }

        loggedInUsers.remove(uuid);
        VanishUtils.hideAll(Bukkit.getPlayer(uuid));

        ChatUtils.sendMessage(p, "Successfully logged out");

    }

    public static void logoutSilent(UUID uniqueId) {
        loggedInUsers.remove(uniqueId);
        VanishUtils.hideAll(Bukkit.getPlayer(uniqueId));
    }

    public static boolean isAllowedToLogin(UUID uuid) {
        // TODO: Here you can add if the user is allowed to login.
        // TODO: Example: return uuid.equals(UUID.fromString("myUUID"));
        // TODO: In this case only YOU could login.
        return true;
    }

    public static List<UUID> getLoggedInUsers() {
        return loggedInUsers;
    }
}