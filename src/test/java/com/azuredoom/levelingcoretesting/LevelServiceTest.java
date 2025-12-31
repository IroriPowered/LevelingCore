package com.azuredoom.levelingcoretesting;

import com.azuredoom.levelingcore.Main;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LevelServiceTest {

    public static final System.Logger LOGGER = System.getLogger(Main.class.getName());

    @Test
    void addXp_increasesXpAndLevel() {
        var service = AppBootstrap.createLevelService(Main.configPath);
        var id = UUID.fromString("d3804858-4bb8-4026-ae21-386255ed467d");
        var amount = 500;
        var beforeXp = service.getXp(id);
        var beforeLevel = service.getLevel(id);

        service.addXp(id, amount);
        LOGGER.log(System.Logger.Level.INFO, String.format("XP: %d", service.getXp(id)));
        LOGGER.log(System.Logger.Level.INFO, String.format("Level: %d", service.getLevel(id)));

        assertEquals(beforeXp + amount, service.getXp(id));
        assertTrue(service.getLevel(id) >= beforeLevel);
    }
}
