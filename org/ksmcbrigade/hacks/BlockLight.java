package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import org.ksmcbrigade.utils.Category;

public class BlockLight extends Hack {

    public BlockLight() {
        super("BlockLight",false, Category.RENDER);
    }

    @Override
    public void disabled(){
        if(Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.addChatMessage(new TextComponentString("Please reload the ResourceManager(F3+T)."));
        }
    }

    @Override
    public void enabled(){
        if(Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.addChatMessage(new TextComponentString("Please reload the ResourceManager(F3+T)."));
        }
    }
}
