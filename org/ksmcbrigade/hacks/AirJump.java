package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;

public class AirJump extends Hack {

    public AirJump() {
        super("AirJump",false, Category.MOVE);
    }

    @Override
    public void update(){
        if(Minecraft.getMinecraft().player!=null && Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()){
            Minecraft.getMinecraft().player.jump();
        }
    }
}
