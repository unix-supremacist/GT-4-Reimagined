package trinsdar.gt4r.data;

import io.github.gregtechintergalactical.gtutility.GTUtilityData;
import io.github.gregtechintergalactical.gtutility.machine.DrumMachine;
import io.github.gregtechintergalactical.gtutility.machine.LockerMachine;
import io.github.gregtechintergalactical.gtutility.machine.MaterialMachine;
import io.github.gregtechintergalactical.gtutility.machine.WorkbenchMachine;
import muramasa.antimatter.blockentity.single.BlockEntityBatteryBuffer;
import muramasa.antimatter.blockentity.single.BlockEntityTransformer;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.machine.types.BasicMachine;
import muramasa.antimatter.machine.types.BasicMultiMachine;
import muramasa.antimatter.machine.types.GeneratorMachine;
import muramasa.antimatter.machine.types.HatchMachine;
import muramasa.antimatter.machine.types.MultiMachine;
import muramasa.antimatter.machine.types.TankMachine;
import muramasa.antimatter.blockentity.single.BlockEntityDigitalTransformer;
import net.minecraft.sounds.SoundEvents;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.block.BlockRedstoneMachine;
import trinsdar.gt4r.machine.*;
import trinsdar.gt4r.blockentity.multi.*;
import trinsdar.gt4r.blockentity.multi.BlockEntityDistillationTower;
import trinsdar.gt4r.blockentity.single.*;
import trinsdar.gt4r.blockentity.single.BlockEntityQuantumChest;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.cover.ICover.emptyFactory;
import static muramasa.antimatter.machine.MachineFlag.*;
import static muramasa.antimatter.machine.Tier.*;
import static trinsdar.gt4r.data.GT4RData.COVER_DYNAMO_OLD;
import static trinsdar.gt4r.data.GT4RData.COVER_FUSION_INPUT;
import static trinsdar.gt4r.data.GT4RData.COVER_FUSION_OUTPUT;
import static trinsdar.gt4r.data.GT4RData.COVER_STEAM_VENT;
import static trinsdar.gt4r.data.RecipeMaps.*;

public class Machines {

