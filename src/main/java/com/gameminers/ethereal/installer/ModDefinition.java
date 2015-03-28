package com.gameminers.ethereal.installer;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class ModDefinition {
    private String modId = "NOPE";
    public String displayName = "Unnamed";
    public String description = "";
    private String version = "";
    public String category = "Misc";
    public String fileName = null;
    public String url = null;
    private String imageData = null;
    private transient BufferedImage image = null;
    private transient Integer hashCache = null;
    
    @SuppressWarnings("unused")
    private ModDefinition() {}
    
    public ModDefinition(String id, String version) {
        modId = id;
        this.version = version;
    }
    
    public String getId() {
        return modId;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setId(String id) {
        modId = id;
        hashCache = null;
    }
    
    public void setVersion(String ver) {
        version = ver;
        hashCache = null;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
        if (image!=null) {
            this.imageData = ImageURL.create(image);
        } else {
            this.imageData = null;
        }
    }
    
    public BufferedImage getImage() {
        if (this.image==null && this.imageData!=null) {
            this.image = ImageURL.decode(this.imageData);
        }
        
        return this.image;
    }
    
    /**
     * Two ModDefinitions are considered equal if they refer to the same modId and version.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ModDefinition)) return false;
        
        ModDefinition def = (ModDefinition)other;
        
        return
           def.modId.equals(modId) &&
           def.version.equals(version);
    }
    
    /**
     * ModDefinition's implementation of HashCode is contractually consistent with its equals method.
     */
    @Override
    public int hashCode() {
        if (hashCache==null) hashCache = (modId+version).hashCode();
        return hashCache;
    }
}
