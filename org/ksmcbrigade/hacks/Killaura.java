package org.ksmcbrigade.hacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.CPacketPlayer;
import org.ksmcbrigade.utils.Category;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class Killaura extends Hack {

    File config = new File("config/hacks/autoClick.json");
    double distance = 5;

    public Killaura() {
        super("Killaura",false, Category.COMBAT);
        this.keyCode = Keyboard.KEY_R;
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
        if(MC.world!=null && MC.player!=null && MC.playerController!=null && MC.getConnection()!=null){
            for(Entity entity:MC.world.loadedEntityList.stream().
                    filter(e -> e instanceof EntityLivingBase)
                    .filter(e -> !e.isDead && ((EntityLivingBase) e).getHealth()>0)
                    .filter(e -> e.getDistanceSq(MC.player.posX,MC.player.posY,MC.player.posZ) <= distance)
                    .filter(e -> e!=MC.player)
                    .collect(Collectors.toList())){
                float[] yp = getAngleToEntity(entity,MC.player);
                MC.player.rotationYaw = yp[0];
                MC.player.prevRotationPitch = yp[1];
                MC.getConnection().sendPacket(new CPacketPlayer.PositionRotation(MC.player.posX,MC.player.posY,MC.player.posZ,yp[0],yp[1],MC.player.onGround));
                MC.playerController.attackEntity(MC.player,entity);
            }
        }
    }

    public float[] getAngleToEntity(Entity targetEntity, EntityPlayerSP player) {
        double dX = targetEntity.posX - player.posX;
        double dY = targetEntity.posY - player.posY;
        double dZ = targetEntity.posZ - player.posZ;
        double distanceXZ = Math.sqrt(dX * dX + dZ * dZ);
        float yaw = (float) Math.toDegrees(Math.atan2(dZ, dX)) - 90.0F;
        float pitch = (float) -Math.toDegrees(Math.atan2(dY, distanceXZ));
        return new float[] {yaw, pitch};
    }
}
