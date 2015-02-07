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
