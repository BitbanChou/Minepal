package com.joy187.minepal.container;

import com.joy187.minepal.Main;
import com.joy187.minepal.entity.Pal;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static com.joy187.minepal.entity.Pal.*;
import static com.joy187.minepal.util.PalUtils.OCCUPATION;

public class PalScreen extends ContainerScreen<PalContainer> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MOD_ID, "textures/gui/pal_container.png");
    private final Pal entity;
    private static final ITextComponent FOLLOW_TEXT = new TranslationTextComponent("gui.follow");
    private static final ITextComponent DEFAULT_TEXT = new TranslationTextComponent("gui.default");
    private Button followButton,defaultButton;

    public PalScreen(PalContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        //this.imageHeight = 166; // 修改为新的高度
        int i = 222;
        int j = 114;
        this.imageHeight = 114 + 4 * 18;
        this.inventoryLabelY = this.imageHeight - 94;
        this.entity = pMenu.pal;
    }

    @Override
    protected void init() {
        super.init();
        int buttonWidth = 100;
        int buttonHeight = 20;
        int buttonX = (this.width - buttonWidth) / 2;
        int buttonY = this.height + 30;
//        this.defaultButton = this.addRenderableWidget(new Button((width - imageWidth) / 2 - 110, (height - imageHeight) / 2, buttonWidth, buttonHeight, DEFAULT_TEXT, button -> {
//            // 处理按钮点击事件
//            //handleDefaultButtonClick();
//            this.entity.setIsFollwing(false);
//        }));
//        this.followButton = this.addRenderableWidget(new Button((width - imageWidth) / 2 - 110, (height - imageHeight) / 2 + 30, buttonWidth, buttonHeight, FOLLOW_TEXT, button -> {
//            // 处理按钮点击事件
//            this.entity.setIsFollwing(true);
//            //handleFollowButtonClick();
//        }));
//        this.followButton = this.addRenderableWidget(new Button(buttonX, buttonY, buttonWidth, buttonHeight, FOLLOW_TEXT, button -> {
//            // 处理按钮点击事件
//            handleFollowButtonClick();
//        }));
        // 根据生物当前状态更新按钮状态
        //updateButtonState();
        // 根据生物当前状态更新按钮状态

    }

//    private void updateButtonState() {
//        if (this.entity.getIsFollwing()) {
//            followButton.setMessage(new TranslationTextComponent("gui.default"));
//        } else {
//            defaultButton.setMessage(new TranslationTextComponent("gui.follow"));
//        }
//        this.entity.setIsFollwing(true);
//    }


//    private void handleDefaultButtonClick() {
//        // 处理按钮点击事件，让生物实现主人跟随
//        // 这里可以调用生物的相应方法来实现主人跟随的逻辑
//        this.entity.setIsFollwing(0);
//        //System.out.println("default!");
//    }
//
    private void handleFollowButtonClick() {
        // 处理按钮点击事件，让生物实现主人跟随
        // 这里可以调用生物的相应方法来实现主人跟随的逻辑
        this.entity.setIsFollwing(true);
        //updateButtonState();
    }


    @Override
    protected void renderBg(MatrixStack pMatrixStack, float pPartialTick, int pMouseX, int pMouseY) {
        //RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        // 往上移动整体界面
        this.blit(pMatrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight); //4 * 18 + 17);
        //this.blit(pMatrixStack, x, y + 4 * 18 + 17, 0, 126, this.imageWidth, 96);

        //this.blit(pMatrixStack, x, y, 0, 0, imageWidth, imageHeight);

    }

