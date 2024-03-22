package org.ksmcbrigade.hacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;

import java.io.File;
import java.nio.file.Files;

public class Timer extends Hack {

    File config = new File("config/hacks/timer.json");
    float timer = 40;

    public Timer() {
        super("Timer",false, Category.MOVE);
    }

    @Override
    public void enabled() throws Exception {
        if(!config.exists()){
            JsonObject object = new JsonObject();
            object.addProperty("timer",40F);
            Files.write(config.toPath(),object.toString().getBytes());
        }
        timer = new JsonParser().parse(new String(Files.readAllBytes(config.toPath()))).getAsJsonObject().get("timer").getAsFloat();
    }

    @Override
    public void update(){
        Minecraft.getMinecraft().timer = new net.minecraft.util.Timer(timer);
    }

    @Override
    public void disabled(){
        Minecraft.getMinecraft().timer = new net.minecraft.util.Timer(20F);
    }
}
