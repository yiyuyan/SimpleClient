package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import org.ksmcbrigade.utils.Category;

public class Ping extends Hack {

    public Ping() {
        super("Ping",false, Category.RENDER);
    }

    @Override
    public void render(){
        Minecraft MC = Minecraft.getMinecraft();
        if(MC.player!=null){
            Hack rainbow = Category.MISC.get("RainbowUi");
            int color = 0xFFFFFF;
            if(rainbow!=null && rainbow.enabled){
                try {
                    color = RainbowUi.colors[RainbowUi.color];
                }
                catch (ArrayIndexOutOfBoundsException e){
                    color = RainbowUi.colors[RainbowUi.color-1];
                    RainbowUi.color--;
                }
            }
            NetworkPlayerInfo info = MC.getConnection().getPlayerInfo(MC.player.getUniqueID());
            Hack hack = Category.RENDER.get("XYZ");
            if(info!=null){
                MC.fontRendererObj.drawString("Ping: "+info.getResponseTime()+" ms",0,(hack!=null && hack.enabled && !XYZ.hide)?(2+MC.fontRendererObj.FONT_HEIGHT):2,color);
            }
        }
    }
}
