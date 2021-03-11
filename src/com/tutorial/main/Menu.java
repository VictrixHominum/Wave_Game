package com.tutorial.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.Font.BOLD;

public class Menu extends MouseAdapter {

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void tick(){
    }

    public void render(Graphics g){
        Font fnt = new Font("arial", BOLD, 50);
        Font fnt2 = new Font("arial", BOLD, 30);

        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Menu", 255, 80);

        g.setFont(fnt2);
        g.drawRect(215,100, 200, 64);
        g.drawString("Play", 285, 143);

        g.setColor(Color.white);
        g.drawRect(215,200, 200, 64);
        g.drawString("Help", 285, 243);

        g.setColor(Color.white);
        g.drawRect(215,300, 200, 64);
        g.drawString("Quit", 285, 343);
    }
}
