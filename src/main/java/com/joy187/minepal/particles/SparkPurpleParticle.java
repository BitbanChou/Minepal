package com.joy187.minepal.particles;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SparkPurpleParticle extends SpriteTexturedParticle {
    private final IAnimatedSprite spriteSet;

    public static SparkPurpleParticle.SparkPurpleIParticleFactory provider(IAnimatedSprite spriteSet) {
        return new SparkPurpleParticle.SparkPurpleIParticleFactory(spriteSet);
    }

    protected SparkPurpleParticle(ClientWorld world, double x, double y, double z, double vx, double vy, double vz, IAnimatedSprite spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0.2F, 0.2F);
        this.lifetime = Math.max(1, 10 + (this.random.nextInt(4) - 2));
        this.gravity = 0.0F;
        this.hasPhysics = true;
        this.xd = vx * 1.0D;
        this.yd = vy * 1.0D;
        this.zd = vz * 1.0D;
        this.setSpriteFromAge(spriteSet);
    }

    public int getLightColor(float partialTick) {
        return 15728880;
    }

    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_LIT;
    }

    public void tick() {
        super.tick();
        if (!this.removed) {
            this.setSprite(this.spriteSet.get(this.age / 1 % 7 + 1, 7));
        }

    }

    public static class SparkPurpleIParticleFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public SparkPurpleIParticleFactory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SparkPurpleParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}