package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;
import org.lwjgl.input.Keyboard;

public class NoClip extends Hack {

    public NoClip() {
        super("NoClip",false, Category.MOVE);
        this.keyCode = Keyboard.KEY_P;
    }

    @Override
    public void disabled(){
        if(Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.noClip = false;
        }
    }

    @Override
    public void update(){
        if(Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.noClip = true;
        }
    }
}
