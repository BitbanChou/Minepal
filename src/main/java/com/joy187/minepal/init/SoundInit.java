package com.joy187.minepal.init;

import com.joy187.minepal.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Main.MOD_ID);

    public static final RegistryObject<SoundEvent> ENTITY_KLEE_AMBIENT1 = build("entity.klee.ambient1");
    public static final RegistryObject<SoundEvent> ENTITY_KLEE_AMBIENT2 = build("entity.klee.ambient2");
    public static final RegistryObject<SoundEvent> ENTITY_KLEE_ATTACK1 = build("entity.klee.attack");
    public static final RegistryObject<SoundEvent> ENTITY_KLEE_ATTACK2 = build("entity.klee.attack2");
    public static final RegistryObject<SoundEvent> ENTITY_KLEE_HURT = build("entity.klee.hurt");
    public static final RegistryObject<SoundEvent> ENTITY_KLEE_BENGBENG = build("entity.klee.bengbeng");
    public static final RegistryObject<SoundEvent> ENTITY_KLEE_BENGBENG2 = build("entity.klee.bengbeng2");
    public static final RegistryObject<SoundEvent> ENTITY_KLEE_HONGHONG = build("entity.klee.honghong");
    public static final RegistryObject<SoundEvent> ENTITY_KLEE_HONGHONG2 = build("entity.klee.honghong2");
    public static final RegistryObject<SoundEvent> ENTITY_KLEE_DEATH1 = build("entity.klee.death1");
    public static final RegistryObject<SoundEvent> ENTITY_KLEE_DEATH2 = build("entity.klee.death2");
    public static final RegistryObject<SoundEvent> ENTITY_TIGA_AMBIENT1 = build("entity.tiga.ambient1");
    public static final RegistryObject<SoundEvent> ENTITY_TIGA_AMBIENT2 = build("entity.tiga.ambient2");
    public static final RegistryObject<SoundEvent> ENTITY_TIGA_ATTACK1 = build("entity.tiga.attack");
    public static final RegistryObject<SoundEvent> ENTITY_TIGA_HURT1 = build("entity.tiga.hurt1");
    public static final RegistryObject<SoundEvent> ENTITY_TIGA_HURT2 = build("entity.tiga.hurt2");
    public static final RegistryObject<SoundEvent> ENTITY_TIGA_DEATH = build("entity.tiga.death");

    public static final RegistryObject<SoundEvent> ENTITY_MEBIUS_AMBIENT1 = build("entity.mebius.ambient1");
    public static final RegistryObject<SoundEvent> ENTITY_MEBIUS_AMBIENT2 = build("entity.mebius.ambient2");
    public static final RegistryObject<SoundEvent> ENTITY_MEBIUS_ATTACK1 = build("entity.mebius.attack");
    public static final RegistryObject<SoundEvent> ENTITY_MEBIUS_HURT1 = build("entity.mebius.hurt1");
    public static final RegistryObject<SoundEvent> ENTITY_MEBIUS_HURT2 = build("entity.mebius.hurt2");
    public static final RegistryObject<SoundEvent> ENTITY_MEBIUS_DEATH = build("entity.mebius.death");
    public static final RegistryObject<SoundEvent> ENTITY_MEBIUS_SHOOT = build("entity.mebius.shoot");

    public static final RegistryObject<SoundEvent> ENTITY_LIBAI_AMBIENT1 = build("entity.libai.ambient1");
    public static final RegistryObject<SoundEvent> ENTITY_LIBAI_AMBIENT2 = build("entity.libai.ambient2");
    public static final RegistryObject<SoundEvent> ENTITY_LIBAI_AMBIENT3 = build("entity.libai.ambient3");
    public static final RegistryObject<SoundEvent> ENTITY_LIBAI_ATTACK1 = build("entity.libai.attack");
    public static final RegistryObject<SoundEvent> ENTITY_LIBAI_ATTACK2 = build("entity.libai.attack2");
    public static final RegistryObject<SoundEvent> ENTITY_LIBAI_HURT = build("entity.libai.hurt");
    public static final RegistryObject<SoundEvent> ENTITY_LIBAI_DEATH = build("entity.libai.death");
    public static final RegistryObject<SoundEvent> ENTITY_LIBAI_DEATH2 = build("entity.libai.death2");


//    private static RegistryObject<SoundEvent> build(String id)
//    {
//        return SOUNDS.register(id, () -> new SoundEvent(new ResourceLocation(Main.MOD_ID, id)));
//    }

    private static RegistryObject<SoundEvent> build(String name) {
        ResourceLocation id = new ResourceLocation(Main.MOD_ID, name);
        return SOUNDS.register(name, () -> new SoundEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
