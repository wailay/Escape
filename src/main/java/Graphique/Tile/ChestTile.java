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
public class ChestTile extends Tile {

    public boolean opened;

    public ChestTile(TiledMap map) {
        super(map);

        this.id = 4;
    }

    @Override
    public int getId() {

        return id;
    }

    public boolean isOpened() {
        return opened;
    }

}
