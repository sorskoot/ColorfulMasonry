package net.sorskoot.colorfulmasonry.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class MasonryOvenScreenHandler extends ScreenHandler {

   
    protected MasonryOvenScreenHandler(ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return false;
    }
    
}
