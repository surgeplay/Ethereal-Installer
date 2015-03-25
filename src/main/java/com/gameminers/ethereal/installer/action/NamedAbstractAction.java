package com.gameminers.ethereal.installer.action;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public abstract class NamedAbstractAction extends AbstractAction{
    public NamedAbstractAction(String name) {
        this.putValue(NAME, name);
    }
}
