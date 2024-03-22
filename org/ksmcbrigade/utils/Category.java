package org.ksmcbrigade.utils;

import org.ksmcbrigade.hacks.Hack;

import java.util.ArrayList;

public enum Category{
    BLOCK(new ArrayList<>()),
    COMBAT(new ArrayList<>()),
    MOVE(new ArrayList<>()),
    RENDER(new ArrayList<>()),
    MISC(new ArrayList<>());

    private ArrayList<Hack> list;

    Category(ArrayList<Hack> list) {
        this.list = list;
    }

    public ArrayList<Hack> getList() {
        return list;
    }

    public void setList(ArrayList<Hack> list){
        this.list = list;
    }

    public Hack get(String name){
        for(Hack hack:this.list){
            if(hack.name.equals(name)){
                return hack;
            }
        }
        return null;
    }
}
