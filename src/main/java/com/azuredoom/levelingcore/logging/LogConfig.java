package com.azuredoom.levelingcore.logging;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * LogConfig is a utility class designed for configuring and initializing Java logging with a consistent format. This
 * class customizes log messages by defining a specific format, ensuring uniformity and improved readability, especially
 * for applications requiring structured logging.
 * <p>
 * This class cannot be instantiated and provides a static method to set up and configure a {@link Logger} instance.
 */
public final class LogConfig {

    /**
     * Configures and initializes a {@link Logger} instance for the specified class with a custom log message format.
     * The logger outputs messages to the console with a timestamp, log level, and a prefixed identifier for improved
     * readability.
     *
     * @param clazz the {@code Class} object for which the logger is being configured. This determines the logger name.
     * @return a {@link Logger} instance configured with a custom {@link SimpleFormatter} and a {@link ConsoleHandler}.
     */
    public static Logger setup(Class<?> clazz) {
        var logger = Logger.getLogger(clazz.getName());
        var handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {

            @Override
            public synchronized String format(LogRecord r) {
                return String.format(
                    "[%1$tF %1$tT] %2$s: %3$s%n",
                    new Date(r.getMillis()),
                    r.getLevel(),
                    "LevelingCore: " + formatMessage(r)
                );
            }
        });

        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
        return logger;
    }
}
