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
 * @author 1529565
 */
public class SpiderEntity extends Entity{

    public SpiderEntity(float x, float y) throws SlickException {
        super(x,y);
         
        this.maxHP = 200;
        
       this.hitPoints = 200;
        this.width = 35;
        this.height = 35;
          this.hitBoxWidth = 30 ;
        this.hitBoxHeight = 30;
        this.damage = 15;
        
        
        this.anim = new Animation[8];
        createEntity("res/text/enemy/spider.png", width,height);
    }
    
     @Override
   public void createEntity(String path, int tw, int th) throws SlickException{
       
        spider = new SpriteSheet(path, width, height);
        anim[0] = AnimationLoader.loadAnimation(spider, 0, 5, 2,100);
        anim[1] = AnimationLoader.loadAnimation(spider, 0, 5, 3, 100);
        anim[2] = AnimationLoader.loadAnimation(spider, 0, 5, 0, 100);
        anim[3] = AnimationLoader.loadAnimation(spider, 0, 5, 1, 100);
        //dead
        anim[4] = AnimationLoader.loadAnimation(spider, 6, 7, 2, 100);
        anim[5] = AnimationLoader.loadAnimation(spider, 6, 7, 3, 100);
        anim[6] = AnimationLoader.loadAnimation(spider, 6, 7, 0, 100);
        anim[7] = AnimationLoader.loadAnimation(spider, 6, 7, 1, 100);
        
        
        
        
   }

    @Override
    public void render(Graphics g) throws SlickException {
        super.render(g); 
        if(dead()){
            moving = false;
            
            anim[direction+(dead() ? 4:0)].draw(x, y);
            
        }
        g.drawAnimation(anim[direction + (dead() ? 4:0)], x, y);
    }
   
   
   
    @Override
    public void update(int d, Map map,Player player) {
        super.update(d, map,player);
    }
   
    
}
