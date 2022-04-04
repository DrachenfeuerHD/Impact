package de.impact.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LockUtils {

    private LockUtils() {
        throw new IllegalStateException("Utility Class");
    }

    private static HashMap<String, Boolean> locked = new HashMap<>();

    public static boolean isLocked(String id) {
        return locked.get(id);
    }

    public static void setLocked(String id, boolean lock) {
        locked.put(id, lock);
    }

    public static boolean containsLock(String id) {
        return locked.containsKey(id);
    }

    public static void addLocks(String[] id) {
        for(var s : id)
            locked.put(s, false);
    }

    public static Set<String> getLocks() {
        return locked.keySet();
    }

    public static Set<Map.Entry<String, Boolean>> getLockEntrySet() {
        return locked.entrySet();
    }

    public static void setupLocks() {
        addLocks(new String[] {"join", "chat", "console", "stop", "commands", "ping", "pluginlist", "pingcrash", "container"});
    }
}
