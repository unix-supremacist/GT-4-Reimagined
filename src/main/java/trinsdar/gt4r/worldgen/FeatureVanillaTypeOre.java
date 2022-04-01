package trinsdar.gt4r.worldgen;

import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.Data;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.worldgen.WorldGenHelper;
import muramasa.antimatter.worldgen.feature.AntimatterFeature;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.core.Registry;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;

import java.util.BitSet;
import java.util.List;
import java.util.Random;

import static muramasa.antimatter.Data.Coal;
import static muramasa.antimatter.Data.ORE_STONE;
import static trinsdar.gt4r.worldgen.GT4RConfiguredFeatures.*;

public class FeatureVanillaTypeOre extends AntimatterFeature<GT4ROreFeatureConfig> {
    public FeatureVanillaTypeOre() {
        super(GT4ROreFeatureConfig.CODEC, GT4ROreFeatureConfig.class);
    }

    @Override
    public String getId() {
        return "feature_vanilla_type_ore";
    }

    @Override
    public boolean enabled() {
        return AntimatterConfig.WORLD.ORE_VEINS && getRegistry().size() > 0;
    }

    @Override
    public void init() {

    }

    @Override
    public void build(BiomeGenerationSettingsBuilder event) {
        if (AntimatterConfig.WORLD.ORE_VEINS){
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, COPPER);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TIN);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, URANITE);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, URANITE_DEAD);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CASSITERITE);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TETRAHEDRITE);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GALENA);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BAUXITE);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, RUBY);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, SAPPHIRE);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLATINUM);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, IRIDIUM);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, EMERALD);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PYRITE);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, SPHALERITE);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CINNABAR);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TUNGSTATE);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PLATINUM_END);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OLIVINE);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, SODALITE);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CHROMITE);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, SALT);
            event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ROCK_SALT);
            if (AntimatterConfig.WORLD.VANILLA_ORE_GEN){
                event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, IRON);
                event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, COAL);
                event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GOLD);
                event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GOLD_MESA);
                event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, REDSTONE);
                event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DIAMOND);
                event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, EMERALD_VANILLA);
                event.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, LAPIS);
            }
        }
    }

    public ConfiguredFeature<?, ?> getConfiguration(GT4ROreFeatureConfig config){
        return this.configured(config).decorated(FeatureDecorator.RANGE.configured(getRange(config.getMinY(), config.getMaxY()))).squared().count(config.getWeight());
    }

    public RangeDecoratorConfiguration getRange(int min, int max){
        return new RangeDecoratorConfiguration(min, min, max);
    }

    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator generator, Random rand, BlockPos pos, GT4ROreFeatureConfig config) {
        int chunkX = pos.getX() >> 4;
        int chunkZ = pos.getZ() >> 4;
        //Feature.ORE.generate()
        if (!config.getDimensionLocations().contains(world.getLevel().dimension().location())) return false;
        if (!config.getConfigNode().isEnabled()) return false;
        List<BiomeDictionary.Type> types = config.getBiomeTypes();
        List<BiomeDictionary.Type> invalidTypes = config.getInvalidBiomeTypes();
        boolean hasType = types.isEmpty();
        for (BiomeDictionary.Type type : BiomeDictionary.getTypes(ResourceKey.create(Registry.BIOME_REGISTRY, world.getBiome(pos).getRegistryName()))) {
            if (types.contains(type)) {
                hasType = true;
            }
            if (invalidTypes.contains(type)){
                hasType = false;
                break;
            }
        }
        if (hasType == config.isInvertBiomeFilter()){
            return false;
        }

        if (config == SALT_CONFIG){
            return generateOnOceanFloor(world, generator, rand, pos, config);
        }
        return generate2(world, rand, pos, config);
    }

    public boolean generateOnOceanFloor(WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos pos, GT4ROreFeatureConfig config) {

        float f = rand.nextFloat() * (float)Math.PI;
        float f1 = (float)config.getSize() / 8.0F;
        int i = Mth.ceil(((float)config.getSize() / 16.0F * 2.0F + 1.0F) / 2.0F);
        double x0 = (double)pos.getX() + Math.sin(f) * (double)f1;
        double x1 = (double)pos.getX() - Math.sin(f) * (double)f1;
        double z0 = (double)pos.getZ() + Math.cos(f) * (double)f1;
        double z1 = (double)pos.getZ() - Math.cos(f) * (double)f1;
        int x = pos.getX() - Mth.ceil(f1) - i;
        int y;
        int z = pos.getZ() - Mth.ceil(f1) - i;
        int j1 = 2 * (Mth.ceil(f1) + i);
        int k1 = 2 * (2 + i);

        for(int ix = x; ix <= x + j1; ++ix) {
            for(int iz = z; iz <= z + j1; ++iz) {
                y = reader.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, ix, iz);
                double y0 = y + rand.nextInt(3) - 2;
                double y1 = y + rand.nextInt(3) - 2;
                return generateVein(reader, rand, config, x0, x1, z0, z1, y0, y1, x, y, z, j1, k1);
            }
        }

        return false;
    }

    public boolean generate2(WorldGenLevel reader, Random rand, BlockPos pos, GT4ROreFeatureConfig config) {
        float f = rand.nextFloat() * (float)Math.PI;
        float f1 = (float)config.getSize() / 8.0F;
        int i = Mth.ceil(((float)config.getSize() / 16.0F * 2.0F + 1.0F) / 2.0F);
        double x0 = (double)pos.getX() + Math.sin(f) * (double)f1;
        double x1 = (double)pos.getX() - Math.sin(f) * (double)f1;
        double z0 = (double)pos.getZ() + Math.cos(f) * (double)f1;
        double z1 = (double)pos.getZ() - Math.cos(f) * (double)f1;
        double y0 = pos.getY() + rand.nextInt(3) - 2;
        double y1 = pos.getY() + rand.nextInt(3) - 2;
        int x = pos.getX() - Mth.ceil(f1) - i;
        int y = pos.getY() - 2 - i;
        int z = pos.getZ() - Mth.ceil(f1) - i;
        int j1 = 2 * (Mth.ceil(f1) + i);
        int k1 = 2 * (2 + i);

        for(int ix = x; ix <= x + j1; ++ix) {
            for(int iz = z; iz <= z + j1; ++iz) {
                if (y <= reader.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, ix, iz)) {
                    return generateVein(reader, rand, config, x0, x1, z0, z1, y0, y1, x, y, z, j1, k1);
                }
            }
        }

        return false;
    }

    protected boolean generateVein(LevelAccessor worldIn, Random random, GT4ROreFeatureConfig config, double x0, double x1, double z0, double z1, double y0, double y1, int x, int y, int z, int p_207803_19_, int p_207803_20_) {
        int i = 0;
        BitSet bitset = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
        int j = config.getSize();
        double[] adouble = new double[j * 4];

        for(int k = 0; k < j; ++k) {
            float f = (float)k / (float)j;
            double d0 = Mth.lerp(f, x0, x1);
            double d2 = Mth.lerp(f, y0, y1);
            double d4 = Mth.lerp(f, z0, z1);
            double d6 = random.nextDouble() * (double)j / 16.0D;
            double d7 = ((double)(Mth.sin((float)Math.PI * f) + 1.0F) * d6 + 1.0D) / 2.0D;
            adouble[k * 4] = d0;
            adouble[k * 4 + 1] = d2;
            adouble[k * 4 + 2] = d4;
            adouble[k * 4 + 3] = d7;
        }

        for(int i3 = 0; i3 < j - 1; ++i3) {
            if (!(adouble[i3 * 4 + 3] <= 0.0D)) {
                for(int k3 = i3 + 1; k3 < j; ++k3) {
                    if (!(adouble[k3 * 4 + 3] <= 0.0D)) {
                        double d12 = adouble[i3 * 4] - adouble[k3 * 4];
                        double d13 = adouble[i3 * 4 + 1] - adouble[k3 * 4 + 1];
                        double d14 = adouble[i3 * 4 + 2] - adouble[k3 * 4 + 2];
                        double d15 = adouble[i3 * 4 + 3] - adouble[k3 * 4 + 3];
                        if (d15 * d15 > d12 * d12 + d13 * d13 + d14 * d14) {
                            if (d15 > 0.0D) {
                                adouble[k3 * 4 + 3] = -1.0D;
                            } else {
                                adouble[i3 * 4 + 3] = -1.0D;
                            }
                        }
                    }
                }
            }
        }

        for(int j3 = 0; j3 < j; ++j3) {
            double d11 = adouble[j3 * 4 + 3];
            if (!(d11 < 0.0D)) {
                double d1 = adouble[j3 * 4];
                double d3 = adouble[j3 * 4 + 1];
                double d5 = adouble[j3 * 4 + 2];
                int l = Math.max(Mth.floor(d1 - d11), x);
                int l3 = Math.max(Mth.floor(d3 - d11), y);
                int i1 = Math.max(Mth.floor(d5 - d11), z);
                int j1 = Math.max(Mth.floor(d1 + d11), l);
                int k1 = Math.max(Mth.floor(d3 + d11), l3);
                int l1 = Math.max(Mth.floor(d5 + d11), i1);

                for(int i2 = l; i2 <= j1; ++i2) {
                    double d8 = ((double)i2 + 0.5D - d1) / d11;
                    if (d8 * d8 < 1.0D) {
                        for(int j2 = l3; j2 <= k1; ++j2) {
                            double d9 = ((double)j2 + 0.5D - d3) / d11;
                            if (d8 * d8 + d9 * d9 < 1.0D) {
                                for(int k2 = i1; k2 <= l1; ++k2) {
                                    double d10 = ((double)k2 + 0.5D - d5) / d11;
                                    if (d8 * d8 + d9 * d9 + d10 * d10 < 1.0D) {
                                        int l2 = i2 - x + (j2 - y) * p_207803_19_ + (k2 - z) * p_207803_19_ * p_207803_20_;
                                        if (!bitset.get(l2)) {
                                            bitset.set(l2);
                                            blockpos$mutable.set(i2, j2, k2);
                                            Material mat = Material.get(config.getPrimary());
                                            if (mat.has(ORE_STONE) && mat != Coal){
                                                StoneType stone = WorldGenHelper.STONE_MAP.get(worldIn.getBlockState(blockpos$mutable));
                                                if (stone == null) continue;
                                                if (WorldGenHelper.setState(worldIn, blockpos$mutable, ORE_STONE.get().get(mat).asState())) {
                                                    ++i;
                                                    continue;
                                                }
                                            }
                                            if (config.getSecondary() != null && !config.getSecondary().equals("null") && config.getSecondaryChance()> 0 && config.getSecondaryChance() < 100){
                                                mat = random.nextInt(100) < config.getSecondaryChance() ? Material.get(config.getSecondary()) : Material.get(config.getPrimary());
                                            }
                                            if (WorldGenHelper.setOre(worldIn, blockpos$mutable, worldIn.getBlockState(blockpos$mutable), mat, Data.ORE)) {
                                                ++i;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return i > 0;
    }
}
