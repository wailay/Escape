/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level.Donjon;

import Level.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author 1529565
 */
public class DungeonZone1 extends Map{
    
    
    
    
       

    public DungeonZone1(GameContainer gc) throws SlickException {
        super(gc);
        tiledMap = loadMap("res/text/Maps/map_dg2.tmx");
    }
    
    
   
    
    @Override
    public void render(Graphics g, Map map){
        super.render(g, map);
       
    }
    
}
