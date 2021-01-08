package net.sorskoot.colorfulmasonry;

public class ItemModelProcessing {
    public static String createItemModelJson(String id, String type) {
//        System.out.println("ColorfulMasonry: Generating "+type+"--"+id);
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
            }else{            
                return processBlock(materialName);
            }
        }
        else {
        //If the type is invalid, return an empty json string.
            return "";
        }
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
        return id.replaceAll("(_slabs_full|_slabs_top|_slabs|_stairs_outer|_stairs_inner|_stairs|_walls)", "s");
    }

    private static String processBlock(String materialName) {
        return             
            "{"+
            "  \"parent\": \"block/cube\","+
            "  \"textures\": {"+
            "    \"down\": \"colorfulmasonry:blocks/"+materialName+"\","+
            "    \"north\": \"colorfulmasonry:blocks/"+materialName+"\","+
            "    \"east\": \"colorfulmasonry:blocks/"+materialName+"\","+
            "    \"south\": \"colorfulmasonry:blocks/"+materialName+"\","+
            "    \"west\": \"colorfulmasonry:blocks/"+materialName+"\","+
            "    \"up\": \"colorfulmasonry:blocks/"+materialName+"\","+
            "    \"particle\": \"colorfulmasonry:blocks/"+materialName+"\""+
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
        "      \"bottom\": \"colorfulmasonry:blocks/"+materialName+"\","+
        "      \"top\": \"colorfulmasonry:blocks/"+materialName+"\","+
        "      \"side\": \"colorfulmasonry:blocks/"+materialName+"\""+
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
        System.out.println("ColorfulMasonry: Generating Stairs "+id+"("+stairsType+"--"+materialName+")");
        return
        "{"+
        "    \"parent\": \"block/"+stairsType+"\","+
        "    \"textures\": {"+
        "      \"bottom\": \"colorfulmasonry:blocks/"+materialName+"\","+
        "      \"top\": \"colorfulmasonry:blocks/"+materialName+"\","+
        "      \"side\": \"colorfulmasonry:blocks/"+materialName+"\""+
        "    }"+
        "}";
    }
}
