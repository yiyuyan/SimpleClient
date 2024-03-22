package org.ksmcbrigade.hacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.ksmcbrigade.utils.Category;

import java.io.File;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class AutoClick extends Hack {

    File config = new File("config/hacks/autoClick.json");
    double distance = 5;

    public AutoClick() {
        super("AutoClick",false, Category.COMBAT);
    }

    @Override
    public void enabled() throws Exception {
        if(!config.exists()){
            JsonObject object = new JsonObject();
            object.addProperty("distance",5D);
            Files.write(config.toPath(),object.toString().getBytes());
        }
        distance = new JsonParser().parse(new String(Files.readAllBytes(config.toPath()))).getAsJsonObject().get("distance").getAsDouble();
    }

    @Override
    public void update(){
        Minecraft MC = Minecraft.getMinecraft();
        if(MC.world!=null && MC.player!=null && MC.playerController!=null){
            for(Entity entity:MC.world.loadedEntityList.stream().
                    filter(e -> e instanceof EntityLivingBase)
                    .filter(e -> !e.isDead && ((EntityLivingBase) e).getHealth()>0)
                    .filter(e -> e.getDistanceSq(MC.player.posX,MC.player.posY,MC.player.posZ) <= distance)
                    .filter(e -> e!=MC.player)
                    .collect(Collectors.toList())){
                MC.playerController.attackEntity(MC.player,entity);
            }
        }
    }
}
