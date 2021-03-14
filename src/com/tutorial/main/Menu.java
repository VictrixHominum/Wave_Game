package com.tutorial.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;
import org.newdawn.slick.Sound;

import static java.awt.Font.BOLD;

public class Menu extends MouseAdapter {

    private final Game game;
    private final Handler handler;
    private final Random r = new Random();
    private final HUD hud;

    public Menu(Game game, Handler handler, HUD hud) {
        this.handler = handler;
        this.game = game;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        Sound click = AudioPlayer.getSound("MouseClick");

        if (game.gameState == STATE.Menu || game.gameState == STATE.Help || game.gameState == STATE.End || game.gameState == STATE.Select) {

            //Play button
            if (game.gameState == STATE.Menu && mouseOver(mx, my, 215, 100, 200, 64)) {
                game.gameState = STATE.Select;
                click.play();
                return;

            }

            //Help Button
            if (game.gameState == STATE.Menu && mouseOver(mx, my, 215, 200, 200, 64)) {
                game.gameState = STATE.Help;
                click.play();
            }

            //Back Button for Help
            if (game.gameState == STATE.Help && mouseOver(mx, my, 215, 300, 200, 64)) {
                game.gameState = STATE.Menu;
                click.play();
                return;
            }

            //Menu Button for End
            if (game.gameState == STATE.End && mouseOver(mx, my, 215, 300, 200, 64)) {
                game.gameState = STATE.Menu;
                hud.setScore(0);
                hud.setLevel(1);
                game.diff = 0;
                click.play();
                return;
            }

            //Back Button for Select
            if (game.gameState == STATE.Select && mouseOver(mx, my, 215, 300, 200, 64)) {
                game.gameState = STATE.Menu;
                click.play();
                return;
            }

            //Normal Difficulty Select
            if (game.gameState == STATE.Select && mouseOver(mx, my, 215, 100, 200, 64)) {
                click.play();
                handler.object.clear();
                game.gameState = STATE.Game;
                game.diff = 0;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, hud));
                for (int i = 0; i < 1; i++) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.BasicEnemy, handler));
                }
                return;
            }

            //Hard Difficulty Select
            if (game.gameState == STATE.Select && mouseOver(mx, my, 215, 200, 200, 64)) {
                click.play();
                game.diff = 1;
                handler.object.clear();
                game.gameState = STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, hud));
                for (int i = 0; i < 1; i++) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.HardEnemy, handler));
                }
                return;
            }


            //Quit Button
            if (game.gameState == STATE.Menu && mouseOver(mx, my, 215, 300, 200, 64)) {
                click.play();
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
        Font fnt = new Font("arial", BOLD, 50);
        Font fnt2 = new Font("arial", BOLD, 30);
        Font fnt3 = new Font("arial", BOLD, 20);

        g.setColor(Color.white);

        if(game.gameState == STATE.Menu) {

            g.setFont(fnt);
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

            g.setFont(fnt);
            g.drawString("Help", 255, 80);


            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player and dodge enemies.", 68, 175);

            g.setFont(fnt2);
            g.drawRect(215, 300, 200, 64);
            g.drawString("Back", 280, 343);
        }
        else if(game.gameState == STATE.End) {

            g.setFont(fnt);
            g.drawString("Game Over", 185, 80);


            g.setFont(fnt3);
            g.drawString("Score: " + hud.getScore(), 265, 175);

            g.setFont(fnt2);
            g.drawRect(215, 300, 200, 64);
            g.drawString("Menu", 278, 343);
        }
        else if(game.gameState == STATE.Select) {

            g.setFont(fnt);
            g.drawString("Select Difficulty", 140, 80);

            g.setFont(fnt2);
            g.drawRect(215, 100, 200, 64);
            g.drawString("Normal", 264, 143);

            g.drawRect(215, 200, 200, 64);
            g.drawString("Hard", 279, 243);

            g.drawRect(215, 300, 200, 64);
            g.drawString("Back", 278, 343);
        }
    }
}