//    protected void renderBg(MatrixStack p_98413_, float p_98414_, int p_98415_, int p_98416_) {
//        RenderSystem.setShader(GameRenderer::getPositionTexShader);
//        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//        RenderSystem.setShaderTexture(0, CONTAINER_BACKGROUND);
//        int i = (this.width - this.imageWidth) / 2;
//        int j = (this.height - this.imageHeight) / 2;
//        this.blit(p_98413_, i, j, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
//        this.blit(p_98413_, i, j + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96);
//    }

    @Override
    protected void renderLabels(MatrixStack pMatrixStack, int pMouseX, int pMouseY) {
        this.font.draw(pMatrixStack, new TranslationTextComponent("tooltip.palname").append(entity.getName()).append(" ").append(new TranslationTextComponent("tooltip.level").append(String.valueOf(entity.calculateCurrentLevel(entity.getExp(),entity.getLevelExp())))), 8.0F, 6.0F, 4210752);
        // 可以添加其他需要渲染的标签
        //this.font.draw(pMatrixStack, new TranslationTextComponent("tooltip.palname").append(entity.getDisplayName()), 8.0F, 6.0F, 4210752);
        this.font.draw(pMatrixStack, this.title.getString(), 8.0F, (float) (this.imageHeight - 96 + 2), 4210752);

        String skills = this.entity.getPSkill();
        String[] skillArray = skills.split(" ");

        // 在屏幕上以方框文字的形式展示每个子字符串
        int labelX = -70; // 设置起始 X 坐标
        int labelY = 40; // 设置起始 Y 坐标
        int lineHeight = this.font.lineHeight+10; // 获取行高度

        for (String skill : skillArray) {
            if (!skill.equals(""))
            {
                String index = "0"; // Initialize the index to -1, indicating that the element was not found

                // Loop through the array to find the index of the searchElement
                for (int i = 0; i < pskill.length; i++) {
                    if (pskill[i].equals(skill)) {
                        index = pskillLevel[i]; // Found the element, set the index
                        break; // No need to continue the loop, break out
                    }
                }

                // 绘制方框文字
                drawStringWithBox(pMatrixStack, skill, labelX, labelY, levelColor[Integer.parseInt(index)], 0x55000000);

                // 更新下一个标签的 Y 坐标，使它们竖直排列
                labelY += lineHeight; // 可以根据需要调整间距
            }
        }

    }

    private void drawStringWithBox(MatrixStack pMatrixStack, String text, int x, int y, int color, int bgColor) {
        // 计算文本的宽度和高度
        int textWidth = 65;//font.get(text);
        int textHeight = this.font.lineHeight;//font.lineHeight;

        // 绘制背景框
        fill(pMatrixStack, x - 2, y - 2, x + textWidth + 2, y + textHeight + 2, bgColor);

        int centeredX = x + 2;
        // 计算文本居中时的起始 Y 坐标
        int centeredY = y + (textHeight + 4) / 2 - 6;

        // 绘制文本
        this.font.draw(pMatrixStack, new TranslationTextComponent(text), centeredX, centeredY, color);
    }

    @Override
    public void render(MatrixStack pMatrixStack, int mouseX, int mouseY, float delta) {
        renderBackground(pMatrixStack);
        super.render(pMatrixStack, mouseX, mouseY, delta);
        renderTooltip(pMatrixStack, mouseX, mouseY);
//        if(this.entity.getIsFollwing())
            this.font.draw(pMatrixStack, new TranslationTextComponent("tooltip.hunger").withStyle(TextFormatting.RED).append(ITextComponent.nullToEmpty(String.valueOf(this.entity.getHunger()))), (width - imageWidth) / 2 - 70, (height - imageHeight) / 2+5 , 4210752);
            this.font.draw(pMatrixStack, new TranslationTextComponent("tooltip.san").withStyle(TextFormatting.BLUE).append(ITextComponent.nullToEmpty(String.valueOf(this.entity.getSan()))), (width - imageWidth) / 2 - 70, (height - imageHeight) / 2+20 , 4210752);
            //        else
//        int d=0;
//        for(String s : this.entity.occupation)
//        {
//            this.font.draw(pMatrixStack, ITextComponent.literal(OCCUPATION.get(s)), (width - imageWidth) / 2 - 60, (height - imageHeight) / 2 + 5+d, 4210752);
//            d+=10;
//        }
//            this.font.draw(pMatrixStack, new TranslationTextComponent("✓").withStyle(TextFormatting.RED), (width - imageWidth) / 2 - 120, (height - imageHeight) / 2 + 5, 4210752);
    }

    // 你需要实现这个方法来实际绘制红色边框
    // 这通常涉及到使用Minecraft的渲染API和GlStateManager类等方法
//    private void drawRedBorder(int x, int y, int width, int height) {
//        Tesselator tessellator = Tesselator.getInstance();
//        BufferBuilder bufferBuilder = tessellator.getBuilder();
//        RenderSystem.enableBlend();
//        RenderSystem.disableTexture();
//        RenderSystem.defaultBlendFunc();
//        RenderSystem.setShader(GameRenderer::getPositionColorShader);
//        // 设置边框颜色为红色，并开启混合模式以便边框能正确显示
//        bufferBuilder.begin(VertexFormat.Mode.LINES, DefaultVertexFormat.POSITION_COLOR);
//        bufferBuilder.vertex(x, y, 0).color(1.0F, 0.0F, 0.0F, 1.0F).endVertex(); // 左上角
//        bufferBuilder.vertex(x + width, y, 0).color(1.0F, 0.0F, 0.0F, 1.0F).endVertex(); // 右上角
//        bufferBuilder.vertex(x + width, y + height, 0).color(1.0F, 0.0F, 0.0F, 1.0F).endVertex(); // 右下角
//        bufferBuilder.vertex(x, y + height, 0).color(1.0F, 0.0F, 0.0F, 1.0F).endVertex(); // 左下角
//        BufferUploader.drawWithShader(bufferBuilder.end());
//        //tessellator.draw();
//        RenderSystem.enableTexture();
//        RenderSystem.disableBlend();
//        // 重置颜色和混合模式
////        GlStateManager.disableBlend();
////        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
//    }
}