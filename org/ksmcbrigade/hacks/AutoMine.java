package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;

public class AutoMine extends Hack {

    boolean mine = false;

    public AutoMine() {
        super("AutoMine",false, Category.BLOCK);
    }

    @Override
    public void enabled(){
        mine = Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown();
    }

    @Override
    public void update(){
        Minecraft.getMinecraft().gameSettings.keyBindAttack.pressed=true;
    }

    @Override
    public void disabled(){
        Minecraft.getMinecraft().gameSettings.keyBindAttack.pressed=mine;
    }
}
