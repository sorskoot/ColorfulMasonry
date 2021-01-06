package net.sorskoot.colorfulmasonry.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sorskoot.colorfulmasonry.ColorfulMasonry;

public class ModBlocks {
        
    public static final Block PINK_BRICKS_BLOCK = 
        new Block(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES));

    public static void registerBlocks(){
    
        Registry.register(Registry.BLOCK, new Identifier(ColorfulMasonry.MOD_ID, "pink_bricks"), PINK_BRICKS_BLOCK);
    }
}
