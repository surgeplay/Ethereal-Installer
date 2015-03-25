package com.gameminers.ethereal.installer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gameminers.ethereal.installer.Modpack;

public class ModpackTile extends JPanel {
    private static final long serialVersionUID = -5086794744494699892L;
    private static final Font tileFont = new Font("Arial", Font.BOLD, 16);
    
    private Modpack data;
    
    public ModpackTile(Modpack data) {
        this.setLayout(null);
        this.data = data;
        this.setBackground(data.backgroundColor);
        this.setForeground(data.textColor);
        this.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(4, 4, 0, 4),
                        BorderFactory.createMatteBorder(2, 2, 2, 2, getForeground())
                        )
                );
        
        this.setMinimumSize(new Dimension(200,100));
        this.setMaximumSize(new Dimension(Short.MAX_VALUE,100));
        this.setPreferredSize(new Dimension(Short.MAX_VALUE,100));
        
        JLabel nameLabel = new JLabel(data.name);
        nameLabel.setForeground(data.textColor);
        this.add(nameLabel);
        nameLabel.setLocation(10, 10);
        nameLabel.setSize(400,20);
        nameLabel.setFont(tileFont);
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(data.backgroundColor);
        Insets insets = this.getInsets();
        g.fillRect(insets.left, insets.top, this.getWidth()-insets.right-insets.left, this.getHeight()-insets.bottom-insets.top);
        this.paintBorder(g);
        this.paintChildren(g);
        //g.setColor(data.backgroundColor.darker().darker());
        //g.drawRect(0, 0, this.getWidth()-1, 99);
        //g.setColor(data.textColor);
        //g.drawString(data.name, 8, 24);
    }
    
    
}
