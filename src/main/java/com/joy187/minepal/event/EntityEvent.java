package com.joy187.minepal.event;

import com.joy187.minepal.Main;
import com.joy187.minepal.entity.*;
import com.joy187.minepal.init.EntityInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= Main.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class EntityEvent {

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(EntityInit.CHIKIPI.get(), Chikipi.prepareAttributes().build());
        event.put(EntityInit.YOUYOU.get(), YouYou.prepareAttributes().build());
        event.put(EntityInit.CATTIVA.get(), Cattiva.prepareAttributes().build());
        event.put(EntityInit.EIKTHYRDEER.get(), Eikthyrdeer.prepareAttributes().build());
        event.put(EntityInit.FOXPARKS.get(), Foxparks.prepareAttributes().build());
        event.put(EntityInit.VIXY.get(), Vixy.prepareAttributes().build());
        event.put(EntityInit.TANZEE.get(), Tanzee.prepareAttributes().build());
        event.put(EntityInit.JOLTHOG.get(), Jolthog.prepareAttributes().build());
        event.put(EntityInit.SWEE.get(), Swee.prepareAttributes().build());
        event.put(EntityInit.DEPRESSO.get(), Depresso.prepareAttributes().build());
        event.put(EntityInit.MOZZARINA.get(), Mozzarina.prepareAttributes().build());
        event.put(EntityInit.RAYHOUND.get(), Rayhound.prepareAttributes().build());
        event.put(EntityInit.PENGULLET.get(), Pengullet.prepareAttributes().build());
        event.put(EntityInit.GUMOSS.get(), Gumoss.prepareAttributes().build());
        event.put(EntityInit.PETALLIA.get(), Petallia.prepareAttributes().build());
        event.put(EntityInit.CHILLET.get(), Chillet.prepareAttributes().build());

    }
}
