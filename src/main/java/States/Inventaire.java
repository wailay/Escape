/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Graphique.Inventaire.Slots;
import Graphique.Item.Item;
import Level.LevelManager;
import Sound.SoundLoader;
import escape.InputHandler;

import escape.MouseArea;

import java.util.ArrayList;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import org.newdawn.slick.gui.MouseOverArea;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author 1529565
 */
public class Inventaire extends BasicGameState {

    private Image inventaireBackground;

    SoundLoader soundLoader;

    private AngelCodeFont font;
    private AngelCodeFont font2;
    private Image fontImg;

    public static ArrayList<MouseArea> itemHolder = new ArrayList<>();
    private static Rectangle descriptionHolder;
    public static ArrayList<Slots> slots = new ArrayList<>();
    private Slots weaponSlot = new Slots(104, 237, 64, 64);
    private Slots shieldSlot = new Slots(318, 239, 64, 64);
    private Slots helmetSlot = new Slots(213, 23, 64, 64);
    private Slots chestSlot = new Slots(213, 169, 64, 64);
    private Slots feetSlot = new Slots(213, 417, 64, 64);
    public static boolean weaponEquiped;
    private InputHandler input;
    private int x, y;
    private MouseOverArea mouseOverArea;

    public Inventaire() {

    }

    @Override
    public int getID() {

        return Etat.INVENTAIRE;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame s) throws SlickException {

        input = new InputHandler();

        inventaireBackground = new Image("res/text/Inventory/inventory_border_new.jpg");
        initSlots();
        fontImg = new Image("res/text/font/fontInventaire_0.png");
        font = new AngelCodeFont("res/text/font/fontInventaire.fnt", fontImg);
        font2 = new AngelCodeFont("res/text/font/fontInventaireStats.fnt", new Image("res/text/font/fontInventaireStats_0.png"));
        descriptionHolder = new Rectangle(150, 300, x, y);

    }

    @Override
    public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {

        input.load(gc);
        g.setColor(Color.transparent);

        g.drawImage(inventaireBackground, 0, 0);

        if (!LevelManager.getInventaire().isEmpty()) {

            genererItem(g, gc);
            for (MouseArea moa : itemHolder) {

                if (moa.isMouseOver()) {

                    g.setColor(new Color(37, 32, 35));
                    g.fillRect(moa.getX() + 70, moa.getY() - 75, 280, 300);

                    font.drawString(moa.getX() + 75, moa.getY() - 75, moa.getItem().getDescription(), new Color(238, 138, 37));

                }

            }

        }

        if (LevelManager.getEquipedItem().isEmpty()) {
            font2.drawString(550, 10, LevelManager.getPlayer().getBaseStats(), new Color(238, 138, 37));
        } else {
        for (MouseArea moa : itemHolder) {
            //Lorsqu'il n y a aucun item d'equiper, le equipedItem.get retourne un null, alors j'utilise un catch
            //pour attraper le null, l'exception n'affecte aucunement le code, fait juste planter le programme betement
            try {
                font2.drawString(550, 10, LevelManager.getPlayer().getStats(LevelManager.getEquipedItem().get(moa.getItem().getType())), new Color(238, 138, 37));
            } catch (NullPointerException e) {
                //ne rien faire
            }
        }
            
            
        }
       
           
       
       
        //----------------
        //-------------
    }
    boolean equipOnce = true;

    public void genererItem(Graphics g, GameContainer gc) throws SlickException {

        for (MouseArea moa : itemHolder) {

            moa.render(gc, g);

            selectDraggableItem(moa, gc);

            if (moa.isSelected()) {

                dragItem(moa);

                for (Slots slot : slots) {

                    if (!gc.getInput().isMouseButtonDown(0)) {

                        equipItem(slot, moa, gc);

                    }
                }

            }
        }

    }

