package com.azuredoom.levelingcore.listeners;

import java.util.UUID;

public interface AgilityListener {

    void onAgilityGain(UUID playerId, int agility);
}
