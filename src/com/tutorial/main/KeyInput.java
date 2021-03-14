package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private final Handler handler;
    private final Game game;
    private final boolean[] keyDown = {false, false, false, false};

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player) {
                if(key == KeyEvent.VK_W) { tempObject.setVelY(-5); keyDown[0] = true; }
                if(key == KeyEvent.VK_S) { tempObject.setVelY(5); keyDown[1] = true; }
                if(key == KeyEvent.VK_A) { tempObject.setVelX(-7); keyDown[2] = true; }
                if(key == KeyEvent.VK_D) { tempObject.setVelX(7); keyDown[3] = true; }
            }
        }
        if (key == KeyEvent.VK_P && game.gameState == Game.STATE.Game) {
            Game.paused = !Game.paused;

        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player) {
                if(key == KeyEvent.VK_W) keyDown[0] = false;//tempObject.setVelY(0);
                if(key == KeyEvent.VK_S) keyDown[1] = false;//tempObject.setVelY(0);
                if(key == KeyEvent.VK_A) keyDown[2] = false;//tempObject.setVelX(0);
                if(key == KeyEvent.VK_D) keyDown[3] = false;//tempObject.setVelX(0);

                // vertical movement
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                if(!keyDown[0] && keyDown[1]) tempObject.setVelY(5);
                if(keyDown[0] && !keyDown[1]) tempObject.setVelY(-5);

                // horizontal movement
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
                if(!keyDown[2] && keyDown[3]) tempObject.setVelX(7);
                if(keyDown[2] && !keyDown[3]) tempObject.setVelX(-7);
            }
        }
    }
}
