/*
 *  Ethereal Installer
 *  Copyright (C) 2015 Aesen Vismea
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

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.gameminers.ethereal.lib.Components;
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
