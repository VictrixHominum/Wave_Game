package com.tutorial.main;

import java.awt.*;

public class SmartEnemy extends GameObject{

    private Handler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        for(int i=0; i < handler.object.size(); i++) {
            if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }


    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        int diffX = x - player.getX() - 8;
        int diffY = y - player.getY() - 8;
        float distance = (float) (Math.sqrt(Math.pow(x - player.getX(), 2) + Math.pow(y - player.getY(), 2)));

        velX = Math.round((-1/distance) * diffX) * 2;
        velY = Math.round((-1/distance) * diffY) * 2;

        if (y <= 0 || y >= Game.HEIGHT - 54) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        handler.addObject(new Trail(x, y, Color.cyan, 16, 16, 0.1f, ID.Trail, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(x, y, 16, 16);
    }
}
