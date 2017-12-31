/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author wail
 */
public class AnimationLoader {
    private Animation animation;
    
    public AnimationLoader() {
    }
    /**
     * Permet d'initialiser un tableau d'animation avec des animations d'image 
     * @param spriteSheet La texture qui contient les sprite de l'entité
     * @param sx La valeur de la variable initial a incrementer
     * @param ex La valeur finale de la variable a incrementer
     * @param y La position de la colonne ou se trouve le sprite
     * @param duration La durée entre deux animations
     * @return 
     */
    public static Animation loadAnimation(SpriteSheet spriteSheet,int sx, int ex, int y, int duration){
        Animation anim = new Animation();
        
        for (int x = sx; x < ex; x++) {
            
            anim.addFrame(spriteSheet.getSprite(x, y), duration);
           
            
        }
        
        return anim;
        
    }
    public static Animation loadAnimationEnY(SpriteSheet spriteSheet,int sx, int ex, int y, int duration){
        Animation anim = new Animation();
        for (int x = sx; x < ex; x++) {
            
            anim.addFrame(spriteSheet.getSprite(y, x), duration);
           
            
        }
        
        return anim;
        
    }


}
