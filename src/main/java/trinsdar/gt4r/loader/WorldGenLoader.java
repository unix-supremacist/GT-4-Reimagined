package trinsdar.gt4r.loader;

import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.Data;
import muramasa.antimatter.worldgen.object.WorldGenStoneLayer;
import net.minecraft.world.level.Level;
import trinsdar.gt4r.GT4RConfig;
import trinsdar.gt4r.data.GT4RData;

public class WorldGenLoader {


    public static void init() {
        if (GT4RConfig.WORLD.GENERATE_STONE_LAYERS){
            WorldGenStoneLayer.add(GT4RData.GRANITE_BLACK, 2, Level.OVERWORLD);
            WorldGenStoneLayer.add(GT4RData.GRANITE_RED, 2, Level.OVERWORLD);
            WorldGenStoneLayer.add(GT4RData.KOMATIITE, 4, Level.OVERWORLD);
            WorldGenStoneLayer.add(GT4RData.BASALT, 3, Level.OVERWORLD);
            WorldGenStoneLayer.add(GT4RData.MARBLE, 4, Level.OVERWORLD);
            WorldGenStoneLayer.add(GT4RData.LIMESTONE, 3, Level.OVERWORLD);
            WorldGenStoneLayer.add(GT4RData.GREEN_SCHIST, 1, Level.OVERWORLD);
            WorldGenStoneLayer.add(GT4RData.BLUE_SCHIST, 1, Level.OVERWORLD);
            WorldGenStoneLayer.add(GT4RData.KIMBERLITE, 3, Level.OVERWORLD);
            WorldGenStoneLayer.add(GT4RData.QUARTZITE, 4, Level.OVERWORLD);
            if (AntimatterConfig.WORLD.VANILLA_STONE_GEN){
                WorldGenStoneLayer.add(Data.GRANITE, 4, Level.OVERWORLD);
                WorldGenStoneLayer.add(Data.ANDESITE, 4, Level.OVERWORLD);
                WorldGenStoneLayer.add(Data.DIORITE, 4, Level.OVERWORLD);
                WorldGenStoneLayer.add(Data.STONE, 4, Level.OVERWORLD);
            }
        }
    }
}
