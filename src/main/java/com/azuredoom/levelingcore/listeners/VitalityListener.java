package com.azuredoom.levelingcore.listeners;

import java.util.UUID;

public interface VitalityListener {

    void onVitalityGain(UUID playerId, int vitality);
}
