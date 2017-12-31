/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Graphique.AnimationLoader;
import Level.Map;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Circle;

/**
 *
 * @author wail
 */
public class Player2 extends Entity {

    public int width;
    public int height;
    public int radius = 20;
    public float x = 300;
    public float y = 300;
    public Rectangle box;
    private float vitesseX = 0.2f;
    private float vitesseY = 0.2f;
   

    boolean up, down, left, right;

    private SpriteSheet hero;

    private Animation[] anim = new Animation[8];

    public Player2(String path, int width, int height) throws SlickException {
        super(0, 0);
        this.width = width;
        this.height = height;
        creePlayer(path, width, height);

    }

    @Override
    public void render(Graphics g) {
        g.drawAnimation(anim[direction + (moving ? 4 : 0)], x, y);

        box = new Rectangle(x, y, width, height);
        triggerRadius = new Circle(x + (width / 2), y + (height / 2), radius);

        g.draw(box);
        g.draw(triggerRadius);
        g.destroy();
    }

    public void creePlayer(String path, int tw, int th) throws SlickException {
        hero = new SpriteSheet(path, tw, th);
        anim[0] = AnimationLoader.loadAnimation(hero, 1, 2, 0, 100);
        anim[1] = AnimationLoader.loadAnimation(hero, 1, 2, 1, 100);
        anim[2] = AnimationLoader.loadAnimation(hero, 1, 2, 2, 100);
        anim[3] = AnimationLoader.loadAnimation(hero, 1, 2, 3, 100);
        anim[4] = AnimationLoader.loadAnimation(hero, 0, 3, 0, 100);
        anim[5] = AnimationLoader.loadAnimation(hero, 0, 3, 1, 100);
        anim[6] = AnimationLoader.loadAnimation(hero, 0, 3, 2, 100);
        anim[7] = AnimationLoader.loadAnimation(hero, 0, 3, 3, 100);

    }
    
    public void update(boolean[] keys, int d, Map map) {

        move(keys[4], keys[6], keys[5], keys[7], keys[11], d, map);

    }

    
    public void die() {

    }

    public void move(boolean up, boolean down, boolean right, boolean left, boolean moving, int d, Map map) {
        this.moving = moving;

        if (this.moving) {
            

            if (up) {
                direction = 0;
                y -= vitesseY * d;
            }
            if (right) {
                direction = 1;
               x += vitesseX * d;
            }
            if (down) {
                direction = 2;
                y += vitesseY * d;
            }

            if (left) {
                direction = 3;
                 x -= vitesseX * d;
            }

            map.checkCollision(this);
            map.checkTriggerCollision(this);
           

    }
}
}

