/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escape;

import Graphique.Item.Item;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 *
 * @author wail
 */
public class MouseArea extends MouseOverArea{
    
    
    private boolean selected;
    private boolean moving,equiped;
    private int id;
    private float oldX,oldY;
    private Item item;
    
    public MouseArea(GUIContext container, Image image, Shape shape) {
        super(container, image, shape);
        
        
        
    }

    public MouseArea(GUIContext container, Image image, int x, int y, int id, Item item) {
        super(container, image, x, y);
        this.id = id;
        this.oldX = x;
        this.oldY = y;
        this.item = item;
        
        
    }

    public void setOldX(float oldX) {
        this.oldX = oldX;
    }

    public void setOldY(float oldY) {
        this.oldY = oldY;
    }

    public float getOldX() {
        return oldX;
    }

    public float getOldY() {
        return oldY;
    }

    public GUIContext getContainer() {
        return container;
    }

    @Override
    public int getHeight() {
        return super.getHeight(); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setEquiped(boolean equiped) {
        this.equiped = equiped;
    }

    public boolean isEquiped() {
        return equiped;
    }
    
    
    

    
    
    
    

   
    
    
    
    
   
    
}
