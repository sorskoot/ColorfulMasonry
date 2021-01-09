package net.sorskoot.colorfulmasonry.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sorskoot.colorfulmasonry.ColorfulMasonry;

public class RegisterHelper {
    public static void registerBuildingBlock(Block block, String name) {
		Registry.register(Registry.BLOCK, new Identifier(ColorfulMasonry.MOD_ID, name), block);
        Registry.register(Registry.ITEM, new Identifier(ColorfulMasonry.MOD_ID, name), new BlockItem(block, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
	}
}
