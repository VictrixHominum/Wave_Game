package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject{

    private final Handler handler;
    private final Random r = new Random();

    private final Color col;

    int dir = 0;

    public MenuParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        dir = r.nextInt(2);

        velX = (r.nextInt(6 - -6 + 1) -6);
        velY = (r.nextInt(6 - -6 + 1) -6);

        if(velX == 0) velX = 1;
        if(velY == 0) velY = 1;


        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 54) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        handler.addObject(new Trail(x, y, col, 16, 16, 0.1f, ID.Trail, handler));
    }

    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect(x, y, 16, 16);
    }
}
