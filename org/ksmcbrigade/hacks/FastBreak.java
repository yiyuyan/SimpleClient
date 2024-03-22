package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import org.ksmcbrigade.utils.Category;
import org.lwjgl.input.Keyboard;

public class FastBreak extends Hack {

    public FastBreak() {
        super("FastBreak",false, Category.BLOCK);
        this.keyCode = Keyboard.KEY_B;
    }

    @Override
    public void update(){
        Minecraft MC = Minecraft.getMinecraft();
        MC.leftClickCounter = 0;
        if(MC.playerController!=null){
            MC.playerController.blockHitDelay = 0;
        }
    }
}
