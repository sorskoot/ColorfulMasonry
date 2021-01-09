package net.sorskoot.colorfulmasonry;

public class ItemModelProcessing {
    public static String createItemModelJson(String id, String type) {

        System.out.println("ColorfulMasonry: createItemModelJson "+type+"--"+id);
        if(!id.contains("brick")) return "";

        if ("item".equals(type) || "handheld".equals(type)) {            
        //The two types of items. "handheld" is used mostly for tools and the like, while "generated" is used for everything else. 
            return processBlockItem(id);            
        } else if ("block".equals(type)) {
        //However, if the item is a block-item, it will have a different model json than the previous two.
            String materialName =  extractedMaterialName(id);
            if(id.contains("_slabs")){
                return processSlabs(id, materialName);
            }else if(id.contains("_stairs")){
                return processStairs(id, materialName);
            }else if(id.contains("_wall")){
                return processWall(id, materialName);
            }else{            
                return processBlock(materialName);
            }
        }
     
        //If the type is invalid, return an empty json string.
        return "";
       
    }

    private static String processBlockItem(String id) {

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
    }

    private static String extractedMaterialName(String id) {
        return id.replaceAll("(_slabs_full|_slabs_top|_slabs|_stairs_outer|_stairs_inner|_stairs|_wall_inventory|_wall_post|_wall_side_tall|_wall_side|_wall)", "s");
    }

    private static String processBlock(String materialName) {
        return             
            "{"+
            "  \"parent\": \"block/cube\","+
            "  \"textures\": {"+
            "    \"down\": \"colorfulmasonry:block/"+materialName+"\","+
            "    \"north\": \"colorfulmasonry:block/"+materialName+"\","+
            "    \"east\": \"colorfulmasonry:block/"+materialName+"\","+
            "    \"south\": \"colorfulmasonry:block/"+materialName+"\","+
            "    \"west\": \"colorfulmasonry:block/"+materialName+"\","+
            "    \"up\": \"colorfulmasonry:block/"+materialName+"\","+
            "    \"particle\": \"colorfulmasonry:block/"+materialName+"\""+
            "  }"+
            "}";
    }

    private static String processSlabs(String id, String materialName) {
        String slabType = "slab";
        if(id.contains("_full")){
            slabType = "cube_bottom_top";
        }
        if(id.contains("_top")){
            slabType += "_top";
        }
        return
        "{"+
        "    \"parent\": \"block/"+slabType+"\","+
        "    \"textures\": {"+
        "      \"bottom\": \"colorfulmasonry:block/"+materialName+"\","+
        "      \"top\": \"colorfulmasonry:block/"+materialName+"\","+
        "      \"side\": \"colorfulmasonry:block/"+materialName+"\""+
        "    }"+
        "}";
    }

    private static String processStairs(String id, String materialName) {
        String stairsType = "stairs";
        if(id.contains("stairs_inner")){
            stairsType = "inner_stairs";
        }
        if(id.contains("stairs_outer")){
            stairsType = "outer_stairs";
        }
        return
        "{"+
        "    \"parent\": \"block/"+stairsType+"\","+
        "    \"textures\": {"+
        "      \"bottom\": \"colorfulmasonry:block/"+materialName+"\","+
        "      \"top\": \"colorfulmasonry:block/"+materialName+"\","+
        "      \"side\": \"colorfulmasonry:block/"+materialName+"\""+
        "    }"+
        "}";
    }

    private static String processWall(String id, String materialName) {
        System.out.println("ColorfulMasonry: Generating Walls "+id+"("+materialName+")");
        String wallType = "wall_inventory";
        
        if(id.contains("tall")) wallType = "template_wall_side_tall";
        else if(id.contains("post")) wallType = "template_wall_post";
        else if(id.contains("side")) wallType = "template_wall_side";
        return 
        "{"+
        "    \"parent\": \"minecraft:block/"+wallType+"\","+
        "        \"textures\": {"+
        "    \"wall\":  \"colorfulmasonry:block/"+materialName+"\""+
        "    }"+
        "}";       
        
        // else 
        //   return
        // "{"+
        // "    \"parent\": \"block/wall_inventory\","+
        // "    \"textures\": {"+
        // "     \"wall\": \"colorfulmasonry:block/"+materialName+"\""+
        // "    }"+
        // "}";
    }
}
