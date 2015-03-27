package com.gameminers.ethereal.installer;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.google.gson.Gson;

public class Modpack {
    public String name = "Untitled";
    public Color backgroundColor = new Color(0.8f, 0.8f, 0.8f);
    public Color textColor = new Color(0.2f, 0.2f, 0.2f);
    private transient BufferedImage image = null;
    public String serialImage = null;
    
    public static Modpack fromJson(String json) {
        try {
            Modpack result = new Gson().fromJson(json, Modpack.class);
            if (result.serialImage!=null) result.image = ImageURL.decode(result.serialImage);
            return result;
        } catch (Exception ex) {
            Modpack result = new Modpack();
            result.name = "Error: Bad JSON";
            return result;
        }
    }
    
    /**
     * @return the image associated with this modpack, or null if no image exists.
     */
    public BufferedImage getImage() {
        if (serialImage==null) return null;
        if (image==null) image = ImageURL.decode(serialImage);
        return image;
    }
}
