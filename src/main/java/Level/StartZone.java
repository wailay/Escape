/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Wail
 */
public class StartZone extends Map{
    
    public StartZone(GameContainer gc) throws SlickException {
        super(gc);
        tiledMap = loadMap("res/text/Maps/startzone.tmx");
        
    }   
    
   
    
    @Override
    public void render(Graphics g, Map map){
        super.render(g, map);
       
    }
}
