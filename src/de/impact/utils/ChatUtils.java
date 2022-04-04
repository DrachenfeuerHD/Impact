package de.impact.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ChatUtils {

    private ChatUtils() {
        throw new IllegalArgumentException("Utility Class");
    }

    private static final String DEFAULT_COLOR = "§7";
    private static final String PREFIX = "§dImpact-Free §5❯ " + DEFAULT_COLOR;
    private static final String SERVER_CHAT_PREFIX = "§dServerChat §5❯ ";

    public static void sendMessage(Player p, String message) {
        p.sendMessage(PREFIX + message);
    }

    public static void broadcast(String message) {
        Bukkit.broadcastMessage(PREFIX + message);
    }

    public static void sendDefault(Player p, String message) {
        p.sendMessage(message);
    }

    public static void broadcastDefault(String message) {
        Bukkit.broadcastMessage(message);
    }

    public static String getServerChatPrefix() {
        return SERVER_CHAT_PREFIX;
    }

    public static String getPluginPrefix() {
        return PREFIX;
    }

}
