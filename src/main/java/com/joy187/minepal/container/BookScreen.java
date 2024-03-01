package com.joy187.minepal.container;


import com.joy187.minepal.Main;
import com.joy187.minepal.entity.*;
import com.joy187.minepal.init.EntityInit;
import com.joy187.minepal.item.book.ChangePageButton;
import com.joy187.minepal.item.book.EntityDisplay;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

import static com.joy187.minepal.entity.Pal.*;

public class BookScreen extends Screen {

    protected static final int X = 256;
    protected static final int Y = 256;
    protected ItemStack book;
    private static final ResourceLocation TEXTURE=
            new ResourceLocation(Main.MOD_ID, "textures/gui/book.png");
    private static final ResourceLocation BREAK=
            new ResourceLocation(Main.MOD_ID, "textures/gui/to.png");

    private static final ResourceLocation FIRE=
            new ResourceLocation(Main.MOD_ID, "textures/gui/fire.png");
    private static final ResourceLocation PLANT=
            new ResourceLocation(Main.MOD_ID, "textures/gui/plant.png");
    private static final ResourceLocation CHOP=
            new ResourceLocation(Main.MOD_ID, "textures/gui/chop.png");
    private static final ResourceLocation LIGHT=
            new ResourceLocation(Main.MOD_ID, "textures/gui/light.png");
    private static final ResourceLocation MINE=
            new ResourceLocation(Main.MOD_ID, "textures/gui/mine.png");
    private static final ResourceLocation FARM=
            new ResourceLocation(Main.MOD_ID, "textures/gui/farm.png");
    private static final ResourceLocation FREEZE=
            new ResourceLocation(Main.MOD_ID, "textures/gui/freeze.png");
    private static final ResourceLocation MEDICINE=
            new ResourceLocation(Main.MOD_ID, "textures/gui/medicine.png");
    private static final ResourceLocation HAND=
            new ResourceLocation(Main.MOD_ID, "textures/gui/hand.png");


    private static final ResourceLocation WORKINGFLOW =
            new ResourceLocation(Main.MOD_ID, "textures/gui/workingflow.png");


    public int bookPages;
    public int bookPagesTotal = 8;
    public ChangePageButton previousPage;
    public ChangePageButton nextPage;
    public ChangePageButton toIntroPage;
    public ChangePageButton toIncuPage;
    public ChangePageButton toOccuPage;
    public ChangePageButton toPassivePage;

    private EntityDisplay entityDisplay = new EntityDisplay();

    public BookScreen()
    {
        super(new TranslationTextComponent("text.book.title"));
        this.book = book;
    }

    public BookScreen(ItemStack book) {
        super(new TranslationTextComponent("book_gui"));
        this.book = book;
    }

    public void render(MatrixStack pMatrixStack, int mouseX, int mouseY, float delta) {
        renderBackground(pMatrixStack);

        //background.draw(matrixStack, partialTicks, (int) bookHeight, (float) backgroundScale);
        //RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        //RenderSystem.setShaderTexture(0, TEXTURE);
        this.minecraft.getTextureManager().bind(TEXTURE);
        int cornerX = (this.width - X) / 2;
        int cornerY = (this.height - Y) / 2;
        blit(pMatrixStack, cornerX, cornerY, 0, 0, X, Y, 256, 256);
        //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //this.getMinecraft().getTextureManager().bind(TEXTURE);
        //this.blit(pMatrixStack,book_left,book_top,book_width,window_height,book_width,window_height);
        //this.blit(pMatrixStack, i, 2, 0, 0, 192, 192);
        //renderTooltip(pMatrixStack, mouseX, mouseY);
        if (this.bookPages>=0) {
            drawPerPage(pMatrixStack, this.bookPages);
            int pageLeft = bookPages * 2 + 1;
            int pageRight = pageLeft + 1;
//	            font.draw(pMatrixStack, "" + pageLeft, book_left,book_top, 0X303030);
//	            font.draw(pMatrixStack, "" + pageRight, book_left,book_top, 0X303030);
            font.draw(pMatrixStack, "" + pageLeft, cornerX, cornerY - (int) (Y * 0.13), 0X303030);
            font.draw(pMatrixStack, "" + pageRight, cornerX, cornerY - (int) (Y * 0.13), 0X303030);
        }

        super.render(pMatrixStack, mouseX, mouseY, delta);
        //System.out.println("当前页"+this.bookPages+".");
    }

