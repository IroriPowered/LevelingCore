package com.azuredoom.levelingcore.events;

import java.util.UUID;

public interface StrengthListener {

    void onStrengthGain(UUID playerId, int strength);
}
