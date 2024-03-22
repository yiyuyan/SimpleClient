package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.ksmcbrigade.utils.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static org.ksmcbrigade.utils.Utils.hacks;

public class List extends Hack {

    public List() {
        super("List",true, Category.RENDER);
    }

    @Override
    public void render(){
        Minecraft MC = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(MC);
        int[] y = new int[]{2};
        for(Hack hack : get(hacks))
        {
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
            MC.fontRendererObj.drawString(hack.name, sr.getScaledWidth() - MC.fontRendererObj.getStringWidth(hack.name) - 2, y[0], color);
            y[0] += MC.fontRendererObj.FONT_HEIGHT;
        }
    }

     public Hack[] get(java.util.List<Hack> hacks){
        ArrayList<Hack> newHacks = new ArrayList<>();
        for(Hack hack:hacks){
            if(hack.enabled){
                newHacks.add(hack);
            }
        }
        Hack[] NewHacks = newHacks.toArray(new Hack[0]);
        Arrays.sort(NewHacks, Comparator.comparing(Hack::getNameLength).reversed());
        return NewHacks;
     }
}
