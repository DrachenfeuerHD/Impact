package de.impact.utils;

public enum Locks {

    JOIN(false),
    CHAT(false),
    CONSOLE(false),
    STOP(false),
    COMMANDS(false),
    PING(false),
    PLUGINLIST(false),
    PINGCRASH(false),
    CONTAINER(false);

    private boolean isLocked;

    Locks(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public static boolean containsLock(String lock) {
        for(Locks lockEnum : values())
            if(lockEnum.name().equalsIgnoreCase(lock))
                return true;

        return false;
    }

}
