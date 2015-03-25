package com.gameminers.ethereal.installer;

import java.awt.Color;

import com.gameminers.ethereal.installer.EtherealInstaller;

public class Modpack {
    public String name = "Untitled";
    public Color backgroundColor = new Color(0.8f, 0.8f, 0.8f);
    public Color textColor = new Color(0.2f, 0.2f, 0.2f);
    
    public static Modpack fromJson(String json) {
        try {
            return EtherealInstaller.gson.fromJson(json, Modpack.class);
        } catch (Exception ex) {
            Modpack result = new Modpack();
            result.name = "Error: Bad JSON";
            return result;
        }
    }
}
