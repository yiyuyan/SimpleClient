package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;

public class AutoSpring extends Hack {

    public AutoSpring() {
        super("AutoSpring",false, Category.MOVE);
    }

    @Override
    public void update(){
        if(Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.setSprinting(true);
        }
    }
}
