package com.gameminers.ethereal.installer;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

public class ModpackTileContainer extends JPanel implements Scrollable {
    private static final long serialVersionUID = -256277569487684368L;

    public ModpackTileContainer() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        if (orientation==SwingConstants.VERTICAL) return 100;
        return 16;
        //return ((orientation == SwingConstants.VERTICAL) ? visibleRect.height : visibleRect.width) - 100;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) {
        return 16;
    }

}
