/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Entities.Entity;
import Level.LevelManager;

import Level.Map;
import Level.Prison.PrisonZone1;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Graphique.Item.Item;
import Graphique.Tile.HelperGuyTile;
import escape.Fenetre;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;


/**
 *
 * @author Wail
 */
public class PrisonZone1State extends BasicGameState {

    Map map;
    PrisonZone1 prisonMap;
    Item item;
    Transition t,t2;
    
    @Override
    public int getID() {
        return Etat.PRISON_ZONE_1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame s) throws SlickException {
        map = new Map(gc);
        prisonMap = new PrisonZone1(gc);
       t = new FadeOutTransition();
       t2 = new FadeInTransition();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
        g.scale(2f, 2f);
        g.translate(Fenetre.offsetX - map.getPlayer().getX(), Fenetre.offsetY- map.getPlayer().getY());

        map.render(g, prisonMap);
        map.getPlayer().render(g);

        for (Item item : prisonMap.getListeItem()) {

            item.render(g);
        }
        for(Entity en : prisonMap.getListeEntities()){
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
         dialogueMap2();
        prisonMap.dbug(gc, g);

    }
    boolean executeOnce = true;
    public void dialogueMap2() throws SlickException{
        
        if(HelperGuyTile.getFois()==15){
            HelperGuyTile.setShowDialog(true);
            HelperGuyTile.setText("!!!!!!!!!!!!!!!, Qu'est ce qui est arrive. \nComment sommes-nous la");
            
        }
        if(HelperGuyTile.getFois()==16){
            HelperGuyTile.setText("Essaye de trouver quelquechose pour s'enfuir");
        }
        if(HelperGuyTile.getFois()==17){
            HelperGuyTile.setText("Voila qui peut t'aider");
           if(executeOnce){
                
                executeOnce = false;
                  item = new Item(new Vector2f(616,99));
        
              item.setId(25);
        item.getItemStats();
        prisonMap.getListeItem().add(item);
            
            }
        }
        if(HelperGuyTile.getFois()==18){
                        HelperGuyTile.setShowDialog(false);
        }
    }
   

    @Override
    public void update(GameContainer gc, StateBasedGame s, int d) throws SlickException {
        
        map.getClavier().load(gc);

        map.getPlayer().update(map.getClavier().keys, d, prisonMap, gc);
        
        
       
        for(Entity en : prisonMap.getListeEntities()){
            en.update(d,prisonMap,map.getPlayer());
        }
         if (prisonMap.isPlayerTped(prisonMap, map.getPlayer())) {
              s.enterState(Etat.PRISON,t,t2);
            map.getPlayer().setX(120);
            map.getPlayer().setY(160);
           

        }
        Etat.stateManager(s, gc, map.getClavier());
    }

}
