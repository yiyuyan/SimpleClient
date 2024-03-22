package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;

public class NoWeb extends Hack {

    public NoWeb() {
        super("NoWeb",false, Category.BLOCK);
    }

    @Override
    public void update(){
        if(Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.isInWeb = false;
        }
    }
}
