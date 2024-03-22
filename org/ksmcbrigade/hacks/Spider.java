package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;

public class Spider extends Hack {

    public Spider() {
        super("Spider",false, Category.MOVE);
    }

    @Override
    public void update(){
        if(Minecraft.getMinecraft().player!=null){
            if(!Minecraft.getMinecraft().player.isCollidedHorizontally){
                return;
            }

            if(Minecraft.getMinecraft().player.motionY<0.2){
                Minecraft.getMinecraft().player.motionY=0.2;
            }
        }
    }
}
