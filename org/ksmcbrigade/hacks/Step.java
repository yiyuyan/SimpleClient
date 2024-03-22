package org.ksmcbrigade.hacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;

import java.io.File;
import java.nio.file.Files;

public class Step extends Hack {

    File config = new File("config/hacks/step.json");
    float last = 0.25f;
    float timer = 10;

    public Step() {
        super("Step",false, Category.MOVE);
    }

    @Override
    public void enabled() throws Exception {
        if(Minecraft.getMinecraft().player!=null){
            last = Minecraft.getMinecraft().player.stepHeight;
        }
        if(!config.exists()){
            JsonObject object = new JsonObject();
            object.addProperty("step",10F);
            Files.write(config.toPath(),object.toString().getBytes());
        }
        timer = new JsonParser().parse(new String(Files.readAllBytes(config.toPath()))).getAsJsonObject().get("step").getAsFloat();
    }

    @Override
    public void update(){
        if(Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.stepHeight = timer;
        }
    }

    @Override
    public void disabled(){
        if(Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.stepHeight = last;
        }
    }
}
