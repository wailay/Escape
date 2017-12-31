/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique.Item;

import Entities.Player;
import Level.LevelManager;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Random;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author 1529565
 */
public class Item {

    protected int damage, vitality, armor, range, mana;
    private String description, typeItemString, itemDescription;
    protected Animation[] anim;
    protected boolean equiped;
    protected static LinkedHashMap<Integer, SpriteSheet> sprites = new LinkedHashMap<>();
    protected static LinkedHashMap<Integer, Animation[]> animations = new LinkedHashMap<>();

    

    /**
     * Le type de l'item
     */
    public enum TypeItem {
        WEAPON, CHEST_ARMOR, HELMET, BOOT, OFFHAND
    }

    public enum TypeWeapon {
        MELEE, WAND;
    }
    protected TypeItem type;
    protected TypeWeapon typeWeapon;
    protected int duration = 130;
    private boolean looted;
    Random rand = new Random();
    protected static SpriteSheet itemSheet, itemSheetInventaire;

    protected static SpriteSheet item_0, item_3, item_4, item_5, item_7, item_8, item_24, item_37, item_42, item_44, item_43, item_48, item_55;

    protected Rectangle box;
    protected int id;
    protected boolean picked;
    Random r = new Random();

    protected Vector2f vector = new Vector2f();
    protected int j, i, notPickedSize = 32, width = 50, height = 50;

    public Item(Vector2f vector) throws SlickException {
        this.itemSheet = LevelManager.getItemTexture().itemSheet;
        this.itemSheetInventaire = LevelManager.getItemTexture().itemSheetInventaire;
        this.animations = LevelManager.getItemTexture().animations;
        this.sprites = LevelManager.getItemTexture().sprites;
        this.anim = LevelManager.getItemTexture().anim;
        this.vector = vector;
        box = new Rectangle(vector.x, vector.y, notPickedSize, notPickedSize);

    }

    /**
     * Permet d'afficher un item quelconque lorsqu'il est tombé
     *
     * @param g
     */
    public void render(Graphics g) {

        i = id / 20;
        j = id % 20;
        g.drawImage(itemSheet.getSprite(j, i), vector.x, vector.y);

    }

    /**
     * Permet d'affichier un item équipé sur un joueur
     *
     * @param g
     * @param player Le joueur qui equipe l'item
     */
    public void renderEquipedItem(Graphics g, Player player) {

        anim = animations.get(id);
        if (player.getDirection() == 1) {
            g.drawAnimation(anim[player.getDirection() + (player.isMoving() ? 4 : 0)], player.getX(), player.getY());
        } else if (player.getDirection() == 3) {
            g.drawAnimation(anim[player.getDirection() + (player.isMoving() ? 4 : 0)], player.getX() - 26, player.getY());
        } else if (player.getDirection() == 2) {
            g.drawAnimation(anim[player.getDirection() + (player.isMoving() ? 4 : 0)], player.getX(), player.getY() - 18);
        } else if (player.getDirection() == 0) {
            g.drawAnimation(anim[player.getDirection() + (player.isMoving() ? 4 : 0)], player.getX(), player.getY() - 18);
        }
        
    }

    public void setLooted(boolean looted) {
        this.looted = looted;
    }

    public boolean isLooted() {
        return looted;
    }

    public int getId() {
        return id;
    }

    /**
     * Chaque item est reconnu par son ID. Selon l'ID de chaque Item, un type
     * lui est attribué
     *
     * @return
     */
    public TypeItem getType() {
       
        switch (id) {

            case 48:
            case 55:
            case 3:
            case 0:
            case 42:
            case 4:
            case 5:
            case 7:
            case 8:
            case 24:
            case 25:
            case 26:
            case 44:
            case 43:
            case 37:
                type = TypeItem.WEAPON;
                break;
            case 1:
            case 2:
            case 6:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                type = TypeItem.OFFHAND;
                break;
            case 20:
            case 21:
            case 22:
            case 23:
                type = TypeItem.BOOT;
                break;
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 38:
            case 45:
            case 46:
            case 47:

                type = TypeItem.CHEST_ARMOR;
                break;

            case 40:
            case 41:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
                type = TypeItem.HELMET;
                break;

        }
        return type;

    }

