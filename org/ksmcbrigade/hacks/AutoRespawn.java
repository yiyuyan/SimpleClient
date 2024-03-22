package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.play.server.SPacketSpawnObject;
import org.ksmcbrigade.utils.Category;

public class AutoRespawn extends Hack {

    public AutoRespawn() {
        super("AutoRespawn",false, Category.COMBAT);
    }

    @Override
    public void update(){
        if(Minecraft.getMinecraft().player!=null){
            if(!Minecraft.getMinecraft().player.isEntityAlive()){
                Minecraft.getMinecraft().player.respawnPlayer();
            }
        }
    }
}
