package net.sorskoot.colorfulmasonry;

import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.sorskoot.colorfulmasonry.gui.MasonryOvenScreen;
import net.sorskoot.colorfulmasonry.registry.MasonryOvenBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class ColorfulMasonryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        
      ScreenRegistry.register(MasonryOvenBlocks.SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new MasonryOvenScreen(gui, inventory.player, title));
    
    }
}
