package com.azuredoom.levelingcore.level.mobs;

public final class MobLevelData {

    public int level;

    public boolean locked;

    public int lastAppliedLevel;

    public volatile long lastRecalcMs;

    public MobLevelData(int level) {
        this.level = level;
        this.locked = false;
        this.lastAppliedLevel = level;
        this.lastRecalcMs = 0;
    }
}
