package com.azuredoom.levelingcore.listeners;

import java.util.UUID;

public interface IntelligenceListener {

    void onIntelligenceGain(UUID playerId, int intelligence);
}
