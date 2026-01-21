package com.azuredoom.levelingcore.events;

import java.util.UUID;

public interface AgilityListener {

    void onAgilityGain(UUID playerId, int agility);
}