    /**
     * Initialise la description de chaque items
     *
     * @return Retourne la description
     */
    public String getItemDescription() {
        switch (getId()) {
            case 48:

                itemDescription = "The union of an angel \nand a demon created a \nthird essence."
                        + "Only those children\n can hold this weapon, try\n if you can";

                break;

            case 55:
                itemDescription = "Glow so much that it can\n make you go blind. "
                        + "Better \nclose your eyes";

                break;
            case 3:
                itemDescription = "This staff can make a perfect\n flower drawing,... cool";

                break;
            case 0:
                itemDescription = "Belonged to a great \n wizard monk, it explains\n"
                        + "the useless red ribbons hanging";

                break;
            case 42:

                itemDescription = "Wield it with caution and\n honour, it had"
                        + "belonged to\n the greatest";

                break;
            case 4:
                itemDescription = "This staff have a very\n sensitive trigger, be careful !";

                break;
            case 5:
                itemDescription = "This a beginner training staff,\n you can throw it away...";

                break;
            case 7:
                itemDescription = "It's just red, nothing special...";

                break;
            case 8:
                itemDescription = "Made from glass, long time\n ago this staff was one\n of the most powerful one.\n"
                        + "We moved to plastic now.";

                break;
            case 24:
                itemDescription = "It said that those who\n hold it can seek the\n truth of the crusader.\n"
                        + "Thou shall not end a\n human life with it";

                break;
            case 44:
                itemDescription = "When you hold it, instantly\n become a ninja.";

                break;
            case 43:
                itemDescription = "Can cut trees pretty well";

                break;

            case 37:
                itemDescription = "Forged by the greatest \nMom Lord, it cuts perfectly\n "
                        + "through your favorite vegetables";

                break;

        }
        return itemDescription;
    }

    public Image getImage() {
        return itemSheet.getSprite(j, i);
    }

    public Image getInventaireImage() {
        return itemSheetInventaire.getSprite(j, i);
    }

    /**
     * Initialise le type d'item en chaine de caractere
     *
     * @return Retourne le type d'item en chaine de caractere
     */
    public String getTypeItemString() {
        switch (getType()) {
            case BOOT:
                typeItemString = "Boots";
                break;
            case WEAPON:
                typeItemString = "Weapon";
                break;
            case HELMET:
                typeItemString = "Helmet";
                break;
            case OFFHAND:
                typeItemString = "Offhand";
                break;
            case CHEST_ARMOR:
                typeItemString = "Chest";
                break;
        }

        return typeItemString;
    }

