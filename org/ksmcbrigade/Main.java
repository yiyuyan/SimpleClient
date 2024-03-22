package org.ksmcbrigade;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ksmcbrigade.gui.MainGUI;
import org.ksmcbrigade.hacks.Hack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import static org.ksmcbrigade.utils.Utils.addHacks;
import static org.ksmcbrigade.utils.Utils.hacks;

public class Main {

    public static String version = "dev-"+System.nanoTime();
    public static Logger LOGGER = LogManager.getLogger();

    public static Minecraft MC = Minecraft.getMinecraft();
    public static KeyBinding guiKey = new KeyBinding("SimpleClientScreen",184,"SimpleClient");

    public static File file = new File("config/SimpleEnabledHacks.json");
    public static boolean tick = false;

    public static void init() throws IOException {

        addHacks();

        new File("config").mkdirs();
        new File("config/hacks").mkdirs();
        if(!file.exists()){
            Files.write(file.toPath(),new JsonObject().toString().getBytes());
        }

        if(hacks!=null){
            tick = true;
            for(Map.Entry<String, JsonElement> key: new JsonParser().parse(new String(Files.readAllBytes(file.toPath()))).getAsJsonObject().entrySet()){
                for(Hack hack:hacks){
                    if(hack.name.equals(key.getKey())){
                        hack.setEnabled(true);
                    }
                }
            }
        }
    }

    public static void KeyInput(int keyCode){
        if(keyCode==guiKey.getKeyCode()){
            if(!MainGUI.init){
                if(MainGUI.THIS!=null){
                    MainGUI.THIS.dispose();;
                }
                new MainGUI(-1,-1);
            }
        }
        for(Hack hack:hacks){
            if(hack.keyCode!=-1 && keyCode==hack.keyCode){
                hack.setEnabled(!hack.enabled);
            }
        }
    }

    public static void tick() throws Exception {
        if(tick){
            for(Hack hack:hacks){
                if(hack.enabled){
                    hack.update();
                }
            }
        }
    }

    public static void renderTick() throws Exception{
        if(tick){
            for(Hack hack:hacks){
                if(hack.enabled){
                    hack.render();
                }
            }
        }
    }
}
