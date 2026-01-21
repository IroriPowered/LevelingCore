package com.azuredoom.levelingcore.events;

import java.util.UUID;

public interface IntelligenceListener {

    void onIntelligenceGain(UUID playerId, int intelligence);
}
