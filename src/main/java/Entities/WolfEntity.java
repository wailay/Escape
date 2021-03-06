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
public class WolfEntity extends Entity{

    public WolfEntity(float x, float y) throws SlickException {
        super(x,y);
        this.maxHP = 450;
        
       this.hitPoints = 450;
        this.width = 36;
        this.height = 36;
          this.hitBoxWidth = 30 ;
        this.hitBoxHeight = 30;
        this.damage = 30;
        
        
        this.anim = new Animation[9];
        createEntity("res/text/enemy/wolf.png", width,height);
    }
    
     @Override
   public void createEntity(String path, int tw, int th) throws SlickException{
       
        wolf = new SpriteSheet(path, width, height);
        anim[0] = AnimationLoader.loadAnimationEnY(wolf, 0, 3, 3, 100);
        anim[1] = AnimationLoader.loadAnimationEnY(wolf, 0, 3, 2, 100);
        anim[2] = AnimationLoader.loadAnimationEnY(wolf, 0, 3, 1, 100);
        anim[3] = AnimationLoader.loadAnimationEnY(wolf, 0, 3, 0, 100);
        //dead
        anim[4] = AnimationLoader.loadAnimation(wolf, 4, 8, 1, 300);
        
        //attack
        anim[8] = AnimationLoader.loadAnimation(wolf, 0, 3, 0, 100);
        
        
   }

    @Override
    public void render(Graphics g) throws SlickException {
        super.render(g); 
        
        if(dead()){
            moving = false;
            g.drawAnimation(anim[4], x, y);
            
            anim[4].stopAt(3);
        
            
        }
        else g.drawAnimation(anim[direction + (dead() ? 4:0)], x, y);
        
    }
   
   
   
    @Override
    public void update(int d, Map map,Player player) {
        super.update(d, map,player);
    }
   
   
    
}
