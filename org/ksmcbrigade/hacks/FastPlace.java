package org.ksmcbrigade.hacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.nio.file.Files;

public class FastPlace extends Hack {

    public FastPlace() {
        super("FastPlace",false, Category.BLOCK);
        this.keyCode = Keyboard.KEY_B;
    }

    @Override
    public void update(){
        Minecraft.getMinecraft().rightClickDelayTimer = 0;
    }
}
