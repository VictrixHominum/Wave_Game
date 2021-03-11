package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

    }

    public Rectangle getBounds() {
       return new Rectangle(x, y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 1, Game.WIDTH - 51);
        y = Game.clamp(y, 1, Game.HEIGHT - 75);

        collision();
    }

    private void collision() {
        for(int i=0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 2;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }
}
