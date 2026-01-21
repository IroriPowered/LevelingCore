package com.azuredoom.levelingcore.listeners;

import java.util.UUID;

public interface StrengthListener {

    void onStrengthGain(UUID playerId, int strength);
}
