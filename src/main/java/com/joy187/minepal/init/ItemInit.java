package com.joy187.minepal.init;

import com.joy187.minepal.Main;
import com.joy187.minepal.item.*;
import com.joy187.minepal.item.book.ItemBook;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.function.Supplier;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static final RegistryObject<Item> PALORESHARD = register("paloreshard",
            () -> new Item(new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item> PALBALL = register("palball",
            () -> new ItemPalball(new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item> PALBALL2 = register("palball2",
            () -> new ItemPalball2(new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item> STORYBOOK = register("book",
            () -> new ItemBook(new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item> DETECTOR = register("detector",
            () -> new ItemDetector(new Item.Properties().tab(Main.TUTORIAL_TAB).stacksTo(1).durability(50)));

    public static final RegistryObject<Item> TOUCH = register("touchitem",
            () -> new ItemTouch(new Item.Properties().tab(Main.TUTORIAL_TAB).stacksTo(1).durability(50)));

    public static RegistryObject<Item> CUTKNIFE = register("cutknife",()->
    {
        return new ItemKnife(CustomItemTier.TOOL_HAGWAND, 1, -2.0F,
                new Item.Properties().tab(Main.TUTORIAL_TAB));
    });

    public static final RegistryObject<Item>  YOUYOU_SPAWN_EGG = ITEMS.register("youyou_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.YOUYOU, 0xFFFFFF, 15659506, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  CATTIVA_SPAWN_EGG = ITEMS.register("cattiva_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.CATTIVA, 14187397, 0xFFFFFF, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  CHIKIPI_SPAWN_EGG = ITEMS.register("chikipi_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.CHIKIPI, 0xFFFFFF, 13651221, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  EIKTHYRDEER_SPAWN_EGG = ITEMS.register("eikthyrdeer_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.EIKTHYRDEER, 14793625, 14760191, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  FOXPARKS_SPAWN_EGG = ITEMS.register("foxparks_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.FOXPARKS, 16206378, 16249942, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  VIXY_SPAWN_EGG = ITEMS.register("vixy_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.VIXY, 14654567, 16115111, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  TANZEE_SPAWN_EGG = ITEMS.register("tanzee_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.TANZEE, 144238144, 0xF8F8FF, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  JOLTHOG_SPAWN_EGG = ITEMS.register("jolthog_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.JOLTHOG, 0xFFFF00, 0xF8F8FF, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  SWEE_SPAWN_EGG = ITEMS.register("swee_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.SWEE, 0xFFFAF0, 0xF8F8FF, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  DEPRESSO_SPAWN_EGG = ITEMS.register("depresso_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.DEPRESSO, 0x4169E1, 0x000000, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  MOZZARINA_SPAWN_EGG = ITEMS.register("mozzarina_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.MOZZARINA, 0xFFFFFF, 0x000000, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  RAYHOUND_SPAWN_EGG = ITEMS.register("rayhound_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.RAYHOUND, 0x000000, 0xFFFF00, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  PENGULLET_SPAWN_EGG = ITEMS.register("pengullet_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.PENGULLET, 0x4169E1, 0xFFFF00, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  GUMOSS_SPAWN_EGG = ITEMS.register("gumoss_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.GUMOSS, 0xCD853F, 0x8B4513, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  PETALLIA_SPAWN_EGG = ITEMS.register("petallia_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.PETALLIA, 0x228B22, 0xFFC0CB, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item>  CHILLET_SPAWN_EGG = ITEMS.register("chillet_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.CHILLET, 0x4069E1, 0xFFFCFF, new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item> JAMBREAD = ITEMS.register("jambread", () -> new Item(new Item.Properties().
            tab(Main.TUTORIAL_TAB).food(new Food.Builder().nutrition(4).saturationMod(0.6F).
            alwaysEat().build())));

    private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
        return ITEMS.register(name, item);
    }

}
