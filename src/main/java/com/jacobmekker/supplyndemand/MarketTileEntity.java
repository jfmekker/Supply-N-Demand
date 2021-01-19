package com.jacobmekker.supplyndemand;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarketTileEntity extends TileEntity implements IInventory {

    private NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);
    public UUID player;

    public MarketTileEntity() {
        super(Registries.MARKET_TILE_ENTITY.get());
    }

    @SubscribeEvent
    public static void registerTE(RegistryEvent.Register<TileEntityType<?>> evt) {
        evt.getRegistry().register(Registries.MARKET_TILE_ENTITY.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putUniqueId("player", player);
        return super.write(compound);
    }

    // Read NBT
    @Override
    public void func_230337_a_(BlockState bs, CompoundNBT nbt) {
        super.func_230337_a_(bs, nbt);
        this.player = nbt.getUniqueId("player");
    }


    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        // pretend to do that
        if (player != null && world != null) {
            PlayerEntity p = world.getPlayerByUuid(player);
            if (p != null) {
                p.sendStatusMessage(
                        new StringTextComponent("Submitted item:" + stack.getItem().getTranslationKey()),
                        true);
            }
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        // ok
    }
}
