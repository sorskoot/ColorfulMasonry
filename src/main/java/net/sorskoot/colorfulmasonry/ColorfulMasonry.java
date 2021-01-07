package net.sorskoot.colorfulmasonry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sorskoot.colorfulmasonry.registry.ModBlocks;

public class ColorfulMasonry implements ModInitializer {
    
    public static final String MOD_ID = "colorfulmasonry";
    
    // public static final Item EXAMPLE_ITEM = new Item(new Item.Settings());
    // public static final Block BRICKS = new Block(FabricBlockSettings.copy(Blocks.BRICKS)); //new Block(FabricBlockSettings.copy(Blocks.BRICKS));
    
    @Override
	public void onInitialize() {
       ModBlocks.registerBlocks();
    //    Registry.register(Registry.BLOCK, new Identifier(ColorfulMasonry.MOD_ID, "pink_bricks"), BRICKS);
	//    Registry.register(Registry.ITEM, new Identifier(ColorfulMasonry.MOD_ID, "pink_bricks"), new BlockItem(BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

    }
    
    // Dynamic model generation:
    // https://fabricmc.net/wiki/tutorial:dynamic_model_generation

    public static String createItemModelJson(String id, String type) {
        System.out.println("ColorfulMasonry: Generating "+type+"--"+id);
        if ("item".equals(type) || "handheld".equals(type)) {            
        //The two types of items. "handheld" is used mostly for tools and the like, while "generated" is used for everything else. 
            return   
            "{"+
            "  \"parent\": \"colorfulmasonry:block/"+id+"\","+
            "  \"display\": {"+
            "    \"thirdperson\": {"+
            "      \"rotation\": [ 10,-45,170],"+
            "      \"translation\": [0,1.5,-2.75],"+
            "      \"scale\": [0.375,0.375,0.375]"+
            "    }"+
            "  }"+
            "}";
            
        } else if ("block".equals(type)) {
        //However, if the item is a block-item, it will have a different model json than the previous two.
            return 
            "{"+
            "  \"parent\": \"block/cube\","+
            "  \"textures\": {"+
            "    \"down\": \"colorfulmasonry:blocks/"+id+"\","+
            "    \"north\": \"colorfulmasonry:blocks/"+id+"\","+
            "    \"east\": \"colorfulmasonry:blocks/"+id+"\","+
            "    \"south\": \"colorfulmasonry:blocks/"+id+"\","+
            "    \"west\": \"colorfulmasonry:blocks/"+id+"\","+
            "    \"up\": \"colorfulmasonry:blocks/"+id+"\","+
            "    \"particle\": \"colorfulmasonry:blocks/"+id+"\""+
            "  }"+
            "}";
           
        }
        else {
        //If the type is invalid, return an empty json string.
            return "";
        }
    }
}
