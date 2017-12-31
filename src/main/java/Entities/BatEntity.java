/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Graphique.AnimationLoader;
import Level.Map;
import escape.Fenetre;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author wail
 */
public class BatEntity extends Entity {

    public BatEntity(float x, float y) throws SlickException {
        super(x, y);
        hitPoints = 100;
        maxHP = 100;
        damage = 10;
        anim = new Animation[8];
        this.width = hitBoxWidth = 24;
        this.height = hitBoxHeight = 24;
        createEntity("res/text/enemy/batEntity.png", width, width);
    }

    @Override
    public void createEntity(String path, int tw, int th) throws SlickException {

        bat = new SpriteSheet(path, width, height);
        anim[0] = AnimationLoader.loadAnimation(bat, 1, 4, 2, 100);
        anim[1] = AnimationLoader.loadAnimation(bat, 1, 4, 1, 100);
        anim[2] = AnimationLoader.loadAnimation(bat, 1, 4, 0, 100);
        anim[3] = AnimationLoader.loadAnimation(bat, 1, 4, 3, 100);
        //dead
        anim[4] = AnimationLoader.loadAnimation(bat, 0, 1, 2, 100);
        anim[5] = AnimationLoader.loadAnimation(bat, 0, 1, 1, 100);
        anim[6] = AnimationLoader.loadAnimation(bat, 0, 1, 0, 100);
        anim[7] = AnimationLoader.loadAnimation(bat, 0, 1, 3, 100);

    }

    @Override
    public void render(Graphics g) throws SlickException {
        super.render(g);
        if (dead()) {
            moving = false;
            
            anim[direction + (dead() ? 4 : 0)].draw(x, y);

        } else {
            g.drawAnimation(anim[direction + (dead() ? 4 : 0)], x, y);
        }

    }

    @Override
    public void update(int d, Map map, Player player) {
        super.update(d, map, player);
        if (!dead()) {

        }

    }

}
