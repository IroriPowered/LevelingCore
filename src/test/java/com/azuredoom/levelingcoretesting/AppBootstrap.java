package com.azuredoom.levelingcoretesting;

import com.azuredoom.levelingcore.config.ConfigManager;
import com.azuredoom.levelingcore.config.LevelFormulaFactory;
import com.azuredoom.levelingcore.database.DataSourceFactory;
import com.azuredoom.levelingcore.database.JdbcLevelRepository;
import com.azuredoom.levelingcore.level.LevelServiceImpl;

import java.nio.file.Path;

public final class AppBootstrap {

    private AppBootstrap() {}

    public static LevelServiceImpl createLevelService(Path configPath) {
        var config = ConfigManager.loadOrCreate(configPath);
        var desc = LevelFormulaFactory.descriptorFromConfig(config);
        var formula = LevelFormulaFactory.fromConfig(config);

        var ds = DataSourceFactory.create(
            config.database.jdbcUrl,
            config.database.username,
            config.database.password,
            config.database.maxPoolSize
        );

        var repository = new JdbcLevelRepository(ds);

        if (config.formula.migrateXP) {
            repository.migrateFormulaIfNeeded(formula, desc);
        }

        return new LevelServiceImpl(formula, repository);
    }
}
