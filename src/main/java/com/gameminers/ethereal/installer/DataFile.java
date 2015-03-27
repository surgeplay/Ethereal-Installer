package com.gameminers.ethereal.installer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;

import com.google.gson.Gson;

public class DataFile {
    public HashSet<Modpack> modpacks = new HashSet<Modpack>();
    public HashSet<ModDefinition> mods = new HashSet<ModDefinition>();
    
    
    public static DataFile fromStream(InputStream stream) throws IOException {
        try {
            return new Gson().fromJson(new InputStreamReader(stream), DataFile.class);
        } catch (Throwable t) {
            throw new IOException("There was an error reading the JSON data.",t);
        }
    }
    
    public DataFile() {}
    
    
    public void toStream(OutputStream stream) throws IOException {
        try {
            PrintWriter pw = new PrintWriter(stream);
            new Gson().toJson(this, pw);
            pw.flush();
            pw.close();
        } catch (Throwable t) {
            throw new IOException("There was an error writing the JSON data.",t);
        }
    }
    
    /**
     * Merges two data files. If two mod or modpack entries of the same name and version are found,
     * this object's entry is overwritten for that version.
     * @param other The data file to merge on top of this one
     */
    public void merge(DataFile other) {
        for(ModDefinition def : other.mods) {
            mods.remove(def);
            mods.add(def);
        }
    }
    
}
