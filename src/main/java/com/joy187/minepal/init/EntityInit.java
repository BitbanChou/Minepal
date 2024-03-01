package com.joy187.minepal.init;

import com.joy187.minepal.Main;
import com.joy187.minepal.entity.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MOD_ID);

    public static final RegistryObject<EntityType<EntityPalball>> PALBALL_ENTITY = ENTITY_TYPES.register("palball",
            () -> EntityType.Builder.<EntityPalball>of(EntityPalball::new, EntityClassification.MISC).sized(0.5F, 0.5F)
                    .setTrackingRange(4).updateInterval(10).build("palball"));
    
    public static final RegistryObject<EntityType<YouYou>> YOUYOU = ENTITY_TYPES.register("youyou",
            () -> EntityType.Builder.of(YouYou::new, EntityClassification.CREATURE).sized(0.9f,1.2f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "youyou").toString()));

    public static final RegistryObject<EntityType<Cattiva>> CATTIVA = ENTITY_TYPES.register("cattiva",
            () -> EntityType.Builder.of(Cattiva::new, EntityClassification.CREATURE).sized(0.75f,1.2f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "cattiva").toString()));

    public static final RegistryObject<EntityType<Chikipi>> CHIKIPI = ENTITY_TYPES.register("chikipi",
            () -> EntityType.Builder.of(Chikipi::new, EntityClassification.CREATURE).sized(0.6f,0.8f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "chikipi").toString()));

    public static final RegistryObject<EntityType<Bullet>> BULLET = ENTITY_TYPES.register("bullet",
            () -> EntityType.Builder.<Bullet>of(Bullet::new, EntityClassification.MISC).sized(0.25F, 0.25F)
                    .setTrackingRange(4).updateInterval(10).build("bullet"));

    public static final RegistryObject<EntityType<Eikthyrdeer>> EIKTHYRDEER = ENTITY_TYPES.register("eikthyrdeer",
            () -> EntityType.Builder.of(Eikthyrdeer::new, EntityClassification.CREATURE).sized(2.0f,2.8f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "eikthyrdeer").toString()));

    public static final RegistryObject<EntityType<Foxparks>> FOXPARKS = ENTITY_TYPES.register("foxparks",
            () -> EntityType.Builder.of(Foxparks::new, EntityClassification.CREATURE).sized(0.8f,1.0f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "foxparks").toString()));

    public static final RegistryObject<EntityType<Vixy>> VIXY = ENTITY_TYPES.register("vixy",
            () -> EntityType.Builder.of(Vixy::new, EntityClassification.CREATURE).sized(0.8f,1.0f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "vixy").toString()));

    public static final RegistryObject<EntityType<Tanzee>> TANZEE = ENTITY_TYPES.register("tanzee",
            () -> EntityType.Builder.of(Tanzee::new, EntityClassification.CREATURE).sized(0.8f,1.2f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "tanzee").toString()));

    public static final RegistryObject<EntityType<Jolthog>> JOLTHOG = ENTITY_TYPES.register("jolthog",
            () -> EntityType.Builder.of(Jolthog::new, EntityClassification.CREATURE).sized(0.8f,1.2f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "jolthog").toString()));

    public static final RegistryObject<EntityType<Swee>> SWEE = ENTITY_TYPES.register("swee",
            () -> EntityType.Builder.of(Swee::new, EntityClassification.CREATURE).sized(0.8f,0.6f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "swee").toString()));

    public static final RegistryObject<EntityType<Depresso>> DEPRESSO = ENTITY_TYPES.register("depresso",
            () -> EntityType.Builder.of(Depresso::new, EntityClassification.CREATURE).sized(0.8f,1.3f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "depresso").toString()));

    public static final RegistryObject<EntityType<Mozzarina>> MOZZARINA = ENTITY_TYPES.register("mozzarina",
            () -> EntityType.Builder.of(Mozzarina::new, EntityClassification.CREATURE).sized(1.8f,1.5f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "mozzarina").toString()));

    public static final RegistryObject<EntityType<Rayhound>> RAYHOUND = ENTITY_TYPES.register("rayhound",
            () -> EntityType.Builder.of(Rayhound::new, EntityClassification.CREATURE).sized(1.2f,1.5f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "rayhound").toString()));

    public static final RegistryObject<EntityType<Pengullet>> PENGULLET = ENTITY_TYPES.register("pengullet",
            () -> EntityType.Builder.of(Pengullet::new, EntityClassification.CREATURE).sized(0.6f,1.1f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "pengullet").toString()));

    public static final RegistryObject<EntityType<Gumoss>> GUMOSS = ENTITY_TYPES.register("gumoss",
            () -> EntityType.Builder.of(Gumoss::new, EntityClassification.CREATURE).sized(0.6f,0.8f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "gumoss").toString()));

    public static final RegistryObject<EntityType<Petallia>> PETALLIA = ENTITY_TYPES.register("petallia",
            () -> EntityType.Builder.of(Petallia::new, EntityClassification.CREATURE).sized(1.5f,2.2f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "petallia").toString()));

    public static final RegistryObject<EntityType<Chillet>> CHILLET = ENTITY_TYPES.register("chillet",
            () -> EntityType.Builder.of(Chillet::new, EntityClassification.CREATURE).sized(2.0f,2.8f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "chillet").toString()));


}
