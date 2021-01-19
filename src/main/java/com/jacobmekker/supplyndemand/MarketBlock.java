package com.jacobmekker.supplyndemand;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MarketBlock extends Block {

    public MarketBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new MarketTileEntity();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (placer != null) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof MarketTileEntity) {
                ((MarketTileEntity) te).player = placer.getUniqueID();
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player == null || player.isCrouching()) return ActionResultType.PASS;

        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof MarketTileEntity) {
            MarketTileEntity mte = ((MarketTileEntity) te);
            if (mte.player != null) {
                PlayerEntity owner = worldIn.getPlayerByUuid(mte.player);
                if (owner != null) {
                    player.sendStatusMessage(
                            new StringTextComponent("Owner: " + owner.getDisplayName().getString()),
                            true);
                    return ActionResultType.SUCCESS;
                }
            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

}
