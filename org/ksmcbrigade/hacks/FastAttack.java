package org.ksmcbrigade.hacks;

import net.minecraft.client.Minecraft;
import org.ksmcbrigade.utils.Category;

public class FastAttack extends Hack {

    public FastAttack() {
        super("FastAttack",false, Category.COMBAT);
    }

    @Override
    public void update(){
        Minecraft.getMinecraft().leftClickCounter = 0;
    }
}
