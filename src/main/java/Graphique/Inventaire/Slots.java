/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique.Inventaire;

import escape.MouseArea;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author wail
 */
public class Slots extends Rectangle{
    
    private boolean containsItem;
    
    
    
    public Slots(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    
    public boolean isEmpty(MouseArea ma){
        if(this.contains(ma.getX()+5, ma.getY()+5)){
            return false;
        }
        return true;
    }
    
    
    
    public boolean containsItem() {
        
        return containsItem;
    }

    public void setContainsItem(boolean containsItem) {
        this.containsItem = containsItem;
    }
    
    
    
    
}
