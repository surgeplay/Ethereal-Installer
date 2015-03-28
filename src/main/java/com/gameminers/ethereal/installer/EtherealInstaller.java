/*
 *  Ethereal Installer
 *  Copyright (C) 2015 Aesen Vismea and Falkreon
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gameminers.ethereal.installer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.gameminers.ethereal.installer.action.ApplicationActions;
import com.gameminers.ethereal.lib.Dialogs;
import com.gameminers.ethereal.lib.Directories;
import com.gameminers.ethereal.lib.Frames;
import com.gameminers.ethereal.lib.Resources;
import com.gameminers.system_specific.ProcessList;
import com.google.gson.Gson;

public class EtherealInstaller {
	private static File minecraftDirectory;
	public static JFrame window;
	public static final Gson gson = new Gson();
	public static void main(String[] args) {
		minecraftDirectory = Directories.getAppData("minecraft");
		
		boolean launchReady = true;
		
		if (ProcessList.isRunning("Minecraft")) {
            Dialogs.showErrorDialog(window, "Can't edit a running Minecraft instance. Please close the launcher and try again.",
                    new IllegalStateException("Can't edit a running Minecraft instance."));
            launchReady = false;
        }
        
        
        File launcherJar = new File(minecraftDirectory, "launcher.jar");
        if (!launcherJar.exists()) {
            Dialogs.showErrorDialog(window, "Can't locate the Minecraft launcher. Are you sure Minecraft is installed?",
                    new FileNotFoundException("Can't locate the Minecraft launcher."));
            launchReady = false;
        }
		
		
		if (launchReady) {
    		Frames.initLAF();
    		window = Frames.create("Installer");
    		window.addWindowListener(new WindowAdapter(){
    		    @Override
    		    public void windowClosing(WindowEvent e) {
    		        System.exit(0); // TODO
    		    }
    		});
    		
    		window.setJMenuBar(createMenuBar());
    		
    		JPanel container = new ModpackTileContainer();
    		
    		JScrollPane scroller = new JScrollPane(container);
    		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    		window.add(scroller, BorderLayout.CENTER);
    		
    		/*
    		ModDefinition farragoMod = new ModDefinition("farrago", "1.0");
    		farragoMod.displayName = "Farrago";
    		farragoMod.description = "A confused variety of random things, from blocks, to items, to entities. The point is there is no point.";
    		farragoMod.setImage(Resources.getPNGAsset("iface/farrago"));
    		*/
    		DataFile file = new DataFile(); //Necessary in case of IO error
    		try {
    		    file = DataFile.fromStream( ClassLoader.getSystemResourceAsStream("assets/json/distributed.json") );
    		} catch (IOException ex) {
    		    System.out.println("There was a problem loading the data file.");
    		    ex.printStackTrace();
    		}
    		
    		/*
    		try {
    		    FileOutputStream out = new FileOutputStream("out.json");
    		    file.toStream(out);
    		    out.close();
    		} catch(Exception ex) {
    		    ex.printStackTrace();
    		}*/
            
            
    		
    		/*
    		try {
    		    //ByteArrayOutputStream stream = new ByteArrayOutputStream();
    		    file.toStream(System.out);
    		    //byte[] output = stream.toByteArray();
    		    //System.out.println("Generated "+output.length+" bytes.");
    		    //System.out.println(Arrays.toString(output));
    		} catch (Throwable t) { t.printStackTrace();}
    		*/
    		
    		Modpack fondue = new Modpack();
    		fondue.name = "Fondue";
    		fondue.backgroundColor = new Color(255, 224, 12);
    		fondue.textColor = new Color(98, 91, 47);
    		
    		Modpack ata = new Modpack();
    		ata.name = "After the Apocalypse";
    		ata.backgroundColor = new Color(106, 38, 111);
    		ata.textColor = new Color(178, 134, 181);
    		
    		container.add(new ModpackTile(fondue));
    		container.add(new ModpackTile(ata));
    		Component glue = Box.createVerticalGlue();
    		container.add(glue);
    		
    		window.setVisible(true);
		}
	}
	
	private static JMenuBar createMenuBar() {
		JMenuBar bar = new JMenuBar();
		bar.add(createHelpMenu());
		return bar;
	}


	private static JMenu createHelpMenu() {
		JMenu menu = new JMenu("Help");
		menu.add(ApplicationActions.ABOUT);
		//menu.add(Components.createAboutDialogMenuItem(window, "Installer"));
		return menu;
	}
}
