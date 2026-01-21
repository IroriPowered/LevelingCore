package com.azuredoom.levelingcore.events;

import java.util.UUID;

public interface AbilityPointsListener {

    void onAbilityPointGain(UUID playerId, int abilityPoints);

    void onAbilityPointLoss(UUID playerId, int abilityPoints);

    void onAbilityPointUsed(UUID playerId, int abilityPoints);
}
