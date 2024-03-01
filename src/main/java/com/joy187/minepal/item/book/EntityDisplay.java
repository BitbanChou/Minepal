package com.joy187.minepal.item.book;

import com.joy187.minepal.entity.Cattiva;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;

import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class EntityDisplay {

    private static final float RENDER_HEIGHT = 32;
    private static final float RENDER_WIDTH = 18;
    private static final float WIDTH = 40;
    private static final float HEIGHT = WIDTH;

    private LivingEntity entity;
    private int entityScale = 1;

    private float xOffset;
    private float yOffset;

    public void setEntity(LivingEntity entity) {
        this.entity = entity;
        updateScale();
    }

    public void draw(MatrixStack matrix, float scale) {
        if (entity != null) {
            try {
                drawEntity(matrix, (int) xOffset, (int) yOffset, entityScale, -80, -20, entity, scale);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void draw(MatrixStack matrix,LivingEntity ent, int x,int y,float scale) {
        if (ent != null) {
            try {
                drawEntity(matrix, (int) xOffset+x, (int) yOffset+y, entityScale, -80, -20, ent, scale);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateScale() {
        if (entity == null) {
            return;
        }

        int scaleY = MathHelper.ceil(RENDER_HEIGHT / entity.getBbHeight());
        int scaleX = MathHelper.ceil(RENDER_WIDTH / entity.getBbHeight());
        entityScale = Math.min(scaleX, scaleY);

        if (entity instanceof ChickenEntity) {
            entityScale *= 0.7;
        }

        if (entity instanceof VillagerEntity && entity.isSleeping()) {
            entityScale = entity.isBaby() ? 31 : 16;
        }

        xOffset = WIDTH / 2;

        yOffset = HEIGHT / 2 + RENDER_HEIGHT / 2;
        if (entity instanceof GhastEntity) {
            yOffset -= 10;
        }
    }

    /**
     * copied from InventoryScreen.drawEntity() to expose the matrixStack
     */
    public static void drawEntity(MatrixStack matrixStack2, int x, int y, int size, float mouseX,
                                  float mouseY, LivingEntity entity, float scale) {
        float f = (float) Math.atan((double) (mouseX / 40.0F));
        float g = (float) Math.atan((double) (mouseY / 40.0F));
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)x* scale, (float)y* scale, 1050.0F* scale);
        RenderSystem.scalef(8.0F, 8.0F, -1.0F);
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.pushPose();
        matrixStack.translate((double) x * scale, (double) y * scale, 1050.0D * scale);
        matrixStack.scale(8.0F, 8.0F, -1.0F);
        //RenderSystem.applyModelViewMatrix();
        matrixStack2.pushPose();
        matrixStack2.translate(0.0D, 0.0D, 1000.0D);
        matrixStack2.scale((float) size, (float) size, (float) size);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion2 = Vector3f.XP.rotationDegrees(g * 20.0F);
        quaternion.mul(quaternion2);
        matrixStack2.mulPose(quaternion);
        float h = entity.yBodyRot; // bodyYaw;
        float i = entity.yRot; // getYaw();
        float j = entity.xRot; // getPitch();
        float k = entity.yHeadRotO; // prevHeadYaw;
        float l = entity.yHeadRot; // headYaw;
        entity.yBodyRot = 180.0F + f * 20.0F;
        entity.yRot=(180.0F + f * 40.0F);
        entity.xRot=(-g * 20.0F);
        entity.yHeadRot = entity.yRot;
        entity.yHeadRotO = entity.yRot;
        //Lighting.setupForEntityInInventory();
        EntityRendererManager entityrenderermanager = Minecraft.getInstance().getEntityRenderDispatcher();
//        EntityRenderDispatcher entityrenderdispatcher =
//                Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion2.conj();
        entityrenderermanager.overrideCameraOrientation(quaternion2);
        entityrenderermanager.setRenderShadow(false);
//        entityrenderdispatcher.overrideCameraOrientation(quaternion2);
//        entityrenderdispatcher.setRenderShadow(false);
//        MultiBufferSource.BufferSource immediate =
//                Minecraft.getInstance().renderBuffers().bufferSource();
        IRenderTypeBuffer.Impl immediate = Minecraft.getInstance().renderBuffers().bufferSource();

        RenderSystem.runAsFancy(() -> {
            entityrenderermanager.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixStack2, immediate,
                    15728880);
        });

        immediate.endBatch();
//        entityrenderdispatcher.setRenderShadow(true);
        entityrenderermanager.setRenderShadow(true);

        entity.yBodyRot = h;
        entity.yRot=i;
        entity.xRot=j;
        entity.yHeadRotO = k;
        entity.yHeadRot = l;
        matrixStack.popPose();
        matrixStack2.popPose();
//        RenderSystem.applyModelViewMatrix();
//        Lighting.setupFor3DItems();
        RenderSystem.popMatrix();
    }


}
