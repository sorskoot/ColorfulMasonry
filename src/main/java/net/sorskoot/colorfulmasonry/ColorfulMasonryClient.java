package net.sorskoot.colorfulmasonry;

import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.sorskoot.colorfulmasonry.blocks.MasonryOvenScreen;
import net.sorskoot.colorfulmasonry.registry.MasonryOvenBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class ColorfulMasonryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(MasonryOvenBlocks.MASONRY_OVEN_SCREEN_HANDLER, MasonryOvenScreen::new);
    }
}
