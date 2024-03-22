package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.ksmcbrigade.utils.Category;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;

public class CreativeFight extends Hack {

    boolean sended = false;

    public CreativeFight() {
        super("CreativeFlight",false, Category.MOVE);
        this.keyCode = Keyboard.KEY_G;
    }

    @Override
    public void update(){
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if(player!=null){
            player.capabilities.allowFlying=true;
            if(!sended){
                player.sendPlayerAbilities();
                sended = true;
            }
        }
    }

    @Override
    public void disabled(){
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if(player!=null && !player.isCreative() && !player.isSpectator()){
            player.capabilities.allowFlying=false;
            player.capabilities.isFlying=false;
            player.sendPlayerAbilities();
        }
    }
}
