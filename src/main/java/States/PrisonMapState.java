/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Entities.Entity;
import Graphique.Item.Item;
import Graphique.Tile.HelperGuyTile;
import Level.LevelManager;
import Level.Map;
import Level.Prison.PrisonMap;
import Level.Prison.PrisonZone1;
import escape.Fenetre;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

/**
 *
 * @author Wail
 */
public class PrisonMapState extends BasicGameState{

    @Override
    public int getID() {
        return Etat.PRISON;
    }
     Map map;
    PrisonMap prison;
    Transition t;
    Transition t2;
    @Override
    public void init(GameContainer gc, StateBasedGame s) throws SlickException {
       map = new Map(gc);
       prison = new PrisonMap(gc);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
         g.scale(2f, 2f);
        g.translate(Fenetre.offsetX - map.getPlayer().getX(), Fenetre.offsetY- map.getPlayer().getY());

        map.render(g, prison);
        map.getPlayer().render(g);

        for (Item item : prison.getListeItem()) {

            item.render(g);
        }
        for(Entity en : prison.getListeEntities()){
            en.render(g);
        }
        try {
            for (Item.TypeItem typeItem : LevelManager.getEquipedItem().keySet()) {
                if(typeItem == Item.TypeItem.WEAPON){
                     LevelManager.getEquipedItem().get(typeItem).renderEquipedItem(g, map.getPlayer());
                }
              
            }

        } catch (NullPointerException e) {
        }
        LevelManager.getHud().renderDialogue(g, map, HelperGuyTile.getText());
         LevelManager.getHud().render(g, map);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame s, int d) throws SlickException {
        map.getClavier().load(gc);

        map.getPlayer().update(map.getClavier().keys, d, prison, gc);
        
        for(Entity en : prison.getListeEntities()){
            en.update(d,prison,map.getPlayer());
        }
        if (prison.isPlayerTped(prison, map.getPlayer())) {
            s.enterState(Etat.PRISON_ZONE_1,t,t2);
            map.getPlayer().setX(630);
            map.getPlayer().setY(700);
            

        }
        
    }
    
}
