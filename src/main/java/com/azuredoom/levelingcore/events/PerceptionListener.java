package com.azuredoom.levelingcore.events;

import java.util.UUID;

public interface PerceptionListener {

    void onPerceptionGain(UUID playerId, int perception);
}
