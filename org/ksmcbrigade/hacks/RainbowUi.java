package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class RainbowUi extends Hack {

    public static Integer[] colors;

    public static int color = 0;

    static {
        try {
            colors = getColors();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    int count = (int) (1000F / Minecraft.getMinecraft().timer.field_194149_e) * 4;

    public RainbowUi() {
        super("RainbowUi",false, Category.MISC);
    }

    @Override
    public void update(){
        if(count==0){
            count = (int) (1000F / Minecraft.getMinecraft().timer.field_194149_e) * 4;
        }
        else{

            if((color+1)>=colors.length){
                color = 0;
            }
            else{
                color++;
            }

            count--;
        }
    }

    public static Integer[] getColors() throws IllegalAccessException {
        ArrayList<Integer> colors = new ArrayList<>();
        Field[] fields = Color.class.getDeclaredFields();
        for(Field field:fields){
            if(Modifier.isStatic(field.getModifiers()) && field.getType().equals(Color.class)){
                colors.add(((Color)field.get(null)).getRGB());
            }
        }
        return colors.toArray(new Integer[0]);
    }
}
