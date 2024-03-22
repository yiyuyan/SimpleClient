package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;

public class NoGravity extends Hack {

    public NoGravity() {
        super("NoGravity",false, Category.MOVE);
    }

    @Override
    public void disabled(){
        if(Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.setNoGravity(false);
        }
    }

    @Override
    public void update(){
        if(Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.setNoGravity(true);
        }
    }
}
