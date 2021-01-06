package net.sorskoot.colorfulmasonry;

import net.fabricmc.api.ModInitializer;
import net.sorskoot.colorfulmasonry.registry.ModBlocks;
import net.sorskoot.colorfulmasonry.registry.ModItems;

public class ColorfulMasonry implements ModInitializer {
    
    public static final String MOD_ID = "colorfulmasonry";

    @Override
	public void onInitialize() {
        ModItems.registerItems();		
        ModBlocks.registerBlocks();
	}
}
