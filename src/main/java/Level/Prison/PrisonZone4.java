/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level.Prison;

import Level.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author 1529565
 */
public class PrisonZone4 extends Map{
    
    
    
      

    public PrisonZone4(GameContainer gc) throws SlickException {
        super(gc);
           tiledMap = loadMap("res/text/Maps/prison_salle_execution.tmx");
    }
    
    
    
   
    
    @Override
    public void render(Graphics g, Map map){
        super.render(g, map);
       
    }
    
}
