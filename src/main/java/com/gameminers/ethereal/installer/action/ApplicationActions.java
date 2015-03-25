package com.gameminers.ethereal.installer.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JDialog;

import com.gameminers.ethereal.installer.EtherealInstaller;
import com.gameminers.ethereal.lib.Dialogs;

@SuppressWarnings("serial")
public class ApplicationActions {
    public static Action ABOUT = new NamedAbstractAction("About…") {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            JDialog dialog = Dialogs.createAboutDialog(EtherealInstaller.window, "Installer");
            dialog.setVisible(true);
        }
    };
    
}
