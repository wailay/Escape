/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique;

import Level.LevelManager;
import Level.Map;
import escape.Fenetre;
import org.newdawn.slick.Graphics;

/**
 *
 * @author wail
 */
public class Camera {
    
    
    
    
    public void render(Graphics g, Map map){
        
                     g.translate(Fenetre.offsetX - map.getPlayer().getX(), Fenetre.offsetY- map.getPlayer().getY());
    }
}
