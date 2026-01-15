package com.azuredoom.levelingcore.commands;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.util.EventTitleUtil;

import javax.annotation.Nonnull;

import com.azuredoom.levelingcore.api.LevelingCoreApi;

/**
 * The RemoveXpCommand class is responsible for handling the logic to remove experience points (XP) from a player's
 * progress using the LevelingCore API. This command ensures that the leveling system is initialized before modifying
 * the XP. It retrieves the player's XP and calculates their level after removal, providing feedback messages to both
 * the player and the command executor.
 */
public class RemoveXpCommand extends CommandBase {

    @Nonnull
    private final RequiredArg<PlayerRef> playerArg;

    @Nonnull
    private final RequiredArg<Integer> xpArg;

    public RemoveXpCommand() {
        super("removexp", "Remove XP from player");
        this.playerArg = this.withRequiredArg(
            "player",
            "server.commands.levelingcore.addlevel.desc",
            ArgTypes.PLAYER_REF
        );
        this.xpArg = this.withRequiredArg("xpvalue", "server.commands.levelingcore.addlevel.desc", ArgTypes.INTEGER);
    }

    @Override
    protected void executeSync(@Nonnull CommandContext commandContext) {
        if (LevelingCoreApi.getLevelServiceIfPresent().isEmpty()) {
            commandContext.sendMessage(Message.raw("Leveling Core is not initialized"));
            return;
        }
        var playerRef = this.playerArg.get(commandContext);
        var xpRef = this.xpArg.get(commandContext);
        var playerUUID = playerRef.getUuid();
        var removedXPMsg = "Removed " + xpRef + " xp to " + playerRef.getUsername();
        var levelTotalMsg = "Player " + playerRef.getUsername() + " is now level " + LevelingCoreApi
            .getLevelServiceIfPresent()
            .get()
            .getLevel(playerUUID);
        EventTitleUtil.showEventTitleToPlayer(playerRef, Message.raw(levelTotalMsg), Message.raw(removedXPMsg), true);
        commandContext.sendMessage(Message.raw(removedXPMsg));
        commandContext.sendMessage(Message.raw(levelTotalMsg));
        LevelingCoreApi.getLevelServiceIfPresent().get().removeXp(playerUUID, xpRef);
    }
}
