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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.gameminers.ethereal.installer.action.ApplicationActions;
import com.gameminers.ethereal.lib.Dialogs;
import com.gameminers.ethereal.lib.Directories;
import com.gameminers.ethereal.lib.Frames;
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
