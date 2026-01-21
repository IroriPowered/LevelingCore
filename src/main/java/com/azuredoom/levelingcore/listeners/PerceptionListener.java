package com.azuredoom.levelingcore.listeners;

import java.util.UUID;

public interface PerceptionListener {

    void onPerceptionGain(UUID playerId, int perception);
}
