package de.impact.utils.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LockUtils {

    private LockUtils() {
        throw new IllegalStateException("Utility Class");
    }

    private static HashMap<Locks, Boolean> locked = new HashMap<>();

    public static boolean isLocked(Locks id) {
        return locked.get(id);
    }

    public static void setLocked(Locks id, boolean lock) {
        locked.put(id, lock);
    }

    public static boolean containsLock(Locks id) {
        return locked.containsKey(id);
    }

    public static void addLocks() {
        for(Locks s : Locks.values())
            locked.put(s, false);
    }

    public static HashMap<Locks, Boolean> getLocked() {
        return locked;
    }

    public static void setupLocks() {
        addLocks();
    }
}
