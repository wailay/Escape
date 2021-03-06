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

/**
 *
 * @author Wail
 */
public class SqueletonEntity extends Entity {

    public SqueletonEntity(float x, float y) throws SlickException {
        super(x, y);
        maxHP = 350;
        hitPoints = 350;
        this.width = 45;
        this.height = 45;
        this.hitBoxWidth = 30;
        this.hitBoxHeight = 30;
        this.damage = 20;

        this.anim = new Animation[8];
        createEntity("res/text/enemy/squeleton.png", width, height);
    }

    @Override
    public void createEntity(String path, int tw, int th) throws SlickException {

        squeleton = new SpriteSheet(path, width, height);
        anim[0] = AnimationLoader.loadAnimation(squeleton, 4, 6, 0, 100);
        anim[1] = AnimationLoader.loadAnimation(squeleton, 0, 3, 1, 100);
        anim[2] = AnimationLoader.loadAnimation(squeleton, 4, 6, 1, 100);
        anim[3] = AnimationLoader.loadAnimation(squeleton, 0, 3, 0, 100);
        //dead
        anim[4] = AnimationLoader.loadAnimation(squeleton, 0, 8, 3, 100);
        
        //attack
        anim[5] = AnimationLoader.loadAnimation(squeleton, 0, 3, 2, 100);
        anim[6] = AnimationLoader.loadAnimation(squeleton, 7, 4, 2, 100);
        //transform
        anim[7] = AnimationLoader.loadAnimation(squeleton, 1, 6, 5, 100);
    }

    @Override
    public void render(Graphics g) throws SlickException {
        super.render(g);
        if (dead()) {
            moving = false;
            
              g.drawAnimation(anim[4], x, y);
             
            anim[4].stopAt(7);
            

        } else {
            g.drawAnimation(anim[direction + (dead() ? 4 : 0)], x, y);
        }
    }

    @Override
    public void update(int d, Map map, Player player) {
        super.update(d, map, player);
    }

}