    public void selectDraggableItem(MouseArea moa, GameContainer gc) {
        if (moa.isMouseOver()) {

            if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

                if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {

                    moa.setSelected(true);

                    equipOnce = true;
                }
            } else if (!gc.getInput().isMouseButtonDown(0)) {
                moa.setSelected(false);
            }

        }
    }

    public void dragItem(MouseArea moa) {
        moa.setX(input.getMouseX());
        moa.setY(input.getMouseY());
    }

    public void equipItem(Slots slot, MouseArea moa, GameContainer gc) {
        if ((moa.getX() >= slot.getMinX() && moa.getX() <= slot.getMaxX())
                && (moa.getY() >= slot.getMinY() && moa.getY() <= slot.getMaxY())) {

            for (Item item : LevelManager.getInventaire()) {
                putItemToSlot(moa, slot, item);
            }
            
                
                    if (equipOnce) {
                   
                    equipItemTypeToPlayer(moa.getItem(), slot, moa, weaponSlot, Item.TypeItem.WEAPON);
                    equipItemTypeToPlayer(moa.getItem(), slot, moa, chestSlot, Item.TypeItem.CHEST_ARMOR);
                    equipItemTypeToPlayer(moa.getItem(), slot, moa, feetSlot, Item.TypeItem.BOOT);
                    equipItemTypeToPlayer(moa.getItem(), slot, moa, helmetSlot, Item.TypeItem.HELMET);
                    equipItemTypeToPlayer(moa.getItem(), slot, moa, shieldSlot, Item.TypeItem.OFFHAND);
                    
                    equipOnce = false;

                } 
           
        }

    }

    public void equipItemTypeToPlayer(Item item, Slots slot, MouseArea moa, Slots shape, Item.TypeItem typeItem) {
        if (slot == shape) {
            
            if (!LevelManager.getEquipedItem().containsKey(typeItem)) {
                LevelManager.getEquipedItem().put(typeItem, moa.getItem());
            } else {
                LevelManager.getEquipedItem().replace(typeItem, moa.getItem());
            }
           LevelManager.getPlayer().calculateStats(moa.getItem());
           
        }
        
        if (shape.isEmpty(moa)) {
            
            LevelManager.getEquipedItem().remove(typeItem, moa.getItem());
          
              
            
        }
        
        
        
    }

    public void putItemToSlot(MouseArea moa, Slots slot, Item item) {
        if (slot == helmetSlot) {
            wrongItemSlot(slot, moa, Item.TypeItem.HELMET);

        } else if (slot == weaponSlot) {
            wrongItemSlot(slot, moa, Item.TypeItem.WEAPON);

        } else if (slot == feetSlot) {
            wrongItemSlot(slot, moa, Item.TypeItem.BOOT);

        } else if (slot == chestSlot) {
            wrongItemSlot(slot, moa, Item.TypeItem.CHEST_ARMOR);

        } else if (slot == shieldSlot) {
            wrongItemSlot(slot, moa, Item.TypeItem.OFFHAND);

        } else {

            moa.setX(slot.getX());
            moa.setY(slot.getY());
            moa.setOldX(slot.getX());
            moa.setOldY(slot.getY());
        }
    }

    public void wrongItemSlot(Slots slot, MouseArea moa, Item.TypeItem typeItem) {

        if (moa.getItem().getType() != typeItem) {
            moa.setX(moa.getOldX());
            moa.setY(moa.getOldY());
            moa.setSelected(false);
        } else {
            moa.setOldX(slot.getX());
            moa.setOldY(slot.getY());
            moa.setX(slot.getX());
            moa.setY(slot.getY());
        }

    }

    public static void addToItemHolder(GameContainer gc) throws SlickException {

        for (int i = 0; i < LevelManager.getInventaire().size(); i++) {
            if (!slots.get(i).containsItem()) {

                if (!slots.get(i).containsItem()) {
                    itemHolder.add(new MouseArea(gc, LevelManager.getInventaire().get(i).getInventaireImage(),
                            (int) slots.get(i).getX(), (int) slots.get(i).getY(), LevelManager.getInventaire().get(i).getId(), LevelManager.getInventaire().get(i)));
                    slots.get(i).setContainsItem(true);

                }

            }
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame s, int d) throws SlickException {
        Etat.stateManager(s, gc, input);

    }

    private void initSlots() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 16; j++) {

                slots.add(new Slots(j * 64, 512 + (i * 64), 64, 64));
            }

        }
        slots.add(weaponSlot);
        slots.add(shieldSlot);
        slots.add(chestSlot);
        slots.add(helmetSlot);
        slots.add(feetSlot);
    }

}
