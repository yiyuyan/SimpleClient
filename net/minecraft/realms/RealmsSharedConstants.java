package net.minecraft.realms;

import net.minecraft.util.ChatAllowedCharacters;
import org.ksmcbrigade.hacks.Hack;
import org.ksmcbrigade.utils.Category;

public class RealmsSharedConstants
{
    public static int NETWORK_PROTOCOL_VERSION = 335;

    static {
        Hack hack = Category.MISC.get("Update1.12.2");
        if (hack != null) {
            NETWORK_PROTOCOL_VERSION = (hack.enabled)?335:340;
        }
    }

    public static int TICKS_PER_SECOND = 20;
    public static String VERSION_STRING = "1.12";

    static {
        Hack hack = Category.MISC.get("Update1.12.2");
        if (hack != null) {
            VERSION_STRING = (hack.enabled)?"1.12.2":"1.12";
        }
    }

    public static char[] ILLEGAL_FILE_CHARACTERS = ChatAllowedCharacters.ILLEGAL_FILE_CHARACTERS;
}
