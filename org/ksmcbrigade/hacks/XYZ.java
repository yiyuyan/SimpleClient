package org.ksmcbrigade.hacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import org.ksmcbrigade.utils.Category;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.nio.file.Files;

public class XYZ extends Hack {

    File config = new File("config/hacks/xyz.json");
    boolean shiftHide = true;

    public static boolean hide = false;

    public XYZ() {
        super("XYZ",false, Category.RENDER);
    }

    @Override
    public void enabled() throws Exception {
        if(!config.exists()){
            JsonObject object = new JsonObject();
            object.addProperty("shiftHide",shiftHide);
            Files.write(config.toPath(),object.toString().getBytes());
        }
        shiftHide = new JsonParser().parse(new String(Files.readAllBytes(config.toPath()))).getAsJsonObject().get("shiftHide").getAsBoolean();
    }

    @Override
    public void render(){
        Minecraft MC = Minecraft.getMinecraft();
        if(shiftHide && MC.gameSettings.keyBindSneak.isKeyDown()){
            hide = true;
            return;
        }
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
            MC.fontRendererObj.drawString("XYZ: "+Math.round(MC.player.posX * 1000.0) / 1000.0D+", "+Math.round(MC.player.posY * 1000.0)/1000.0D+", "+Math.round(MC.player.posZ * 1000.0) / 1000.0D,0,2,color);
            hide = false;
        }
    }
}
