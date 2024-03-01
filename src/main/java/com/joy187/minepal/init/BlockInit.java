package com.joy187.minepal.init;

import com.joy187.minepal.Main;
import com.joy187.minepal.block.AmethystBlock;
import com.joy187.minepal.block.AmethystClusterBlock;
import com.joy187.minepal.block.IncubatorBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

public class BlockInit {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = ItemInit.ITEMS;

//    public static final RegistryObject<Block> EXAMPLE_BLOCK = register("example_block",
//            () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_PURPLE).strength(5f, 6f)
//                    .sound(SoundType.METAL).requiresCorrectToolForDrops()),
//            object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Block> INCUBATOR_BLOCK = registerBlock("incubator",
            () -> new IncubatorBlock(AbstractBlock.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()),
            Main.TUTORIAL_TAB);

    public static final RegistryObject<Block> PAL_ORE_BLOCK = register("pal_ore_block",
            () -> new AmethystBlock(AbstractBlock.Properties.of(Material.DECORATION, MaterialColor.COLOR_BLUE).strength(1.5F)
                    .sound(SoundType.BASALT).requiresCorrectToolForDrops()),
            object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Block> PAL_ORE_CLUSTER = register("pal_ore_cluster",
            () -> new AmethystClusterBlock(7, 3, AbstractBlock.Properties.of(Material.DECORATION).noOcclusion().randomTicks().sound(SoundType.WART_BLOCK).strength(1.5F).lightLevel((p_152632_) -> {
        return 5; })),
            object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Main.TUTORIAL_TAB)));

    private static <T extends Block> RegistryObject<T> registerBlock(final String name,
                                                                     final Supplier<? extends T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<? extends T> block,
                                                                Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> obj = registerBlock(name, block);
        ITEMS.register(name, item.apply(obj));
        return obj;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, ItemGroup tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            ItemGroup tab) {
        return ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static Supplier<Block> createStainedGlassFromColor(DyeColor color) {
        return () -> new StainedGlassBlock(color, AbstractBlock.Properties.of(Material.GLASS, color).strength(0.3F)
                .sound(SoundType.GLASS).noOcclusion().isValidSpawn(BlockInit::never).isRedstoneConductor(BlockInit::never).isSuffocating(BlockInit::never).isViewBlocking(BlockInit::never));
    }

    public static boolean always(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    public static boolean never(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    public static boolean always(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entityType) {
        return true;
    }

    public static boolean never(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entityType) {
        return false;
    }
}
