package com.joy187.minepal.init;

import com.joy187.minepal.Main;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleInit {

    public static final DeferredRegister<ParticleType<?>> PARTICLE =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Main.MOD_ID);

    public static final RegistryObject<BasicParticleType> SPARK =
            PARTICLE.register("spark", () -> new BasicParticleType(true));

    public static final RegistryObject<BasicParticleType> SPARK_PURPLE =
            PARTICLE.register("spark_purple", () -> new BasicParticleType(true));

    public static final RegistryObject<BasicParticleType> SPARK_RED =
            PARTICLE.register("spark_red", () -> new BasicParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE.register(eventBus);
    }
}
