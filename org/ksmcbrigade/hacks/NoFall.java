package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayer;
import org.ksmcbrigade.utils.Category;

public class NoFall extends Hack {

    public NoFall() {
        super("NoFall",false, Category.MOVE);
    }

    @Override
    public void update(){
        if(Minecraft.getMinecraft().player!=null){
            if(Minecraft.getMinecraft().player.fallDistance>2){
                Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayer(true));
            }
        }
    }
}
