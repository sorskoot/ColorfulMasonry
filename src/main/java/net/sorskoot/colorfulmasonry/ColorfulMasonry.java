package net.sorskoot.colorfulmasonry;

import net.fabricmc.api.ModInitializer;
import net.sorskoot.colorfulmasonry.registry.ModBlocks;

public class ColorfulMasonry implements ModInitializer {

    public static final String MOD_ID = "colorfulmasonry";

    @Override
    public void onInitialize() {
        ModBlocks.registerBlocks();
    }

    // Dynamic model generation:
    // https://fabricmc.net/wiki/tutorial:dynamic_model_generation

}
