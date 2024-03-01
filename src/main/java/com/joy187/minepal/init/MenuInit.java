package com.joy187.minepal.init;

import com.joy187.minepal.Main;
import com.joy187.minepal.container.IncubatorMenu;
import com.joy187.minepal.container.PalContainer;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MenuInit {
    public static final DeferredRegister<ContainerType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, Main.MOD_ID);

//    public static final RegistryObject<ContainerType<PalContainer>> PAL_CONTAINER_MENU =
//            registerContainerType(PalContainer::new, "pal_container");
    public static final RegistryObject<ContainerType<PalContainer>> PAL_CONTAINER_MENU =
        MENUS.register("pal_container", () -> PalContainer.TYPE);

    public static final RegistryObject<ContainerType<IncubatorMenu>> INCUBATOR_MENU =
            registerContainerType(IncubatorMenu::new, "incubator_menu");


    private static <T extends Container>RegistryObject<ContainerType<T>> registerContainerType(IContainerFactory<T> factory,
                                                                                          String name) {
        return MENUS.register(name, () -> IForgeContainerType.create(factory));
    }


    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
