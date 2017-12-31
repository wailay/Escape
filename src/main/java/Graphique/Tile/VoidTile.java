/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique.Tile;

import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author wail
 */
public class VoidTile extends Tile{

    public VoidTile(TiledMap map) {
        super(map);
        this.id = 22;
    }
    
    
    @Override
    public int getId(){
        
        return id;
    }
}
