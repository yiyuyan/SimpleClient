package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import org.ksmcbrigade.utils.Category;

public class FPS extends Hack {

    public FPS() {
        super("FPS",false, Category.RENDER);
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
            Hack hack = Category.RENDER.get("XYZ");
            Hack hack2 = Category.RENDER.get("Ping");
                int y = 2;
                if(hack!=null && hack.enabled && !XYZ.hide){
                    y+=MC.fontRendererObj.FONT_HEIGHT;
                }
                if(hack2!=null && hack2.enabled){
                    y+=MC.fontRendererObj.FONT_HEIGHT;
                }
                MC.fontRendererObj.drawString("FPS: "+Minecraft.getDebugFPS(),0,y,color);
        }
    }
}
