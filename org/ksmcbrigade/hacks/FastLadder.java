package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.ksmcbrigade.utils.Category;

public class FastLadder extends Hack {

    public FastLadder() {
        super("FastLadder",false, Category.MOVE);
    }

    @Override
    public void update(){
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        if(player!=null){
            if(!player.isOnLadder() || !player.isCollidedHorizontally)
                return;
            if(player.movementInput.field_192832_b == 0
                    && player.movementInput.moveStrafe == 0)
                return;
            if(player.motionY < 0.25)
                player.motionY = 0.25;
        }
    }
}