    public static BasicMachine ALLOY_SMELTER = new UpgradeableMachine(Ref.ID, "alloy_smelter").setMap(ALLOY_SMELTING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine ASSEMBLER = new UpgradeableMachine(Ref.ID, "assembler").setMap(ASSEMBLING).setTiers(LV).addFlags(GUI, ITEM).custom();
    public static BasicMachine BENDER = new UpgradeableMachine(Ref.ID, "plate_bender").setMap(BENDING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine CANNER = new UpgradeableMachine(Ref.ID, "canner").setMap(CANNING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine COMPRESSOR = new UpgradeableMachine(Ref.ID, "compressor").setMap(COMPRESSING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine CUTTER = new UpgradeableMachine(Ref.ID, "cutter").setMap(CUTTING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine FURNACE = new UpgradeableMachine(Ref.ID, "furnace").setMap(SMELTING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine EXTRACTOR = new UpgradeableMachine(Ref.ID, "extractor").setMap(EXTRACTING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine EXTRUDER = new BasicMachine(Ref.ID, "extruder").setTiers(MV).setMap(EXTRUDING).addFlags(GUI, ITEM).custom();
    public static BasicMachine LATHE = new UpgradeableMachine(Ref.ID, "lathe").setMap(LATHING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine MACERATOR = new UpgradeableMachine(Ref.ID, "macerator").setMap(MACERATING).addFlags(GUI, ITEM).setTiers(LV, MV).custom().overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).setTierSpecificLang();
    public static BasicMachine RECYCLER = new UpgradeableMachine(Ref.ID, "recycler").setMap(RECYCLING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine SCANNER = new UpgradeableMachine(Ref.ID, "scanner").setTiers(HV).setMap(SCANNING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine WIRE_MILL = new UpgradeableMachine(Ref.ID, "wire_mill").setMap(WIRE_MILLING).setTiers(LV).addFlags(GUI, ITEM).custom();
    public static BasicMachine CENTRIFUGE = new UpgradeableMachine(Ref.ID, "centrifuge").setMap(CENTRIFUGING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine ELECTROLYZER = new UpgradeableMachine(Ref.ID, "electrolyzer").setMap(ELECTROLYZING).addFlags(GUI, ITEM, FLUID).setTiers(LV, MV).setTierSpecificLang();
    public static BasicMachine CHEMICAL_REACTOR = new UpgradeableMachine(Ref.ID, "chemical_reactor").setTiers(MV).setMap(CHEMICAL_REACTING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_CANNER = new UpgradeableMachine(Ref.ID, "fluid_canner").setMap(FLUID_CANNING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine DISASSEMBLER = new UpgradeableMachine(Ref.ID, "disassembler").setMap(DISASSEMBLING).setTiers(LV).addFlags(GUI, ITEM).custom();
    public static BasicMachine MASS_FABRICATOR = new UpgradeableMachine(Ref.ID, "mass_fabricator").setTiers(EV).setMap(MASS_FABRICATING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine REPLICATOR = new UpgradeableMachine(Ref.ID, "replicator").setTiers(EV).setMap(REPLICATING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FORGE_HAMMER = new UpgradeableMachine(Ref.ID, "forge_hammer").setMap(HAMMERING).setTiers(LV).addFlags(GUI, ITEM).setTile(BlockEntityForgeHammer::new);
    public static BasicMachine ORE_WASHER = new UpgradeableMachine(Ref.ID, "ore_washer").setMap(ORE_WASHING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine THERMAL_CENTRIFUGE = new UpgradeableMachine(Ref.ID, "thermal_centrifuge").setTiers(MV).setMap(THERMAL_CENTRIFUGING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_EXTRACTOR = new UpgradeableMachine(Ref.ID, "fluid_extractor").setMap(FLUID_EXTRACTING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_SOLIDIFIER = new UpgradeableMachine(Ref.ID, "fluid_solidifier").setMap(FLUID_SOLIDIFYING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine PUMP = new BasicMachine(Ref.ID, "pump").addFlags(GUI, ITEM, FLUID).setTiers(LV).setTile(BlockEntityPump::new);
    public static BasicMachine SIFTER = new UpgradeableMachine(Ref.ID, "sifter").setMap(SIFTING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine BATH = new UpgradeableMachine(Ref.ID, "bath").setMap(BATHING).setTiers(LV).addFlags(GUI, ITEM);
    public static BasicMachine DISTILLERY = new UpgradeableMachine(Ref.ID, "distillery").setMap(BASIC_DISTILLING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FERMENTER = new UpgradeableMachine(Ref.ID, "fermenter").setMap(FERMENTING).setTiers(LV).addFlags(GUI, ITEM, FLUID);
    public static NonSolidMachine DUSTBIN = new NonSolidMachine(Ref.ID, "dustbin").setMap(RecipeMaps.DUSTBIN).addFlags(GUI, ITEM).setTiers(LV).custom().baseTexture(Textures.DUSTBIN_HANDLER).covers(emptyFactory).frontCovers().setTile(BlockEntityDustBin::new);

    public static SteamMachine COAL_BOILER = new SteamMachine(Ref.ID, "coal_boiler").setMap(COAL_BOILERS).setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID, CELL).baseTexture(Textures.BOILER_HANDLER).setTile(BlockEntityCoalBoiler::new).noCovers();
    public static SteamMachine STEAM_FURNACE = new SteamMachine(Ref.ID, "steam_furnace").setTiers(BRONZE, STEEL).addFlags(GUI, STEAM, ITEM, FLUID).setMap(STEAM_SMELTING).baseTexture(Textures.BOILER_HANDLER).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_MACERATOR = new SteamMachine(Ref.ID, "steam_macerator").setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID).setMap(STEAM_MACERATING).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_EXTRACTOR = new SteamMachine(Ref.ID, "steam_extractor").setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID).setMap(STEAM_EXTRACTING).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_FORGE_HAMMER = new SteamMachine(Ref.ID, "steam_forge_hammer").setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID).setMap(STEAM_HAMMERING).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_COMPRESSOR = new SteamMachine(Ref.ID, "steam_compressor").setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID).setMap(STEAM_COMPRESSING).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_ALLOY_SMELTER = new SteamMachine(Ref.ID, "steam_alloy_smelter").setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID).setMap(STEAM_ALLOY_SMELTING).baseTexture(Textures.BOILER_HANDLER).covers(COVER_STEAM_VENT);

    public static BasicMachine TELEPORTER = new BasicMachine(Ref.ID, "teleporter").setTiers(HV, LUV).setTile(BlockEntityTeleporter::new).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER);
    public static BasicMachine COMPUTER_CUBE = new BasicMachine(Ref.ID, "computer_cube").setTiers(LV).overlayTexture(Textures.SIMPLE_SIDED).noCovers();
    public static BasicMachine ELECTRIC_ITEM_FILTER = new BasicMachine(Ref.ID, "electric_item_filter").setTiers(LV).addFlags(GUI, ITEM).setTile(BlockEntityItemFilter::new).noCovers().allowFrontIO().setAllowVerticalFacing(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine ELECTRIC_TYPE_FILTER = new BasicMachine(Ref.ID, "electric_type_filter").setTiers(LV).addFlags(GUI, ITEM).setTile(BlockEntityTypeFilter::new).noCovers().allowFrontIO().setAllowVerticalFacing(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine ELECTRIC_ITEM_TRANSLOCATOR = new BasicMachine(Ref.ID, "electric_item_translocator").setTiers(LV).addFlags(GUI, ITEM).setTile(BlockEntityTranslocator.BlockEntityItemTranslocator::new).noCovers().allowFrontIO().setAllowVerticalFacing(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine ELECTRIC_FLUID_TRANSLOCATOR = new BasicMachine(Ref.ID, "electric_fluid_translocator").setTiers(LV).addFlags(GUI, ITEM, FLUID).setTile(BlockEntityTranslocator.BlockEntityFluidTranslocator::new).noCovers().allowFrontIO().setAllowVerticalFacing(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);

    public static BasicMachine PLAYER_DETECTOR = new BasicMachine(Ref.ID, "player_detector").setTiers(LV).setTile(BlockEntityPlayerDetector::new).setBlock(BlockRedstoneMachine::new).setItemBlockClass(() -> BlockRedstoneMachine.class).frontCovers().noCovers().allowFrontIO().overlayTexture(Textures.SIMPLE_ACTIVE_SIDED);

    public static BasicMultiMachine<?> COKE_OVEN = new BasicMultiMachine<>(Ref.ID, "coke_oven").setMap(COKING).setTiers(NONE).addFlags(GUI, ITEM, FLUID).setTile(BlockEntityCokeOven::new);
    public static BasicMultiMachine<?> PRIMITIVE_BLAST_FURNACE = new BasicMultiMachine<>(Ref.ID, "primitive_blast_furnace").setMap(BASIC_BLASTING).setTiers(NONE).addFlags(GUI, ITEM).setTile(BlockEntityPrimitiveBlastFurnace::new);
    public static MultiMachine CHARCOAL_PIT = new MultiMachine(Ref.ID, "charcoal_pit").setTiers(LV).setTile(BlockEntityCharcoalPit::new);
    public static BasicMultiMachine<?> BLAST_FURNACE = new UpgradeableBasicMultiMachine(Ref.ID, "industrial_blast_furnace").setMap(BLASTING).setTiers(MV).addFlags(GUI, ITEM, ENERGY).setTile(BlockEntityIndustrialBlastFurnace::new).setSound(SoundEvents.CAT_PURREOW, 1.0f);
    public static BasicMultiMachine<?> IMPLOSION_COMPRESSOR = new UpgradeableBasicMultiMachine(Ref.ID, "implosion_compressor").setMap(IMPLOSION_COMPRESSING).setTiers(LV).addFlags(GUI, ITEM, ENERGY).setTile(BlockEntityImplosionCompressor::new);
    public static BasicMultiMachine<?> INDUSTRIAL_GRINDER = new UpgradeableBasicMultiMachine(Ref.ID, "industrial_grinder").setMap(INDUSTRIAL_GRINDING).setTiers(MV).addFlags(GUI, ITEM, ENERGY, FLUID).setTile(BlockEntityIndustrialGrinder::new);
    public static BasicMultiMachine<?> INDUSTRIAL_SAWMILL = new UpgradeableBasicMultiMachine(Ref.ID, "industrial_sawmill").setMap(INDUSTRIAL_SAWMILLING).setTiers(MV).addFlags(GUI, ITEM, ENERGY, FLUID).setTile(BlockEntityIndustrialSawmill::new);
    public static BasicMultiMachine<?> DISTILLATION_TOWER = new UpgradeableBasicMultiMachine(Ref.ID, "distillation_tower").setMap(DISTILLING).setTiers(MV).addFlags(GUI, ITEM, ENERGY, FLUID).setTile(BlockEntityDistillationTower::new);
    public static BasicMultiMachine<?> VACUUM_FREEZER = new UpgradeableBasicMultiMachine(Ref.ID, "vacuum_freezer").setMap(VACUUM_FREEZING).setTiers(LV).addFlags(GUI, ITEM, FLUID,ENERGY).setTile(BlockEntityVacuumFreezer::new);
    public static BasicMultiMachine<?> PYROLYSIS_OVEN = new UpgradeableBasicMultiMachine(Ref.ID, "pyrolysis_oven").setTiers(LV).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(BlockEntityPyrolysisOven::new);
    public static MultiMachine THERMAL_BOILER = new MultiMachine(Ref.ID, "thermal_boiler").setMap(THERMAL_BOILER_FUELS).setTiers(LV).addFlags(GUI, ITEM, FLUID).setTile(BlockEntityThermalBoiler::new);
    public static MultiMachine LARGE_STEAM_TURBINE = new MultiMachine(Ref.ID, "large_steam_turbine").setMap(LARGE_STEAM_FUELS).setTiers(EV).addFlags(GUI, FLUID, ITEM, ENERGY, GENERATOR).setTile(BlockEntityLargeTurbine::new).custom(Textures.TURBINE);
    public static MultiMachine LARGE_GAS_TURBINE = new MultiMachine(Ref.ID, "large_gas_turbine").setMap(LARGE_GAS_FUELS).setTiers(IV).addFlags(GUI, FLUID, ITEM, ENERGY, GENERATOR).setTile(BlockEntityLargeTurbine::new).custom(Textures.TURBINE);
    public static MultiMachine LARGE_HEAT_EXCHANGER = new MultiMachine(Ref.ID, "large_heat_exchanger").setTiers(EV).addFlags(GUI, FLUID, ENERGY).setTile(BlockEntityLargeHeatExchanger::new);
    public static MultiMachine FUSION_REACTOR = new MultiMachine(Ref.ID, "fusion_control_computer").setMap(FUSION).setTiers(IV).addFlags(GUI, FLUID, ENERGY).setTile(BlockEntityFusionReactor::new);

    public static HatchMachine HATCH_ITEM_I = new HatchMachine(Ref.ID, "item_input_hatch", COVERINPUT).addFlags(GUI, ITEM).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine HATCH_ITEM_O = new HatchMachine(Ref.ID, "item_output_hatch", COVEROUTPUT).addFlags(GUI, ITEM).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine HATCH_FLUID_I = new HatchMachine(Ref.ID, "fluid_input_hatch", COVERINPUT).addFlags(GUI, FLUID, CELL).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine HATCH_FLUID_O = new HatchMachine(Ref.ID, "fluid_output_hatch", COVEROUTPUT).addFlags(GUI, FLUID, CELL).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine HATCH_MUFFLER = new HatchMachine(Ref.ID, "muffler_hatch", COVERMUFFLER).addFlags(GUI, ITEM).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine HATCH_DYNAMO = new HatchMachine(Ref.ID, "dynamo_hatch", COVER_DYNAMO_OLD).addFlags(ENERGY).setTiers(EV, IV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine FUSION_ITEM_INJECTOR = new HatchMachine(Ref.ID, "fusion_item_injector", COVER_FUSION_INPUT).addFlags(GUI, ITEM).baseTexture(Textures.FUSION_IN).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine FUSION_ITEM_EXTRACTOR = new HatchMachine(Ref.ID, "fusion_item_extractor", COVER_FUSION_OUTPUT).addFlags(GUI, ITEM).baseTexture(Textures.FUSION_OUT).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine FUSION_FLUID_INJECTOR = new HatchMachine(Ref.ID, "fusion_fluid_injector", COVER_FUSION_INPUT).addFlags(GUI, FLUID, CELL).baseTexture(Textures.FUSION_IN).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine FUSION_FLUID_EXTRACTOR = new HatchMachine(Ref.ID, "fusion_fluid_extractor", COVER_FUSION_OUTPUT).addFlags(GUI, FLUID, CELL).baseTexture(Textures.FUSION_OUT).setTiers(LV).setAllowVerticalFacing(false).allowFrontIO();
    public static HatchMachine FUSION_ENERGY_INJECTOR = new HatchMachine(Ref.ID, "fusion_energy_injector", emptyFactory).addFlags(ENERGY).baseTexture(Textures.FUSION_IN).setTiers(IV).setAllowVerticalFacing(false).allowFrontIO();
    //public static HatchMachine FUSION_ENERGY_EXTRACTOR = new HatchMachine(Ref.ID, "fusion_energy_extractor", COVER_DYNAMO_OLD).addFlags(ENERGY).baseTexture(Textures.FUSION_OUT).setTiers(UV).setAllowVerticalFacing(false).allowFrontIO();

    public static TankMachine QUANTUM_TANK = new TankMachine(Ref.ID, "quantum_tank").addFlags(GUI, CELL).setTiers(MAX).setTile(BlockEntityQuantumTank::new).frontCovers().allowFrontIO();
    public static TankMachine DIGITAL_TANK = new TankMachine(Ref.ID, "digital_tank").addFlags(GUI, CELL, ITEM).setTiers(LV).setTile(BlockEntityDigitalTank::new).frontCovers().allowFrontIO();
    public static StorageMachine QUANTUM_CHEST = new StorageMachine(Ref.ID, "quantum_chest").addFlags(GUI, ITEM).setTiers(MAX).setTile(BlockEntityQuantumChest::new);
    public static StorageMachine DIGITAL_CHEST = new StorageMachine(Ref.ID, "digital_chest").addFlags(GUI, ITEM).setTiers(LV).setTile(BlockEntityDigitalChest::new);

    public static DrumMachine BRONZE_DRUM = GTUtilityData.createDrum(Materials.Bronze, 16000);
    public static DrumMachine STEEL_DRUM = GTUtilityData.createDrum(Materials.Steel, 32000);
    public static DrumMachine STAINLESS_STEEL_DRUM = GTUtilityData.createDrum(Materials.StainlessSteel, 64000);
    public static DrumMachine INVAR_DRUM = GTUtilityData.createDrum(Materials.Invar, 48000);
    public static DrumMachine TUNGSTEN_DRUM = GTUtilityData.createDrum(Materials.Tungsten, 256000);
    public static DrumMachine TUNGSTENSTEEL_DRUM = GTUtilityData.createDrum(Materials.TungstenSteel, 256000);
    public static DrumMachine NETHERITE_DRUM = GTUtilityData.createDrum(AntimatterMaterials.Netherite, 128000);

    public static MaterialMachine IRON_CABINET = new MaterialMachine(Ref.ID, "iron_cabinet", AntimatterMaterials.Iron).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityCabinet(m, p, s, 222));
    public static MaterialMachine ALUMINIUM_CABINET = new MaterialMachine(Ref.ID, "aluminium_cabinet", Materials.Aluminium).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityCabinet(m, p, s, 222));
    public static MaterialMachine WROUGHT_IRON_CABINET = new MaterialMachine(Ref.ID, "wrought_iron_cabinet", Materials.WroughtIron).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityCabinet(m, p, s, 222));
    public static MaterialMachine BRASS_CABINET = new MaterialMachine(Ref.ID, "brass_cabinet", Materials.Brass).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityCabinet(m, p, s, 222));
    public static MaterialMachine CUPRONICKEL_CABINET = new MaterialMachine(Ref.ID, "cupronickel_cabinet", Materials.Cupronickel).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityCabinet(m, p, s, 222));
    public static MaterialMachine ELECTRUM_CABINET = new MaterialMachine(Ref.ID, "electrum_cabinet", Materials.Electrum).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityCabinet(m, p, s, 240));
    public static MaterialMachine GOLD_CABINET = new MaterialMachine(Ref.ID, "gold_cabinet", AntimatterMaterials.Gold).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityCabinet(m, p, s, 240));
    public static MaterialMachine SILVER_CABINET = new MaterialMachine(Ref.ID, "silver_cabinet", Materials.Silver).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityCabinet(m, p, s, 240));
    public static MaterialMachine MAGNALIUM_CABINET = new MaterialMachine(Ref.ID, "magnalium_cabinet", Materials.Magnalium).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityCabinet(m, p, s, 240));
    public static MaterialMachine PLATINUM_CABINET = new MaterialMachine(Ref.ID, "platinum_cabinet", Materials.Platinum).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityCabinet(m, p, s, 258));
    public static MaterialMachine OSMIUM_CABINET = new MaterialMachine(Ref.ID, "osmium_cabinet", Materials.Osmium).addFlags(ITEM, GUI, COVERABLE).overlayTexture(Textures.CABINET_OVERLAY_HANDLER).baseTexture(Textures.CABINET_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityCabinet(m, p, s, 276));

    public static MaterialMachine IRON_CHEST = new ChestMachine(Ref.ID, "iron_chest", AntimatterMaterials.Iron).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityChest(m, p, s, 222)).tesr().noCovers();
    public static MaterialMachine ALUMINIUM_CHEST = new ChestMachine(Ref.ID, "aluminium_chest", Materials.Aluminium).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityChest(m, p, s, 222)).tesr().noCovers();
    public static MaterialMachine WROUGHT_IRON_CHEST = new ChestMachine(Ref.ID, "wrought_iron_chest", Materials.WroughtIron).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityChest(m, p, s, 222)).tesr().noCovers();
    public static MaterialMachine BRASS_CHEST = new ChestMachine(Ref.ID, "brass_chest", Materials.Brass).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityChest(m, p, s, 222)).tesr().noCovers();
    public static MaterialMachine CUPRONICKEL_CHEST = new ChestMachine(Ref.ID, "cupronickel_chest", Materials.Cupronickel).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityChest(m, p, s, 222)).tesr().noCovers();
    public static MaterialMachine ELECTRUM_CHEST = new ChestMachine(Ref.ID, "electrum_chest", Materials.Electrum).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityChest(m, p, s, 240)).tesr().noCovers();
    public static MaterialMachine GOLD_CHEST = new ChestMachine(Ref.ID, "gold_chest", AntimatterMaterials.Gold).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityChest(m, p, s, 240)).tesr().noCovers();
    public static MaterialMachine SILVER_CHEST = new ChestMachine(Ref.ID, "silver_chest", Materials.Silver).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityChest(m, p, s, 240)).tesr().noCovers();
    public static MaterialMachine MAGNALIUM_CHEST = new ChestMachine(Ref.ID, "magnalium_chest", Materials.Magnalium).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityChest(m, p, s, 240)).tesr().noCovers();
    public static MaterialMachine PLATINUM_CHEST = new ChestMachine(Ref.ID, "platinum_chest", Materials.Platinum).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityChest(m, p, s, 258)).tesr().noCovers();
    public static MaterialMachine OSMIUM_CHEST = new ChestMachine(Ref.ID, "osmium_chest", Materials.Osmium).addFlags(ITEM, GUI).overlayTexture(Textures.CHEST_OVERLAY_HANDLER).baseTexture(Textures.CHEST_HANDLER).setTiers(LV).setTile((m, p, s) -> new BlockEntityChest(m, p, s, 276)).tesr().noCovers();

    public static WorkbenchMachine BRONZE_WORKBENCH = GTUtilityData.createWorkbench(Materials.Bronze, false);
    public static WorkbenchMachine IRON_WORKBENCH = GTUtilityData.createWorkbench(AntimatterMaterials.Iron, false);
    public static WorkbenchMachine ALUMINIUM_WORKBENCH = GTUtilityData.createWorkbench(Materials.Aluminium, false);
    public static WorkbenchMachine IRON_CHARGING_WORKBENCH = GTUtilityData.createWorkbench(AntimatterMaterials.Iron, true);
    public static WorkbenchMachine ALUMINIUM_CHARGING_WORKBENCH = GTUtilityData.createWorkbench(Materials.Aluminium, true);
    public static LockerMachine IRON_LOCKER = GTUtilityData.createLocker(AntimatterMaterials.Iron, false);
    public static LockerMachine ALUMINIUM_LOCKER = GTUtilityData.createLocker(Materials.Aluminium, false);
    public static LockerMachine IRON_CHARGING_LOCKER = GTUtilityData.createLocker(AntimatterMaterials.Iron, true);
    public static LockerMachine ALUMINIUM_CHARGING_LOCKER = GTUtilityData.createLocker(Materials.Aluminium, true);

    public static GeneratorMachine STEAM_TURBINE = new GeneratorMachine(Ref.ID, "steam_turbine").frontCovers().allowFrontIO().setMap(STEAM_FUELS).setTiers(LV).addFlags(GUI, ITEM, FLUID, GENERATOR, CELL).covers(emptyFactory,emptyFactory,emptyFactory,emptyFactory,emptyFactory, COVER_DYNAMO_OLD).setAllowVerticalFacing(false).setOutputCover(COVER_DYNAMO_OLD).setTile(BlockEntityCoveredGenerator::new);
    public static GeneratorMachine GAS_TURBINE = new GeneratorMachine(Ref.ID, "gas_turbine").frontCovers().allowFrontIO().setMap(GAS_FUELS).setTiers(LV).addFlags(GUI, ITEM, FLUID, GENERATOR, CELL).covers(emptyFactory,emptyFactory,emptyFactory,emptyFactory,emptyFactory, COVER_DYNAMO_OLD).setAllowVerticalFacing(false).setOutputCover(COVER_DYNAMO_OLD).setTile(BlockEntityCoveredGenerator::new);
    public static BasicMachine HEAT_EXCHANGER = new BasicMachine(Ref.ID, "heat_exchanger").setMap(HOT_FUELS).setTiers(LV).addFlags(GUI, FLUID).overlayTexture(Textures.LEFT_RIGHT_HANDLER).covers(emptyFactory).setTile(BlockEntityHeatExchanger::new);
    public static GeneratorMachine DIESEL_GENERATOR = new GeneratorMachine(Ref.ID, "diesel_generator").frontCovers().allowFrontIO().setMap(DIESEL_FUELS).setTiers(LV).addFlags(GUI, ITEM, FLUID, GENERATOR, CELL).covers(emptyFactory,emptyFactory,emptyFactory,emptyFactory,emptyFactory, COVER_DYNAMO_OLD).setAllowVerticalFacing(false).setOutputCover(COVER_DYNAMO_OLD).setTile(BlockEntityCoveredGenerator::new);
    public static GeneratorMachine SEMIFLUID_GENERATOR = new GeneratorMachine(Ref.ID, "semifluid_generator").frontCovers().allowFrontIO().setMap(SEMIFLUID_FUELS).setTiers(LV).addFlags(GUI, ITEM, FLUID, GENERATOR, CELL).covers(emptyFactory,emptyFactory,emptyFactory,emptyFactory,emptyFactory, COVER_DYNAMO_OLD).setAllowVerticalFacing(false).setOutputCover(COVER_DYNAMO_OLD).setTile(BlockEntityCoveredGenerator::new);
    public static GeneratorMachine WINDMILL = new GeneratorMachine(Ref.ID, "windmill").setTiers(ULV).covers(emptyFactory,emptyFactory,emptyFactory,emptyFactory,emptyFactory, COVER_DYNAMO_OLD).allowFrontIO().setAllowVerticalFacing(false).setOutputCover(COVER_DYNAMO_OLD).setTile(BlockEntityCoveredGenerator::new);
    public static GeneratorMachine WATERMILL = new GeneratorMachine(Ref.ID, "watermill").setTiers(ULV).covers(emptyFactory,emptyFactory,emptyFactory,emptyFactory,emptyFactory, COVER_DYNAMO_OLD).allowFrontIO().setAllowVerticalFacing(false).setOutputCover(COVER_DYNAMO_OLD).setTile(BlockEntityCoveredGenerator::new).custom();

    public static TankMachine INFINITE_STEAM = new TankMachine(Ref.ID, "infinite_steam").addFlags(FLUID, CELL, GUI).setTiers(LV).setTile(BlockEntityInfiniteFluid::new);
    public static BasicMachine BATTERY_BUFFER_ONE = new BasicMachine(Ref.ID, "1x_battery_buffer").noCovers().addFlags(GUI, ENERGY, ITEM).setTile(BlockEntityBatteryBuffer::new).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).allowFrontIO().setAllowVerticalFacing(true);
    public static BasicMachine BATTERY_BUFFER_FOUR = new BasicMachine(Ref.ID, "4x_battery_buffer").noCovers().addFlags(GUI, ENERGY, ITEM).setTile(BlockEntityBatteryBuffer::new).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).allowFrontIO().setAllowVerticalFacing(true);
    public static BasicMachine BATTERY_BUFFER_EIGHT = new BasicMachine(Ref.ID, "8x_battery_buffer").noCovers().addFlags(GUI, ENERGY, ITEM).setTile(BlockEntityBatteryBuffer::new).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).allowFrontIO().setAllowVerticalFacing(true);
    public static BasicMachine TRANSFORMER = new BasicMachine(Ref.ID, "transformer").addFlags(ENERGY).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).setTile((m, p, s) -> new BlockEntityTransformer<>(m, p, s, 1)).noCovers().setAllowVerticalFacing(true).allowFrontIO();
    public static BasicMachine SUPERCONDENSATOR = new BasicMachine(Ref.ID, "supercondensator").addFlags(ENERGY).setTile((m, p, s) -> new BlockEntitySupercondensator(m, p, s, 1)).setTiers(LUV).noCovers().setAllowVerticalFacing(true).allowFrontIO();
    public static BasicMachine TRANSFORMER_DIGITAL = new BasicMachine(Ref.ID, "transformer_digital").addFlags(GUI, ENERGY).setTiers(EV, IV).setTile(BlockEntityDigitalTransformer::new).noCovers().allowFrontIO();

    public static void init() {
        STEAM_TURBINE.setOutputCover(COVER_DYNAMO_OLD);
        GAS_TURBINE.setOutputCover(COVER_DYNAMO_OLD);
        DIESEL_GENERATOR.setOutputCover(COVER_DYNAMO_OLD);
        SEMIFLUID_GENERATOR.setOutputCover(COVER_DYNAMO_OLD);
        WINDMILL.setOutputCover(COVER_DYNAMO_OLD);
        WATERMILL.setOutputCover(COVER_DYNAMO_OLD);
        ENERGY.remove(HEAT_EXCHANGER);
        ENERGY.remove(DUSTBIN);
    }
}