    /**
     * Initialise les statistiques de chaques items
     */
    public void getItemStats() {
        switch (getId()) {

            case 48:
                damage = rand.nextInt(13) + 400;
                vitality = 500;
                armor = 1000;
                range = 5;
                break;

            case 55:
                damage = rand.nextInt(13) + 150;
                vitality = 200;
                armor = 700;
                range = 5;
                break;
            case 3:
                damage = rand.nextInt(5) + 20;
                vitality = 10;
                armor = 150;
                range = 25;
                break;
            case 0:
                damage = rand.nextInt(5) + 17;
                vitality = 16;
                armor = 97;
                range = 24;
                break;
            case 42:
                damage = rand.nextInt(8) + 67;
                vitality = 85;
                armor = 267;
                break;
            case 4:
                damage = rand.nextInt(3) + 8;
                vitality = 8;
                armor = 22;
                range = 27;
                break;
            case 5:
                damage = rand.nextInt(2) + 7;
                vitality = 5;
                armor = 19;
                range = 17;
                break;
            case 7:
                damage = rand.nextInt(5) + 24;
                vitality = 9;
                armor = 19;
                range = 20;
                break;
            case 8:
                damage = rand.nextInt(6) + 24;
                vitality = 56;
                armor = 40;
                range = 30;
                break;
            case 24:
                damage = rand.nextInt(9) + 69;
                vitality = 15;
                armor = 69;
                range = 1;
                break;
            case 25:
                damage = rand.nextInt(1) + 1;
                vitality = 1;
                armor = 1;
                range = 1;
                break;
            case 44:
                damage = rand.nextInt(7) + 35;
                vitality = 25;
                armor = 5;
                range = 1;
                break;
            case 43:
                damage = rand.nextInt(5) + 29;
                vitality = 6;
                armor = 37;
                range = 1;
                break;

            case 37:
                damage = rand.nextInt(3) + 1;
                vitality = rand.nextInt(3) + 1;
                armor = rand.nextInt(3) + 1;
                range = 1;
                break;
            case 20:
                damage = rand.nextInt(4) + 1;
                vitality = rand.nextInt(3) + 7;
                armor = rand.nextInt(5) + 15;
                range = 1;
                break;
            case 21:
                damage = rand.nextInt(3) + 1;
                vitality = rand.nextInt(3) + 9;
                armor = rand.nextInt(7) + 27;
                range = 1;
                break;
            case 22:
                damage = rand.nextInt(4) + 2;
                vitality = rand.nextInt(3) + 11;
                armor = rand.nextInt(8) + 35;
                range = 1;
                break;
            case 23:
                damage = rand.nextInt(5) + 2;
                vitality = rand.nextInt(3) + 10;
                armor = rand.nextInt(6) + 17;
                range = 1;
                break;
            case 1:
                damage = rand.nextInt(6) + 25;
                vitality = rand.nextInt(3) + 25;
                armor = rand.nextInt(3) + 5;
                range = 15;
                break;
            case 2:
                damage = rand.nextInt(6) + 15;
                vitality = rand.nextInt(3) + 15;
                armor = rand.nextInt(3) + 5;
                range = 7;
                break;
            case 6:
                damage = rand.nextInt(6) + 20;
                mana = rand.nextInt(3) + 5;
                armor = rand.nextInt(3) + 5;
                range = 7;
                break;
            case 9:
                damage = rand.nextInt(6) + 25;
                mana = rand.nextInt(3) + 5;
                armor = rand.nextInt(3) + 5;
                range = 20;
                break;
            case 10:
                damage = rand.nextInt(6) + 22;
                mana = rand.nextInt(3) + 5;
                armor = rand.nextInt(3) + 10;
                range = 7;
                break;
            case 11:
                damage = rand.nextInt(6) + 10;
                mana = rand.nextInt(3) + 5;
                armor = rand.nextInt(3) + 5;
                range = 5;
                break;
            case 12:
                damage = rand.nextInt(6) + 10;
                mana = rand.nextInt(3) + 5;
                armor = rand.nextInt(3) + 5;
                range = 5;
                break;
            case 13:
                damage = rand.nextInt(6) + 10;
                mana = rand.nextInt(3) + 5;
                armor = rand.nextInt(3) + 5;
                range = 5;
                break;
            case 14:
                damage = rand.nextInt(6) + 10;
                mana = rand.nextInt(3) + 5;
                armor = rand.nextInt(3) + 5;
                range = 5;
                break;
            case 15:
                damage = rand.nextInt(6) + 10;
                mana = rand.nextInt(3) + 5;
                armor = rand.nextInt(3) + 5;
                range = 5;
                break;
            case 16:
                damage = rand.nextInt(6) + 10;
                mana = rand.nextInt(3) + 5;
                armor = rand.nextInt(3) + 5;
                range = 5;
                break;
            case 17:
                damage = rand.nextInt(6) + 10;
                mana = rand.nextInt(3) + 10;
                armor = rand.nextInt(3) + 5;
                range = 5;
                break;
            case 18:
                damage = rand.nextInt(6) + 10;
                mana = rand.nextInt(3) + 5;
                armor = rand.nextInt(3) + 5;
                range = 5;
                break;
            case 19:
                damage = rand.nextInt(6) + 10;
                mana = rand.nextInt(3) + 10;
                armor = rand.nextInt(3) + 5;
                range = 5;
                break;
            case 60:
                damage = rand.nextInt(3) + 1;
                vitality = rand.nextInt(2) + 10;
                armor = rand.nextInt(3) + 35;
                range = 0;
                break;
            case 61:
                damage = rand.nextInt(3) + 5;
                vitality = rand.nextInt(2) + 13;
                armor = rand.nextInt(3) + 37;
                range = 0;
                break;
            case 62:
                damage = rand.nextInt(3) + 4;
                vitality = rand.nextInt(2) + 8;
                armor = rand.nextInt(3) + 33;
                range = 0;
                break;
            case 63:
                damage = rand.nextInt(3) + 4;
                vitality = rand.nextInt(2) + 15;
                armor = rand.nextInt(3) + 45;
                range = 0;
                break;
            case 64:
                damage = rand.nextInt(3) + 3;
                vitality = rand.nextInt(2) + 12;
                armor = rand.nextInt(3) + 47;
                range = 0;
                break;
            case 65:
                damage = rand.nextInt(3) + 1;
                vitality = rand.nextInt(2) + 12;
                armor = rand.nextInt(3) + 47;
                range = 0;
                break;
            case 66:
                damage = rand.nextInt(3) + 2;
                vitality = rand.nextInt(2) + 12;
                armor = rand.nextInt(3) + 43;
                range = 0;
                break;
            case 67:
                damage = rand.nextInt(3) + 1;
                vitality = rand.nextInt(2) + 1;
                armor = rand.nextInt(3) + 65;
                range = 0;
                break;
            case 68:
                damage = rand.nextInt(3) + 2;
                vitality = rand.nextInt(2) + 5;
                armor = rand.nextInt(3) + 25;
                range = 0;
                break;
            case 29:
                damage = rand.nextInt(3) + 13;
                vitality = rand.nextInt(2) + 15;
                armor = rand.nextInt(3) + 2;
                range = 0;
                break;
            case 30:
                damage = rand.nextInt(3) + 12;
                vitality = rand.nextInt(2) + 6;
                armor = rand.nextInt(3) + 1;
                range = 9;
                break;
            case 31:
                damage = rand.nextInt(3) + 13;
                vitality = rand.nextInt(2) + 5;
                armor = rand.nextInt(3) + 1;
                range = 9;
                break;
            case 32:
                damage = rand.nextInt(3) + 11;
                vitality = rand.nextInt(2) + 12;
                armor = rand.nextInt(3) + 1;
                range = 9;
                break;
            case 33:
                damage = rand.nextInt(3) + 3;
                vitality = rand.nextInt(2) + 8;
                armor = rand.nextInt(3) + 24;
                range = 1;
                break;
            case 34:
                damage = rand.nextInt(3) + 3;
                vitality = rand.nextInt(2) + 8;
                armor = rand.nextInt(3) + 32;
                range = 1;
                break;
            case 35:
                damage = rand.nextInt(3) + 3;
                vitality = rand.nextInt(2) + 5;
                armor = rand.nextInt(3) + 31;
                range = 1;
                break;
            case 36:
                damage = rand.nextInt(3) + 3;
                vitality = rand.nextInt(2) + 9;
                armor = rand.nextInt(3) + 20;
                range = 1;
                break;
            case 38:
                damage = rand.nextInt(3) + 25;
                vitality = rand.nextInt(2) + 25;
                armor = rand.nextInt(3) + 0;
                range = 14;
                break;
            case 45:
                damage = rand.nextInt(3) + 2;
                vitality = rand.nextInt(2) + 25;
                armor = rand.nextInt(3) + 50;
                range = 0;
                break;
            case 46:
                damage = rand.nextInt(3) + 2;
                vitality = rand.nextInt(2) + 25;
                armor = rand.nextInt(6) + 65;
                range = 0;
                break;
            case 47:
                damage = rand.nextInt(3) + 2;
                vitality = rand.nextInt(2) + 45;
                armor = rand.nextInt(10) + 75;
                range = 0;
                break;
            case 40:
                damage = rand.nextInt(3) + 10;
                vitality = rand.nextInt(2) + 15;
                armor = rand.nextInt(1) + 1;
                range = 6;
                break;
            case 41:
                damage = rand.nextInt(3) + 13;
                vitality = rand.nextInt(2) + 20;
                armor = rand.nextInt(1) + 1;
                range = 9;
                break;
            case 49:
                damage = rand.nextInt(3) + 20;
                vitality = rand.nextInt(2) + 20;
                armor = rand.nextInt(1) + 1;
                range = 20;
                break;
            case 50:
                damage = rand.nextInt(3) + 5;
                vitality = rand.nextInt(2) + 20;
                armor = rand.nextInt(3) + 75;
                range = 0;
                break;
            case 51:
                damage = rand.nextInt(3) + 7;
                vitality = rand.nextInt(2) + 20;
                armor = rand.nextInt(4) + 70;
                range = 0;
                break;
            case 52:
                damage = rand.nextInt(3) + 7;
                vitality = rand.nextInt(2) + 25;
                armor = rand.nextInt(7) + 100;
                range = 0;
                break;

            case 53:
                damage = rand.nextInt(3) + 5;
                vitality = rand.nextInt(2) + 15;
                armor = rand.nextInt(7) + 80;
                range = 0;
                break;

        }

    }