    @Override
    public void init()
    {
        super.init();

        int book_width = Math.round((3f/5)*this.width);
        int up_x = this.width/2 - (book_width/2)+10;
        int next_x = this.width/2 + (book_width/2)-25;
        int both_y = Math.round(320*(float)this.height/512)+35;
        //int both_y = (int)Math.round(book_width*0.625)-10;
        //float zoom = 320*(float)this.height/512;


        int cornerX = (this.width - X) / 2;
        int cornerY = (this.height - Y) / 2+220;

        //上一页
        this.addButton(
                this.previousPage = new ChangePageButton(cornerX+20, cornerY, false, 0, (p_214132_1_) -> {
                    if(this.bookPages>0)
                        this.bookPages--;
                    else
                        this.bookPages=0;
                }));
        //下一页
        this.addButton(
                this.nextPage = new ChangePageButton(cornerX+220, cornerY, true, 0, (p_214132_1_) -> {
                    if(this.bookPages<this.bookPagesTotal)
                        this.bookPages++;
                }));

        //简介页面 0
        this.addButton(
                this.toIntroPage = new ChangePageButton(cornerX+50, cornerY, true, 0, "book.button.intro", (p_214132_1_) -> {
                    this.bookPages=0;
                }));

        //孵化表 1
        this.addButton(
                this.toIncuPage = new ChangePageButton(cornerX+90, cornerY, true, 0, "book.button.incu", (p_214132_1_) -> {
                    this.bookPages=1;
                }));

        //职业表 3
        this.addButton(
                this.toOccuPage = new ChangePageButton(cornerX+140, cornerY, true, 0, "book.button.occu", (p_214132_1_) -> {
                    this.bookPages=3;
                }));

        //被动技能表 8
        this.addButton(
                this.toPassivePage = new ChangePageButton(cornerX+50, cornerY+13, true, 0, "book.passive", (p_214132_1_) -> {
                    this.bookPages=8;
                }));
    }

