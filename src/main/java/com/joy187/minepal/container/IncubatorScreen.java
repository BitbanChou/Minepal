package com.joy187.minepal.container;

import com.joy187.minepal.Main;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;

public class IncubatorScreen extends ContainerScreen<IncubatorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MOD_ID, "textures/gui/incubator.png");

    public IncubatorScreen(IncubatorMenu pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }
    
    @Override
    protected void renderBg(MatrixStack pMatrixStack, float pPartialTick, int pMouseX, int pMouseY) {
        //RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pMatrixStack, x, y, 0, 0, imageWidth, imageHeight);

        if(menu.isCrafting()) {
            //blit(pMatrixStack, x + 102, y + 41, 176, 0, 8, menu.getScaledProgress());
            blit(pMatrixStack, x + 81, y + 62, 176, 12-13, 14, menu.getScaledProgress());
        }
    }
    //this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 54 + 12 - k, 176, 12 - k, 14, k + 1);

    @Override
    public void render(MatrixStack pMatrixStack, int mouseX, int mouseY, float delta) {
        renderBackground(pMatrixStack);
        renderTooltip(pMatrixStack, mouseX, mouseY);
        super.render(pMatrixStack, mouseX, mouseY, delta);
    }

}
