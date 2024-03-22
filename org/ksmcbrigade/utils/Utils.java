package org.ksmcbrigade.utils;

import net.minecraft.client.settings.KeyBinding;
import org.ksmcbrigade.hacks.Hack;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Utils {

    public static List<Hack> hacks;

    public static KeyBinding[] addKey(KeyBinding[] keys,KeyBinding key){
        KeyBinding[] keyBindings = Arrays.copyOf(keys,keys.length+1);
        keyBindings[keys.length] = key;
        return keyBindings;
    }

    public static void addHacks(){
        hacks = getSubclassesOfHack();
        for(Hack hack:hacks){
            hack.category.getList().add(hack);
        }
    }

    public static List<Hack> getSubclassesOfHack() {
        List<Hack> subclasses = new ArrayList<>();
        Package pack = Hack.class.getPackage();
        String packageName = pack.getName();
        List<Class> classes = getClassesForPackage(packageName);
        for (Class clazz : classes) {
            if (Hack.class.isAssignableFrom(clazz) && !clazz.equals(Hack.class)) {
                try {
                    subclasses.add((Hack) clazz.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return subclasses;
    }

    private static List<Class> getClassesForPackage(String packageName) {
        List<Class> classes = new ArrayList<>();
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName.replace('.', '/'));
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                if (resource.getProtocol().equals("file")) {
                    File directory = new File(resource.toURI());
                    if (directory.exists()) {
                        File[] files = directory.listFiles();
                        for (File file : files) {
                            if (file.getName().endsWith(".class")) {
                                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                                classes.add(Class.forName(className));
                            }
                        }
                    }
                } else if (resource.getProtocol().equals("jar")) {
                    JarURLConnection jarConnection = (JarURLConnection) resource.openConnection();
                    JarFile jarFile = jarConnection.getJarFile();
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        if (entry.getName().startsWith(packageName.replace('.', '/')) && entry.getName().endsWith(".class")) {
                            String className = entry.getName().replace("/", ".").substring(0, entry.getName().length() - 6);
                            classes.add(Class.forName(className));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

}
