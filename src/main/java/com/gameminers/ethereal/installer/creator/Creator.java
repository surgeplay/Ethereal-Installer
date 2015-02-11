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
package com.gameminers.ethereal.installer.creator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.gameminers.ethereal.lib.Components;
import com.gameminers.ethereal.lib.Dialogs;
import com.gameminers.ethereal.lib.Directories;
import com.gameminers.ethereal.lib.Frames;
import com.google.gson.Gson;

public class Creator {
	private static File minecraftDirectory;
	public static JFrame window;
	public static final Gson gson = new Gson();
	public static void main(String[] args) {
		minecraftDirectory = Directories.getAppData("minecraft");
		
		Frames.initLAF();
		window = Frames.create("Installer");
		window.addWindowListener(new MainWindowListener());
		
		window.setJMenuBar(createMenuBar());
		
		window.setVisible(true);
		
		File launcherJar = new File(minecraftDirectory, "servers.dat");
		if (!launcherJar.exists()) {
		    Dialogs.showErrorDialog(window, "Can't locate the Minecraft launcher. Are you sure Minecraft is installed?",
		            new FileNotFoundException("Can't locate the Minecraft launcher."));
		    
		} else {
		    System.out.println("Writable: "+launcherJar.canWrite());
		    try (FileLock lock = new FileOutputStream(launcherJar).getChannel().lock()) {
		        
		        System.out.println("lock isValid?: "+lock.isValid());
		        System.out.println("OK to modify.");
		        
		    } catch (IOException ex) {
		        Dialogs.showErrorDialog(window, "Can't edit a running Minecraft instance. Please close the launcher and try again.",
	                    new FileNotFoundException("Can't edit a running Minecraft instance."));
		    }
		}
	}
	
	private static JMenuBar createMenuBar() {
		JMenuBar bar = new JMenuBar();
		bar.add(createHelpMenu());
		return bar;
	}


	private static JMenu createHelpMenu() {
		JMenu menu = new JMenu("Help");
		menu.add(Components.createAboutDialogMenuItem(window, "Installer"));
		return menu;
	}
}
