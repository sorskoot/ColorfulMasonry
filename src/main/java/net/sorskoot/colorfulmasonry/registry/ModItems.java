package net.sorskoot.colorfulmasonry.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sorskoot.colorfulmasonry.ColorfulMasonry;

public class ModItems {
    
    public static final Item PINK_BRICKS = new Item(new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    

    public static void registerItems(){
        Registry.register(Registry.ITEM, new Identifier(ColorfulMasonry.MOD_ID, "pink_bricks"), PINK_BRICKS);    
    }
}
