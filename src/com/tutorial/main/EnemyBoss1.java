package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class EnemyBoss1 extends GameObject{

    private Handler handler;
    private int timer = 80;
    private int timer2 = 50;
    private Random r = new Random();

    public EnemyBoss1(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 2;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 96, 96);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(timer <= 0) velY = 0;
        else timer --;

        if(timer <= 0) timer2--;
        if(timer2 <= 0) {
            if(velX == 0) velX = 2;
            int spawn = r.nextInt(10);
            if(spawn == 0) handler.addObject(new EnemyBossBullet(x+48, y+84, ID.BasicEnemy, handler));
        }

        if(x <= 0 || x >= Game.WIDTH - 112) {
            velX *= -1;
            if (velX > 0) velX++;
            else if (velX < 0) velX--;
        }

        velX = Game.clamp(velX, -10, 10);

        handler.addObject(new Trail(x, y, Color.pink, 96, 96, 0.1f, ID.Trail, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect(x, y, 96, 96);
    }
}
