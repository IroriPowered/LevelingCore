package com.azuredoom.levelingcore;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.*;

import com.azuredoom.levelingcore.api.LevelingCoreApi;
import com.azuredoom.levelingcore.config.ConfigBootstrap;
import com.azuredoom.levelingcore.exceptions.LevelingCoreException;
import com.azuredoom.levelingcore.level.LevelServiceImpl;
import com.azuredoom.levelingcore.logging.LogConfig;

public class LevelingCore {

    public static final Logger LOGGER = LogConfig.setup(LevelingCore.class);

    public static final Path configPath = Paths.get("./data/plugins/levelingcore/");

    private static final ConfigBootstrap.Bootstrap bootstrap = ConfigBootstrap.bootstrap(configPath);

    private static LevelServiceImpl levelingService;

    public LevelingCore() {}

    // TODO: Call this from the server startup hook
    public static void init() {
        LOGGER.log(Level.INFO, "Leveling Core initialized");
        levelingService = bootstrap.service();
    }

    public static void shutdown() {
        LOGGER.log(Level.INFO, "Leveling Core shutting down");
        try {
            LevelingCore.bootstrap.closeable().close();
        } catch (Exception e) {
            throw new LevelingCoreException("Failed to close resources", e);
        }
    }

    static void main() {
        LevelingCore.init();
        // TODO: Remove once hooks into the player/mob kill events are found and integrable.
        var testId = UUID.fromString("d3804858-4bb8-4026-ae21-386255ed467d");
        if (LevelingCoreApi.getLevelServiceIfPresent().isPresent()) {
            var levelingService = LevelingCoreApi.getLevelServiceIfPresent().get();
            levelingService.addXp(testId, 500);
            // TODO: Move to chat based logging instead of System loggers
            LevelingCore.LOGGER.log(Level.INFO, String.format("XP: %d", levelingService.getXp(testId)));
            LevelingCore.LOGGER.log(
                Level.INFO,
                String.format("Level: %d", levelingService.getLevel(testId))
            );
        }
        // TODO: Move to server shutdown so JDBC resources are properly closed
        LevelingCore.shutdown();
    }

    /**
     * Retrieves the {@link LevelServiceImpl} instance managed by the {@code LevelingCore} class. The
     * {@code LevelService} provides methods for managing player levels and experience points (XP).
     *
     * @return the {@link LevelServiceImpl} instance used by the leveling system.
     */
    public static LevelServiceImpl getLevelService() {
        return levelingService;
    }
}
