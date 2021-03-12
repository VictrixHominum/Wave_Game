package com.tutorial.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

import static java.awt.Font.BOLD;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r = new Random();

    public Menu(Game game, Handler handler) {
        this.handler = handler;
        this.game = game;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == STATE.Menu || game.gameState == STATE.Help) {

        //Play button
        if(mouseOver(mx, my, 215, 100, 200, 64)) {
            game.gameState = STATE.Game;
            handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));

            for (int i = 0; i < 1; i++) {
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.BasicEnemy, handler));
            }
        }

        //Help Button
        if(mouseOver(mx, my, 215, 200, 200, 64)) {
            game.gameState = STATE.Help;
        }

        //Back Button for Help
        if(game.gameState == STATE.Help && mouseOver(mx, my, 215,300, 200, 64)) {
            game.gameState = STATE.Menu;
            return;
        }

        //Quit Button
        if(mouseOver(mx, my, 215,300, 200, 64)) {
            System.exit(1);
        }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if(mx > x && mx < x + width) {
            return my > y && my < y + height;
        } else return false;
    }

    public void tick(){
    }

    public void render(Graphics g){

        if(game.gameState == STATE.Menu) {
            Font fnt = new Font("arial", BOLD, 50);
            Font fnt2 = new Font("arial", BOLD, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Menu", 255, 80);

            g.setFont(fnt2);
            g.drawRect(215, 100, 200, 64);
            g.drawString("Play", 285, 143);

            g.drawRect(215, 200, 200, 64);
            g.drawString("Help", 285, 243);

            g.drawRect(215, 300, 200, 64);
            g.drawString("Quit", 285, 343);
        }
        else if(game.gameState == STATE.Help) {
            Font fnt = new Font("arial", BOLD, 50);
            Font fnt2 = new Font("arial", BOLD, 30);
            Font fnt3 = new Font("arial", BOLD, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 255, 80);


            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player and dodge enemies.", 68, 175);

            g.setFont(fnt2);
            g.drawRect(215, 300, 200, 64);
            g.drawString("Back", 280, 343);
        }
    }
}
