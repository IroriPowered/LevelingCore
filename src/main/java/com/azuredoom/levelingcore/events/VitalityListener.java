package com.azuredoom.levelingcore.events;

import java.util.UUID;

public interface VitalityListener {

    void onVitalityGain(UUID playerId, int vitality);
}
