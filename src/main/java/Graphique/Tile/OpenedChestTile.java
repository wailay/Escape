/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique.Tile;

import Level.Map;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author wail
 */
public class OpenedChestTile extends Tile{
    
    public OpenedChestTile(TiledMap map) {
        super(map);
        this.id = 5;
    }
     @Override
    public int getId(){
        
        return id;
    }

    @Override
    public boolean isChestOpened() {
        return super.isChestOpened(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
