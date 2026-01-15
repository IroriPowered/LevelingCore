package com.azuredoom.levelingcore.hud;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import com.azuredoom.levelingcore.level.LevelServiceImpl;

public class XPBarHud extends CustomUIHud {

    private LevelServiceImpl levelServiceImpl;

    public XPBarHud(@NonNullDecl PlayerRef playerRef, @NonNullDecl LevelServiceImpl levelServiceImpl) {
        super(playerRef);
        this.levelServiceImpl = levelServiceImpl;
    }

    @Override
    protected void build(@NonNullDecl UICommandBuilder uiCommandBuilder) {
        uiCommandBuilder.append("xpbar.ui");
        update(uiCommandBuilder);
    }

    public void update(UICommandBuilder uiCommandBuilder) {
        var uuid = getPlayerRef().getUuid();
        var currentXp = levelServiceImpl.getXp(uuid);
        var currentLevel = levelServiceImpl.getLevel(uuid);
        var xpForCurrentLevel = levelServiceImpl.getXpForLevel(currentLevel);
        var xpForNextLevel = levelServiceImpl.getXpForLevel(currentLevel + 1);
        var xpIntoLevel = currentXp - xpForCurrentLevel;
        var xpNeededThisLevel = xpForNextLevel - xpForCurrentLevel;
        var progress = (double) xpIntoLevel / xpNeededThisLevel;

        uiCommandBuilder.set("#ProgressBar.Value", progress);
        uiCommandBuilder.set("#Level.TextSpans", Message.raw("LVL " + currentLevel));
        update(false, uiCommandBuilder); // false = don't clear existing UI
    }
}
