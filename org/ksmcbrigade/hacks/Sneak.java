package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;
import org.lwjgl.input.Keyboard;

public class Sneak extends Hack {

    boolean sneak = false;

    public Sneak() {
        super("Sneak",false, Category.MOVE);
        this.keyCode = Keyboard.KEY_Z;
    }

    @Override
    public void enabled(){
        sneak = Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown();
    }

    @Override
    public void update(){
        Minecraft.getMinecraft().gameSettings.keyBindSneak.pressed=true;
    }

    @Override
    public void disabled(){
        Minecraft.getMinecraft().gameSettings.keyBindSneak.pressed=sneak;
    }
}
