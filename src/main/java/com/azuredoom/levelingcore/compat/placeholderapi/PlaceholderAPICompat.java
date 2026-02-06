package com.azuredoom.levelingcore.compat.placeholderapi;

public class PlaceholderAPICompat {

    private PlaceholderAPICompat() {}

    public static void register() {
        new LevelingCoreExpansion().register();
    }
}
