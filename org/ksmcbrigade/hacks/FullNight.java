package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;
import org.lwjgl.input.Keyboard;

public class FullNight extends Hack {

    float gamma = 0;

    public FullNight() {
        super("FullNight",false, Category.RENDER);
        this.keyCode = Keyboard.KEY_C;
    }

    @Override
    public void enabled(){
        this.gamma = Minecraft.getMinecraft().gameSettings.gammaSetting;
    }

    @Override
    public void disabled(){
        Minecraft.getMinecraft().gameSettings.gammaSetting = this.gamma;
    }

    @Override
    public void update(){
        Minecraft.getMinecraft().gameSettings.gammaSetting = 3000;
    }
}