    /**
     * Initialise la description final, qui sera afficher lors du jeux, de
     * chaque item
     *
     * @return La description finale en String
     */
    public String getDescription() {

        description = getTypeItemString() + "\n" + getItemDescription() + " "
                + "\n\n+ " + damage + " Damage increased"
                + "\n\n+ " + vitality + " % Vitality"
                + "\n\n+ " + armor + " % Damage reduction"
                + "\n\n+ " + mana + " Mana Points\n\n"
                + range + " Weapon range";

        return description;

    }

    public int getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }

    public int getVitality() {
        return vitality;
    }

    public Vector2f getVector() {
        return vector;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
    
    

    public Rectangle getBox() {
        return box;
    }

    public Animation[] getAnim() {
        return anim;
    }

    public static LinkedHashMap<Integer, Animation[]> getAnimations() {
        return animations;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEquiped(boolean equiped) {
        this.equiped = equiped;
    }

    public boolean isEquiped() {
        return equiped;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public TypeWeapon getTypeWeapon() {
         switch (id) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
                typeWeapon = TypeWeapon.WAND;
                break;
            case 48:
            case 55:

            case 42:

            case 24:
            case 25:
            case 26:
            case 44:
            case 43:
            case 37:
                typeWeapon = TypeWeapon.MELEE;
                break;
        }
        return typeWeapon;
    }

    public void setVector(Vector2f vector) {
        this.vector = vector;
    }
    

}