    public void drawPerPage(MatrixStack ms, int bookPages) {

        LivingEntity sheep = new SheepEntity(EntityType.SHEEP,this.minecraft.level);
        LivingEntity llama = new LlamaEntity(EntityType.LLAMA,this.minecraft.level);
        LivingEntity chicken = new ChickenEntity(EntityType.CHICKEN,this.minecraft.level);
        LivingEntity cat = new CatEntity(EntityType.CAT,this.minecraft.level);
        LivingEntity cow = new CowEntity(EntityType.COW,this.minecraft.level);
        LivingEntity zombie = new ZombieEntity(EntityType.ZOMBIE,this.minecraft.level);
        LivingEntity zombievillager = new ZombieVillagerEntity(EntityType.ZOMBIE_VILLAGER,this.minecraft.level);
        LivingEntity skeleton = new SkeletonEntity(EntityType.SKELETON,this.minecraft.level);
        LivingEntity stray = new StrayEntity(EntityType.STRAY,this.minecraft.level);
        LivingEntity wither_skeleton = new WitherSkeletonEntity(EntityType.WITHER_SKELETON,this.minecraft.level);
        LivingEntity squid = new SquidEntity(EntityType.SQUID,this.minecraft.level);
        LivingEntity wolf = new WolfEntity(EntityType.WOLF,this.minecraft.level);
        LivingEntity lamball = new YouYou(EntityInit.YOUYOU.get(),this.minecraft.level);
        LivingEntity cattiva = new Cattiva(EntityInit.CATTIVA.get(),this.minecraft.level);
        LivingEntity chikipi = new Chikipi(EntityInit.CHIKIPI.get(),this.minecraft.level);
        LivingEntity foxparks = new Foxparks(EntityInit.FOXPARKS.get(),this.minecraft.level);
        LivingEntity mozzarina = new Mozzarina(EntityInit.MOZZARINA.get(),this.minecraft.level);
        LivingEntity swee = new Swee(EntityInit.SWEE.get(),this.minecraft.level);
        LivingEntity vixy = new Vixy(EntityInit.VIXY.get(),this.minecraft.level);
        LivingEntity jolthog = new Jolthog(EntityInit.JOLTHOG.get(),this.minecraft.level);
        LivingEntity tanzee = new Tanzee(EntityInit.TANZEE.get(),this.minecraft.level);
        LivingEntity depresso = new Depresso(EntityInit.DEPRESSO.get(),this.minecraft.level);
        LivingEntity rayhound = new Rayhound(EntityInit.RAYHOUND.get(),this.minecraft.level);
        LivingEntity gumoss = new Gumoss(EntityInit.GUMOSS.get(),this.minecraft.level);
        LivingEntity pengullet = new Pengullet(EntityInit.PENGULLET.get(),this.minecraft.level);
        LivingEntity eikthyrdeer = new Eikthyrdeer(EntityInit.EIKTHYRDEER.get(),this.minecraft.level);
        LivingEntity petallia = new Petallia(EntityInit.PETALLIA.get(),this.minecraft.level);

        switch (this.bookPages) {
            case 0:
                if(bookPages == 0){
                    int cornerX = (this.width - X) / 2 +5;
                    int cornerY = (this.height - Y) / 2+30;
                    ms.pushPose();
                    String text1 = I18n.get("book.intro");
                    String text2 = I18n.get("book.incu");
                    String text3 = I18n.get("book.capture");
                    String text4 = I18n.get("book.backpack");
                    String text5 = I18n.get("book.hunger");
                    font.draw(ms,text1,cornerX+10,cornerY-20, 0x000000);
                    font.draw(ms,text3,cornerX+10,cornerY-5, 0x000000);
                    font.draw(ms,text4,cornerX+10,cornerY+10, 0x000000);
                    font.draw(ms,text2,cornerX+10,cornerY+75, 0x000000);
                    font.draw(ms,text5,cornerX+10,cornerY+25, 0x000000);

                    ms.popPose();

                    drawImage(ms, WORKINGFLOW, cornerX+40, cornerY+90, 0, 0, 162, 75, 512F);

                }
            case 1:
                if (bookPages == 1) {
//                    ms.pushPose();
//                    ms.scale(2F, 2F, 2F);
                    int cornerX = (this.width - X) / 2 +5;
                    int cornerY = (this.height - Y) / 2+30;
                    int cornerX2 = cornerX + 65;
                    int cornerX3 = cornerX + 132;

                    ms.pushPose();
                    String text1 = I18n.get("block.minepal.incubator");
                    font.draw(ms,text1,cornerX+100,cornerY-20, 0x000000);
                    ms.popPose();

                    //1
                    breedingDraw(ms,lamball,lamball,lamball,cornerX,cornerY+0,0,0,1.2f);
                    breedingDraw(ms,llama,llama,lamball,cornerX,cornerY+15,0,3,1.2f);
                    breedingDraw(ms,sheep,sheep,lamball,cornerX,cornerY+30,0,6,1.2f);
                    breedingDraw(ms,cattiva,lamball,lamball,cornerX,cornerY+45,0,9,1.2f);
                    breedingDraw(ms,sheep,lamball,lamball,cornerX,cornerY+60,0,12,1.2f);
                    breedingDraw(ms,llama,lamball,lamball,cornerX,cornerY+75,0,15,1.2f);


                    breedingDraw(ms,chikipi,chikipi,chikipi,cornerX,cornerY+90,0,18,1.2f);
                    breedingDraw(ms,chicken,chicken,chikipi,cornerX,cornerY+105,0,21,1.2f);
                    breedingDraw(ms,chicken,chikipi,chikipi,cornerX,cornerY+120,0,24,1.2f);

                    breedingDraw(ms,foxparks,foxparks,foxparks,cornerX,cornerY+135,0,27,1.2f);
                    breedingDraw(ms,chikipi,swee,foxparks,cornerX,cornerY+150,0,30,1.2f);

                    //2
                    breedingDraw(ms,cattiva,cattiva,cattiva,cornerX2,cornerY+0,12,0,1.2f);
                    breedingDraw(ms,cat,cat,cattiva,cornerX2,cornerY+15,12,3,1.2f);
                    breedingDraw(ms,vixy,lamball,cattiva,cornerX2,cornerY+30,12,6,1.2f);
                    breedingDraw(ms,cat,cattiva,cattiva,cornerX2,cornerY+45,12,9,1.2f);

                    breedingDraw(ms,cow,cow,mozzarina,cornerX2,cornerY+60,12,12,1.2f);
                    breedingDraw(ms,mozzarina,mozzarina,mozzarina,cornerX2,cornerY+75,12,15,1.2f);
                    breedingDraw(ms,cow,mozzarina,mozzarina,cornerX2,cornerY+90,12,18,1.2f);

                    breedingDraw(ms,tanzee,lamball,jolthog,cornerX2,cornerY+105,12,21,1.2f);
                    breedingDraw(ms,jolthog,jolthog,jolthog,cornerX2,cornerY+120,12,24,1.2f);

                    breedingDraw(ms,depresso,jolthog,jolthog,cornerX2,cornerY+135,12,27,1.2f);
                    breedingDraw(ms,chikipi,gumoss,jolthog,cornerX2,cornerY+150,12,30,1.2f);


                    //3
                    breedingDraw(ms,depresso,depresso,depresso,cornerX3,cornerY+0,27,0,1.2f);
                    breedingDraw(ms,swee,lamball,depresso,cornerX3,cornerY+15,27,3,1.2f);

                    breedingDraw(ms,gumoss,gumoss,gumoss,cornerX3,cornerY+30,27,6,1.2f);

                    breedingDraw(ms,tanzee,tanzee,tanzee,cornerX3,cornerY+45,27,9,1.2f);
                    breedingDraw(ms,gumoss,tanzee,tanzee,cornerX3,cornerY+60,27,12,1.2f);

                    breedingDraw(ms,pengullet,pengullet,pengullet,cornerX3,cornerY+75,27,15,1.2f);
                    breedingDraw(ms,cattiva,gumoss,pengullet,cornerX3,cornerY+90,27,18,1.2f);
                    breedingDraw(ms,gumoss,lamball,pengullet,cornerX3,cornerY+105,27,21,1.2f);
                    breedingDraw(ms,cattiva,tanzee,pengullet,cornerX3,cornerY+120,27,24,1.2f);
                    breedingDraw(ms,tanzee,vixy,pengullet,cornerX3,cornerY+135,27,24,1.2f);

                    breedingDraw(ms,eikthyrdeer,eikthyrdeer,eikthyrdeer,cornerX3,cornerY+150,27,27,1.2f);


//                    ms.popPose();
                }
                break;
            case 2:
                if (bookPages == 2) {
//                    ms.pushPose();
//                    ms.scale(2F, 2F, 2F);
                    int cornerX = (this.width - X) / 2 +5;
                    int cornerY = (this.height - Y) / 2+30;
                    int cornerX2 = cornerX + 65;
                    int cornerX3 = cornerX + 132;

                    ms.pushPose();
                    String text1 = I18n.get("block.minepal.incubator");
                    font.draw(ms,text1,cornerX+100,cornerY-20, 0x000000);
                    ms.popPose();


                    //1
                    breedingDraw(ms,vixy,vixy,vixy,cornerX,cornerY+0,0,0,1.2f);
                    breedingDraw(ms,chikipi,foxparks,vixy,cornerX,cornerY+15,0,3,1.2f);

                    breedingDraw(ms,swee,swee,swee,cornerX,cornerY+30,0,6,1.2f);
                    breedingDraw(ms,gumoss,jolthog,swee,cornerX,cornerY+45,0,9,1.2f);
                    breedingDraw(ms,pengullet,tanzee,swee,cornerX,cornerY+60,0,12,1.2f);

                    breedingDraw(ms,petallia,petallia,petallia,cornerX,cornerY+75,0,15,1.2f);

//
//                    breedingDraw(ms,chikipi,chikipi,chikipi,cornerX,cornerY+90,0,18,1.2f);
//                    breedingDraw(ms,chicken,chicken,chikipi,cornerX,cornerY+105,0,21,1.2f);
//                    breedingDraw(ms,chicken,chikipi,chikipi,cornerX,cornerY+120,0,24,1.2f);
//
//                    breedingDraw(ms,foxparks,foxparks,foxparks,cornerX,cornerY+135,0,27,1.2f);
//                    breedingDraw(ms,chikipi,swee,foxparks,cornerX,cornerY+150,0,30,1.2f);
//
//                    //2
//                    breedingDraw(ms,cattiva,cattiva,cattiva,cornerX2,cornerY+0,12,0,1.2f);
//                    breedingDraw(ms,cat,cat,cattiva,cornerX2,cornerY+15,12,3,1.2f);
//                    breedingDraw(ms,vixy,lamball,cattiva,cornerX2,cornerY+30,12,6,1.2f);
//                    breedingDraw(ms,cat,cattiva,cattiva,cornerX2,cornerY+45,12,9,1.2f);
//
//                    breedingDraw(ms,cow,cow,mozzarina,cornerX2,cornerY+60,12,12,1.2f);
//                    breedingDraw(ms,mozzarina,mozzarina,mozzarina,cornerX2,cornerY+75,12,15,1.2f);
//                    breedingDraw(ms,cow,mozzarina,mozzarina,cornerX2,cornerY+90,12,18,1.2f);
//
//                    breedingDraw(ms,tanzee,lamball,jolthog,cornerX2,cornerY+105,12,21,1.2f);
//                    breedingDraw(ms,jolthog,jolthog,jolthog,cornerX2,cornerY+120,12,24,1.2f);
//
//                    breedingDraw(ms,depresso,jolthog,jolthog,cornerX2,cornerY+135,12,27,1.2f);
//                    breedingDraw(ms,chikipi,gumoss,jolthog,cornerX2,cornerY+150,12,30,1.2f);
//
//
//                    //3
                    breedingDraw(ms,wolf,wolf,rayhound,cornerX3,cornerY+0,27,0,1.2f);
                    breedingDraw(ms,rayhound,rayhound,rayhound,cornerX3,cornerY+15,27,3,1.2f);
                    breedingDraw(ms,rayhound,wolf,rayhound,cornerX3,cornerY+30,27,6,1.2f);

                    breedingDraw(ms,zombie,zombie,zombievillager,cornerX3,cornerY+45,27,9,1.2f);
                    breedingDraw(ms,skeleton,skeleton,stray,cornerX3,cornerY+60,27,12,1.2f);
                    breedingDraw(ms,skeleton,squid,wither_skeleton,cornerX3,cornerY+75,27,15,1.2f);

//                    breedingDraw(ms,cattiva,gumoss,pengullet,cornerX3,cornerY+90,27,18,1.2f);
//                    breedingDraw(ms,gumoss,lamball,pengullet,cornerX3,cornerY+105,27,21,1.2f);
//                    breedingDraw(ms,cattiva,tanzee,pengullet,cornerX3,cornerY+120,27,24,1.2f);
//                    breedingDraw(ms,tanzee,vixy,pengullet,cornerX3,cornerY+135,27,24,1.2f);
//
//                    breedingDraw(ms,eikthyrdeer,eikthyrdeer,eikthyrdeer,cornerX3,cornerY+150,27,27,1.2f);



//                    ms.popPose();
                }
                break;
            case 3:
                if (bookPages == 3) {
                    int cornerX = (this.width - X) / 2 +5;
                    int cornerY = (this.height - Y) / 2+30;
                    int cornerX2 = cornerX + 65;
                    int cornerX3 = cornerX + 132;

                    ms.pushPose();
                    String text1 = I18n.get("book.occu");
                    String text2 = I18n.get("book.plant");
                    String text3 = I18n.get("book.fire");
                    font.draw(ms,text1,cornerX+100,cornerY-20, 0x000000);
                    font.draw(ms,text2,cornerX+30,cornerY, 0x000000);
                    font.draw(ms,text3,cornerX+30,cornerY+85, 0x000000);
                    ms.popPose();

                    drawImage(ms, PLANT, cornerX+120, cornerY+3, 0, 0, 32, 32, 256F);
                    entityDisplay.draw(ms,tanzee,cornerX,cornerY+15,1.2F);
                    entityDisplay.draw(ms,chikipi,cornerX,cornerY+30,1.2F);
                    entityDisplay.draw(ms,gumoss,cornerX,cornerY+45,1.2F);


                    drawImage(ms, FIRE, cornerX+120, cornerY+190, 0, 0, 32, 32, 256F);
                    entityDisplay.draw(ms,foxparks,cornerX,cornerY+90,1.2F);

                }
            case 4:
                if (bookPages == 4) {
                    int cornerX = (this.width - X) / 2 +5;
                    int cornerY = (this.height - Y) / 2+30;
                    int cornerX2 = cornerX + 65;
                    int cornerX3 = cornerX + 132;

                    ms.pushPose();
                    String text1 = I18n.get("book.occu");
                    String text2 = I18n.get("book.chop");
                    String text3 = I18n.get("book.light");
                    font.draw(ms,text1,cornerX+100,cornerY-20, 0x000000);
                    font.draw(ms,text2,cornerX+30,cornerY, 0x000000);
                    font.draw(ms,text3,cornerX+30,cornerY+85, 0x000000);
                    ms.popPose();

                    drawImage(ms, CHOP, cornerX+120, cornerY+3, 0, 0, 32, 32, 256F);
                    entityDisplay.draw(ms,tanzee,cornerX,cornerY+15,1.2F);
                    entityDisplay.draw(ms,eikthyrdeer,cornerX,cornerY+30,1.2F);

                    drawImage(ms, LIGHT, cornerX+120, cornerY+190, 0, 0, 32, 32, 256F);
                    entityDisplay.draw(ms,jolthog,cornerX,cornerY+90,1.2F);
                    entityDisplay.draw(ms,rayhound,cornerX,cornerY+105,1.2F);

                }
            case 5:
                if (bookPages == 5) {
                    int cornerX = (this.width - X) / 2 +5;
                    int cornerY = (this.height - Y) / 2+30;
                    int cornerX2 = cornerX + 65;
                    int cornerX3 = cornerX + 132;

                    ms.pushPose();
                    String text1 = I18n.get("book.occu");
                    String text2 = I18n.get("book.mine");
                    String text3 = I18n.get("book.farm");
                    font.draw(ms,text1,cornerX+100,cornerY-20, 0x000000);
                    font.draw(ms,text2,cornerX+30,cornerY, 0x000000);
                    font.draw(ms,text3,cornerX+30,cornerY+85, 0x000000);
                    ms.popPose();

                    drawImage(ms, MINE, cornerX+120, cornerY+3, 0, 0, 32, 32, 256F);
                    entityDisplay.draw(ms,cattiva,cornerX,cornerY+15,1.2F);
                    entityDisplay.draw(ms,depresso,cornerX,cornerY+30,1.2F);

                    drawImage(ms, FARM, cornerX+120, cornerY+190, 0, 0, 32, 32, 256F);
                    entityDisplay.draw(ms,vixy,cornerX,cornerY+90,1.2F);
                    entityDisplay.draw(ms,mozzarina,cornerX,cornerY+105,1.2F);
                    entityDisplay.draw(ms,lamball,cornerX,cornerY+120,1.2F);
                    entityDisplay.draw(ms,petallia,cornerX+15,cornerY+90,1.2F);

                }
            case 6:
                if (bookPages == 6) {
                    int cornerX = (this.width - X) / 2 +5;
                    int cornerY = (this.height - Y) / 2+30;
                    int cornerX2 = cornerX + 65;
                    int cornerX3 = cornerX + 132;

                    ms.pushPose();
                    String text1 = I18n.get("book.occu");
                    String text2 = I18n.get("book.freeze");
                    String text3 = I18n.get("book.medicine");
                    font.draw(ms,text1,cornerX+100,cornerY-20, 0x000000);
                    font.draw(ms,text2,cornerX+30,cornerY, 0x000000);
                    font.draw(ms,text3,cornerX+30,cornerY+85, 0x000000);
                    ms.popPose();

                    drawImage(ms, FREEZE, cornerX+120, cornerY+3, 0, 0, 32, 32, 256F);
                    entityDisplay.draw(ms,swee,cornerX,cornerY+15,1.2F);
                    entityDisplay.draw(ms,pengullet,cornerX,cornerY+30,1.2F);
//                    entityDisplay.draw(ms,depresso,cornerX,cornerY+15,1.2F);
//
                    drawImage(ms, MEDICINE, cornerX+120, cornerY+190, 0, 0, 32, 32, 256F);
                    entityDisplay.draw(ms,petallia,cornerX,cornerY+90,1.2F);
//                    entityDisplay.draw(ms,mozzarina,cornerX,cornerY+105,1.2F);
//                    entityDisplay.draw(ms,lamball,cornerX,cornerY+120,1.2F);

                }
            case 7:
                if (bookPages == 7) {
                    int cornerX = (this.width - X) / 2 +5;
                    int cornerY = (this.height - Y) / 2+30;
                    int cornerX2 = cornerX + 65;
                    int cornerX3 = cornerX + 132;

                    ms.pushPose();
                    String text1 = I18n.get("book.occu");
                    String text2 = I18n.get("book.hand");
                    //String text3 = I18n.get("book.medicine");
                    font.draw(ms,text1,cornerX+100,cornerY-20, 0x000000);
                    font.draw(ms,text2,cornerX+30,cornerY, 0x000000);
                    //font.draw(ms,text3,cornerX+30,cornerY+85, 0x000000);
                    ms.popPose();

                    drawImage(ms, HAND, cornerX+120, cornerY+3, 0, 0, 32, 32, 256F);
                    entityDisplay.draw(ms,lamball,cornerX,cornerY+15,1.2F);
                    entityDisplay.draw(ms,cattiva,cornerX,cornerY+30,1.2F);
                    entityDisplay.draw(ms,depresso,cornerX,cornerY+45,1.2F);
                    entityDisplay.draw(ms,pengullet,cornerX+15,cornerY+15,1.2F);
                    entityDisplay.draw(ms,tanzee,cornerX+15,cornerY+30,1.2F);
                    entityDisplay.draw(ms,petallia,cornerX+15,cornerY+45,1.2F);

                    //drawImage(ms, MEDICINE, cornerX+120, cornerY+190, 0, 0, 32, 32, 256F);
                    //entityDisplay.draw(ms,petallia,cornerX,cornerY+90,1.2F);
//                    entityDisplay.draw(ms,mozzarina,cornerX,cornerY+105,1.2F);
//                    entityDisplay.draw(ms,lamball,cornerX,cornerY+120,1.2F);

                }
            case 8:
                if (bookPages == 8) {
                    int cornerX = (this.width - X) / 2 +5;
                    int cornerY = (this.height - Y) / 2+30;
                    int cornerX2 = cornerX + 65;
                    int cornerX3 = cornerX + 132;

                    ms.pushPose();
                    ms.scale(0.5f,0.5f,0.5f);
                    String text1 = I18n.get("book.passive");

                    //String text3 = I18n.get("book.medicine");
                    font.draw(ms,text1,cornerX+300,cornerY-10, 0x000000);
                    int d=0;
                    for(int i=0;i<pskill.length;i++)
                    {
                        String text0 = I18n.get(pskill[i]);
                        String text2 = I18n.get(pskill[i])+":"+I18n.get("book."+pskill[i]);
                        if(text2.length()>80)
                        {
                            font.draw(ms,text2.substring(0,text0.length()),cornerX+150,cornerY+d, levelColor[Integer.parseInt(pskillLevel[i])]);
                            font.draw(ms,text2.substring(text0.length(),80),cornerX+150+text0.length()*6,cornerY+d, 0x000000);
                            d+=12;
                            font.draw(ms,text2.substring(80),cornerX+150,cornerY+d, 0x000000);
                            d+=12;
                        }
                        else{
                            font.draw(ms,text2.substring(0,text0.length()),cornerX+150,cornerY+d, levelColor[Integer.parseInt(pskillLevel[i])]);
                            font.draw(ms,text2.substring(text0.length()),cornerX+150+text0.length()*6,cornerY+d, 0x000000);
                            d+=12;
                        }
                    }

                    //font.draw(ms,text3,cornerX+30,cornerY+85, 0x000000);
                    ms.popPose();

                }
        }
    }


    public void drawImage(MatrixStack ms, ResourceLocation texture, int x, int y, int u, int v, int width, int height, float scale) {
        ms.pushPose();
        //RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        //RenderSystem.setShaderTexture(0, texture);
        this.getMinecraft().getTextureManager().bind(texture);
        ms.scale(scale / 512F, scale / 512F, scale / 512F);
        blit(ms, x, y, u, v, width, height, width, height);
        ms.popPose();
    }

    public void breedingDraw(MatrixStack ms,LivingEntity ent1,LivingEntity ent2,LivingEntity result, int x,int y,int xOffset, int yOffset, float scale) {
        entityDisplay.draw(ms,ent1,x-10,y,scale);
        font.draw(ms,"+",x+20+xOffset,y-3+yOffset, 0x000000);
        if(!(ent2 instanceof SquidEntity)) entityDisplay.draw(ms,ent2,x+10,y,scale);
        else entityDisplay.draw(ms,ent2,x+10,y-4,scale);
        font.draw(ms,"--->",x+43+xOffset,y-3+yOffset, 0x000000);
        //drawImage(ms, BREAK, cornerX+100, cornerY, 0, 0, 32, 32, 256F);
        entityDisplay.draw(ms,result,x+40,y,scale);
    }

}