package org.ksmcbrigade.hacks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.text.TextComponentString;
import org.ksmcbrigade.utils.Category;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class XRay extends Hack {

    public static Block[] blocks = new Block[]{Blocks.COAL_ORE, Blocks.COAL_BLOCK, Blocks.IRON_ORE, Blocks.IRON_BLOCK,
            Blocks.GOLD_ORE, Blocks.GOLD_BLOCK, Blocks.LAPIS_ORE,
            Blocks.LAPIS_BLOCK, Blocks.REDSTONE_ORE, Blocks.LIT_REDSTONE_ORE,
            Blocks.REDSTONE_BLOCK, Blocks.DIAMOND_ORE, Blocks.DIAMOND_BLOCK,
            Blocks.EMERALD_ORE, Blocks.EMERALD_BLOCK, Blocks.QUARTZ_ORE,
            Blocks.LAVA, Blocks.MOB_SPAWNER, Blocks.PORTAL, Blocks.END_PORTAL,
            Blocks.END_PORTAL_FRAME};

    boolean fullNight = false;

    Hack FullNight;

    public XRay() {
        super("XRay",false, Category.RENDER);
        this.keyCode = Keyboard.KEY_X;
    }

    @Override
    public void enabled(){
        FullNight = Category.RENDER.get("FullNight");
        if (FullNight != null && !FullNight.enabled) {
            FullNight.setEnabled(true);
            fullNight = true;
        }
        if(Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.addChatMessage(new TextComponentString("Please reload the ResourceManager(F3+T)."));
        }
    }

    @Override
    public void disabled(){
        if(fullNight){
            FullNight.setEnabled(false);
        }
        if(Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.addChatMessage(new TextComponentString("Please reload the ResourceManager(F3+T)."));
        }
    }
}
