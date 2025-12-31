package com.azuredoom.levelingcore;

import com.azuredoom.levelingcore.api.LevelingCoreApi;
import com.azuredoom.levelingcore.config.ConfigBootstrap;
import com.azuredoom.levelingcore.exceptions.LevelingCoreException;
import com.azuredoom.levelingcore.level.LevelServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class LevelingCore {

    public static final System.Logger LOGGER = System.getLogger(LevelingCore.class.getName());

    public static final Path configPath = Paths.get("./data/plugins/levelingcore/");

    private static final ConfigBootstrap.Bootstrap bootstrap = ConfigBootstrap.bootstrap(configPath);

    private static LevelServiceImpl levelingService = bootstrap.service();

    public LevelingCore() {}

    // TODO: Call this from the server startup hook
    public static void init() {
        LOGGER.log(System.Logger.Level.INFO, "Leveling Core initialized");
        levelingService = bootstrap.service();
    }

    static void main() {
        // TODO: Remove once hooks into the player/mob kill events are found and integrable.
        var testId = UUID.fromString("d3804858-4bb8-4026-ae21-386255ed467d");
        if (LevelingCoreApi.getLevelServiceIfPresent().isPresent()) {
            var levelingService = LevelingCoreApi.getLevelServiceIfPresent().get();
            levelingService.addXp(testId, 500);
            // TODO: Move to chat based logging instead of System loggers
            LevelingCore.LOGGER.log(System.Logger.Level.INFO, String.format("XP: %d", levelingService.getXp(testId)));
            LevelingCore.LOGGER.log(
                System.Logger.Level.INFO,
                String.format("Level: %d", levelingService.getLevel(testId))
            );
        }
        // TODO: Move to server shutdown so JDBC resources are properly closed
        try {
            LevelingCore.bootstrap.closeable().close();
        } catch (Exception e) {
            throw new LevelingCoreException("Failed to close resources", e);
        }
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
