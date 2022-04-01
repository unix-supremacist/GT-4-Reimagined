package trinsdar.gt4r.tile.single;

import muramasa.antimatter.Antimatter;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tile.TileEntityMachine;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TileEntityTeleporter extends TileEntityMachine<TileEntityTeleporter> {

    @Nullable
    BlockPos destination = null;
    boolean redstoneTicked = false;

    public TileEntityTeleporter(Machine<?> type) {
        super(type);
    }

    @Override
    public void load(BlockState state, CompoundTag tag) {
        super.load(state, tag);
        this.redstoneTicked = tag.getBoolean("redstoneTicked");
        if (tag.contains("destination")){
            CompoundTag pos = tag.getCompound("destination");
            destination = new BlockPos(pos.getInt("X"), pos.getInt("Y"), pos.getInt("Z"));
        }
    }


    @Override
    public CompoundTag save(CompoundTag tag) {
        super.save(tag);
        tag.putBoolean("redstoneTicked", redstoneTicked);
        if (destination == null) return tag;
        CompoundTag pos = new CompoundTag();
        pos.putInt("X", destination.getX());
        pos.putInt("Y", destination.getY());
        pos.putInt("Z", destination.getZ());
        tag.put("destination", pos);
        return tag;
    }

    @Override
    public void onBlockUpdate(BlockPos neighbor) {
        super.onBlockUpdate(neighbor);
        boolean flag = this.getLevel().hasNeighborSignal(this.getBlockPos());
        if (flag && !redstoneTicked){
            redstoneTicked = true;
            findEntityToTeleport();
        } else if (!flag && redstoneTicked){
            redstoneTicked = false;
        }

    }

    public void findEntityToTeleport(){
        List<Entity> entites = this.getLevel().getEntities(null, new AABB(this.getBlockPos().offset(-2, -3, -2), this.getBlockPos().offset(2, 3, 2)));
        if (!entites.isEmpty()){
            if (destination == null) return;
            BlockEntity teleporter = level.getBlockEntity(destination);
            if (teleporter instanceof TileEntityTeleporter){
                setMachineState(MachineState.ACTIVE);
                final double[] minimumDistance = {Double.MAX_VALUE};
                BlockPos offset = this.getBlockPos().relative(this.getFacing());
                Entity[] entityToTeleport = new Entity[]{null};
                entites.forEach(e -> {
                    if (e.getVehicle() == null && e.isAlive()){
                        double distanceSq = e.distanceToSqr(offset.getX(), offset.getY(), offset.getZ());
                        if (distanceSq >= minimumDistance[0]){
                            return;
                        }
                        minimumDistance[0] = distanceSq;
                        entityToTeleport[0] = e;
                    }
                });
                if (entityToTeleport[0] != null){
                    teleportEntity(entityToTeleport[0], (TileEntityTeleporter) teleporter);
                }
            } else {
                destination = null;
            }
        }

    }

    public void teleportEntity(Entity entity, TileEntityTeleporter teleporter){
        int baseWeight = entityWeight(entity);
        if (baseWeight == 0) return;
        Direction facing = teleporter.getFacing();
        BlockPos teleportTo = teleporter.getBlockPos().relative(facing);
        double distance = Math.sqrt(this.getBlockPos().distSqr(destination));
        int energyCost = (int)(baseWeight * Math.pow(distance + 10.0, 0.7) * 5.0);
        if (energyHandler.map(e -> e.getEnergy() >= energyCost).orElse(false)){
            energyHandler.ifPresent(e -> e.extractEnergy((int) (energyCost * AntimatterConfig.GAMEPLAY.EU_TO_FE_RATIO), false));
            entity.teleportTo(teleportTo.getX(), teleportTo.getY(), teleportTo.getZ());
        }
    }

    public int entityWeight(Entity entity){
        int totalWeight = 0;
        if (entity instanceof ItemEntity){
            ItemStack stack = ((ItemEntity)entity).getItem();
            totalWeight += (stack.getCount() / stack.getMaxStackSize()) * 100;
        }
        else if (entity instanceof Animal || entity instanceof Minecart || entity instanceof Boat){
            totalWeight += 100;
        }
        else if (entity instanceof Player){
            totalWeight += 1000;
            Inventory inventory = ((Player)entity).inventory;
            for (ItemStack stack : inventory.items) {
                if (stack.isEmpty()) continue;
                totalWeight += (stack.getCount() / stack.getMaxStackSize()) * 100;
            }
            for (ItemStack stack : inventory.armor) {
                if (stack.isEmpty()) continue;
                totalWeight += (stack.getCount() / stack.getMaxStackSize()) * 100;
            }
            ItemStack stack = inventory.player.getOffhandItem();
            if (!stack.isEmpty()){
                totalWeight += (stack.getCount() / stack.getMaxStackSize()) * 100;
            }
        }
        else if (entity instanceof Ghast){
            totalWeight += 2500;
        }
        else if (entity instanceof PathfinderMob){
            totalWeight += 500;
        }
        if (entity instanceof LivingEntity && !(entity instanceof Player)){
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ItemStack stack = ((LivingEntity)entity).getItemBySlot(slot);
                if (!stack.isEmpty()) totalWeight += (stack.getCount() / stack.getMaxStackSize()) * 100;
            }
        }
        for (Entity rider : entity.getPassengers()) {
            totalWeight += entityWeight(rider);
        }
        return totalWeight;
    }
}
