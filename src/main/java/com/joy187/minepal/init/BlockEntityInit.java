package com.joy187.minepal.init;

import com.joy187.minepal.Main;
import com.joy187.minepal.block.blockentity.IncubatorBlockEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockEntityInit {
    public static final DeferredRegister<TileEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Main.MOD_ID);

    public static final RegistryObject<TileEntityType<IncubatorBlockEntity>> INCUBATOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("incubator_block_entity", () ->
                    TileEntityType.Builder.of(IncubatorBlockEntity::new,
                            BlockInit.INCUBATOR_BLOCK.get()).build(null));

    //    public static final RegistryObject<BlockEntityType<GeneratorBlockEntity>> GENERATOR_BLOCK_ENTITY =
//            BLOCK_ENTITIES.register("generator_block_entity", () ->
//                    BlockEntityType.Builder.of(GeneratorBlockEntity::new,
//                            BlockInit.GENERATOR_BLOCK.get()).build(null));
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
