package trinsdar.gt4r.loader.multi;

import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.ItemLike;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static trinsdar.gt4r.data.Materials.DistilledWater;
import static trinsdar.gt4r.data.Materials.Lubricant;
import static trinsdar.gt4r.data.RecipeMaps.INDUSTRIAL_SAWMILLING;

public class IndustrialSawmill {
    public static void init(){
        addWoodRecipe(ItemTags.OAK_LOGS, Items.OAK_PLANKS);
        addWoodRecipe(ItemTags.BIRCH_LOGS, Items.BIRCH_PLANKS);
        addWoodRecipe(ItemTags.SPRUCE_LOGS, Items.SPRUCE_PLANKS);
        addWoodRecipe(ItemTags.JUNGLE_LOGS, Items.JUNGLE_PLANKS);
        addWoodRecipe(ItemTags.DARK_OAK_LOGS, Items.DARK_OAK_PLANKS);
        addWoodRecipe(ItemTags.ACACIA_LOGS, Items.ACACIA_PLANKS);
        addWoodRecipe(ItemTags.CRIMSON_STEMS, Items.CRIMSON_PLANKS);
        addWoodRecipe(ItemTags.WARPED_STEMS, Items.WARPED_PLANKS);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(GTRubberData.RUBBER_LOGS, 1)).fi(Water.getLiquid(40)).io(new ItemStack(GTRubberData.StickyResin), DUST.get(Wood, 1), new ItemStack(GTRubberData.RUBBER_PLANKS, 6)).add("rubber_log",200, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(GTRubberData.RUBBER_LOGS, 1)).fi(Lubricant.getLiquid(10)).io(new ItemStack(GTRubberData.StickyResin), DUST.get(Wood, 1), new ItemStack(GTRubberData.RUBBER_PLANKS, 6)).add("rubber_log_1",100, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(GTRubberData.RUBBER_LOGS, 1)).fi(DistilledWater.getLiquid(30)).io(new ItemStack(GTRubberData.StickyResin), DUST.get(Wood, 1), new ItemStack(GTRubberData.RUBBER_PLANKS, 6)).add("rubber_log_2",200, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(Items.MELON, 1)).fi(Water.getLiquid(40)).io(new ItemStack(Items.MELON_SLICE, 7)).add("melon",200, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(Items.MELON, 1)).fi(DistilledWater.getLiquid(30)).io(new ItemStack(Items.MELON_SLICE, 7)).add("melon_1",200, 30);
    }


    private static void addWoodRecipe(TagKey<Item> log, ItemLike wood){
        String woodID = AntimatterPlatformUtils.getIdFromItem(wood.asItem()).getPath().replace("_planks", "");
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(log, 1)).fi(Water.getLiquid(40)).io(new ItemStack(wood, 6), DUST.get(Wood, 1)).add(woodID + "_log",200, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(log, 1)).fi(Lubricant.getLiquid(10)).io(new ItemStack(wood, 6), DUST.get(Wood, 1)).add(woodID + "_log_1",100, 30);
        INDUSTRIAL_SAWMILLING.RB().ii(RecipeIngredient.of(log, 1)).fi(DistilledWater.getLiquid(30)).io(new ItemStack(wood, 6), DUST.get(Wood, 1)).add(woodID + "_log_2",200, 30);
    }
}
