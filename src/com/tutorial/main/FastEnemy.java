package com.tutorial.main;

import java.awt.*;

public class FastEnemy extends GameObject{

    private Handler handler;

    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 2;
        velY = 9;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 54) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        handler.addObject(new Trail(x, y, Color.yellow, 16, 16, 0.1f, ID.Trail, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, 16, 16);
    }
}
