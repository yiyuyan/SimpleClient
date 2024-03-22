package org.ksmcbrigade.hacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.ksmcbrigade.Main;
import org.ksmcbrigade.gui.MainGUI;
import org.ksmcbrigade.utils.Category;

import java.io.IOException;
import java.nio.file.Files;

public abstract class Hack {

    public int keyCode = -1;
    public String name;
    public boolean enabled;
    public Category category;

    public Hack(String name,boolean enabled,Category category){
        this.name = name;
        this.enabled = enabled;
        this.category = category;
    }

    public void disabled() throws Exception{

    }

    public void enabled() throws Exception {

    }

    public void update() throws Exception{

    }

    public void setEnabled(boolean enabled) {
        boolean lastEnabled = this.enabled;
        this.enabled=enabled;
        try {
            if(enabled){
                this.enabled();
                save(true);
            }
            else{
                this.disabled();
                save(false);
            }
            if(lastEnabled!=enabled && MainGUI.init && MainGUI.THIS!=null){
                MainGUI last = MainGUI.THIS;
                MainGUI.init=false;
                last.dispose();
                new MainGUI(last.getX(),last.getY());
            }
        }
        catch (Exception e){
            System.out.println("Can't execute the enabled or the disabled function: "+e.getMessage());
        }
    }

    public int getNameLength(){
        return this.name.length();
    }

    public void save(boolean enabled) throws IOException {
        JsonObject jsonObject = new JsonParser().parse(new String(Files.readAllBytes(Main.file.toPath()))).getAsJsonObject();
        if(enabled){
            jsonObject.add(this.name,null);
        }
        else{
            try {
                jsonObject.remove(this.name);
            }
            catch (Exception e){
                System.out.println("disabled");
            }
        }
        Files.write(Main.file.toPath(),jsonObject.toString().getBytes());
    }

    public void render(){

    }
}
