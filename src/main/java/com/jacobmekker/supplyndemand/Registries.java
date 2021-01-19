package com.jacobmekker.supplyndemand;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registries {

    public static final DeferredRegister<Block> MOD_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SupplyNDemand.MODID);
    public static final DeferredRegister<Item> MOD_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SupplyNDemand.MODID);
    public static final DeferredRegister<TileEntityType<?>> MOD_TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, SupplyNDemand.MODID);

    public static final RegistryObject<Block> MARKET_BLOCK = MOD_BLOCKS.register("market", () ->
            new MarketBlock(Block.Properties.create(Material.GLASS)));
    public static final RegistryObject<Item> MARKET_BLOCK_ITEM = MOD_ITEMS.register("market", () ->
            new BlockItem(MARKET_BLOCK.get(), new Item.Properties().group(ItemGroup.MISC)));
    public static final RegistryObject<TileEntityType<?>> MARKET_TILE_ENTITY = MOD_TILE_ENTITY_TYPES.register(
            "market", () -> TileEntityType.Builder.create(MarketTileEntity::new, Registries.MARKET_BLOCK.get())
                    .build(null));
}
