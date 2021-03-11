package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends GameObject{

    private Handler handler;
    private Random r = new Random();

    public EnemyBossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = (r.nextInt(5 - -5 + 1) -5);
        velY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y >= Game.HEIGHT) handler.removeObject(this);

        handler.addObject(new Trail(x, y, Color.red, 16, 16, 0.1f, ID.Trail, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }
}
