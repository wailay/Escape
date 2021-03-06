/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import Entities.BatEntity;
import Entities.Entity;
import Entities.Player;
import Entities.Player2;
import Entities.WolfEntity;
import Entities.SqueletonEntity;
import Entities.SpiderEntity;

import Graphique.Tile.ChestTile;
import Graphique.Tile.DoorTile;
import Graphique.Item.Item;
import Graphique.Tile.BatSpawnTile;
import Graphique.Tile.HelperGuyTile;
import Graphique.Tile.MetalDoor;
import Graphique.Tile.OpenedChestTile;
import Graphique.Tile.OpenedDoorTile;
import Graphique.Tile.OpenedMetalDoor;
import Graphique.Tile.SpiderSpawnTile;
import Graphique.Tile.SqueletonSpawnTile;
import Graphique.Tile.Tile;
import Graphique.Tile.TileBreakable;
import Graphique.Tile.TileLoad;
import org.newdawn.slick.Graphics;
import Graphique.Tile.TileSolid;
import Graphique.Tile.TriggerTile;
import Graphique.Tile.VoidTile;
import Graphique.Tile.WolfSpawnTile;
import static Level.LevelManager.inventaire;
import States.Inventaire;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author wail
 */
public class Map extends LevelManager {

    private GameContainer gc;
    private int solidTileDown, triggerTileDown, WIDTH, HEIGHT;
    private float scale = 2;
    private static final int solidTileSetID = 0, solidTileID = 1;

    private Rectangle[] recsCollision;
    private ArrayList<Rectangle> recsTrigger;

    private TileSolid[] tabSolidTile;
    private ArrayList<Tile> listeTile, listeEntitySpawnTile;
    private TileSolid tileSolid;
    private TriggerTile tileTrigger;
    private float xOverLap, yOverLap;

    private boolean chestOpened;
    protected TiledMap tiledMap;

    public Map(GameContainer gc) throws SlickException {
        this.gc = gc;
    }
    public int taille;

    public TiledMap loadMap(String path) throws SlickException {

        //TiledMap
        this.tiledMap = new TiledMap(path);

        tileSolid = new TileSolid(tiledMap);
        tileTrigger = new TriggerTile(tiledMap);
        tileTrigger.compteurTile(tiledMap);

        taille = tileSolid.compteurTileSolid(tiledMap);

        this.tabSolidTile = new TileSolid[taille];

        listeTile = new ArrayList<>();
        listeEntitySpawnTile = new ArrayList<>();

        loadMapTile(tiledMap);
        initListeTile(tiledMap);
        initListeEntitySpawnTile(tiledMap);
        tileSolidTriggerPosition(tiledMap);

        addEntities(tiledMap);
        boxCollision(tiledMap);
        boxTrigger(tiledMap);

        this.WIDTH = tiledMap.getHeight();
        this.HEIGHT = tiledMap.getWidth();

        return tiledMap;
    }

    /**
     * Affiche la map au complet avec toutes ses couches
     *
     * @param g
     * @param map
     */
    public void render(Graphics g, Map map) {

        map.tiledMap.render(0, 0);

    }

    /**
     * Permet d'afficher la map selon les couches existantes
     *
     * @param g
     * @param map
     * @param layer La couche a afficher
     */
    public void renderLayer(Graphics g, Map map, int layer) {
        map.tiledMap.render(0, 0, layer);
    }

    /**
     * Initialise une liste contenant les tuiles activables d'une map
     *
     * @param map
     */
    public void initListeTile(TiledMap map) {
        listeTile.clear();
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {

                if (map.getTileId(i, j, 2) == 2) {

                    listeTile.add(new DoorTile(map));
                }
                if (map.getTileId(i, j, 2) == 3) {
                    listeTile.add(new OpenedDoorTile(map));

                }
                if (map.getTileId(i, j, 2) == 4) {
                    listeTile.add(new ChestTile(map));
                }
                if (map.getTileId(i, j, 2) == 5) {
                    listeTile.add(new OpenedChestTile(map));
                }
                if (map.getTileId(i, j, 2) == 6) {
                    listeTile.add(new MetalDoor(map));
                }
                if (map.getTileId(i, j, 2) == 7) {
                    listeTile.add(new OpenedMetalDoor(map));
                }
                if (map.getTileId(i, j, 2) == 8) {
                    listeTile.add(new TileLoad(map));
                }
                if (map.getTileId(i, j, 2) == 21) {
                    listeTile.add(new TileBreakable(map));
                }
                if (map.getTileId(i, j, 2) == 22) {
                    listeTile.add(new VoidTile(map));
                }
                if (map.getTileId(i, j, 2) == 13) {
                    listeTile.add(new HelperGuyTile(map));
                }

            }
        }

    }

    /**
     * Initialise une liste contenant les tuiles servant a faire apparaitre des
     * entités.
     *
     * @param map
     */
    public void initListeEntitySpawnTile(TiledMap map) {

        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {

                if (map.getTileId(i, j, solidTileSetID) == 9) {
                    listeEntitySpawnTile.add(new BatSpawnTile(map));
                }
                if (map.getTileId(i, j, solidTileSetID) == 10) {
                    listeEntitySpawnTile.add(new SqueletonSpawnTile(map));
                }
                if (map.getTileId(i, j, solidTileSetID) == 11) {
                    listeEntitySpawnTile.add(new WolfSpawnTile(map));
                }
                if (map.getTileId(i, j, solidTileSetID) == 12) {
                    listeEntitySpawnTile.add(new SpiderSpawnTile(map));
                }

            }
        }
    }

    /**
     * Le méthode d'ajout des entités consiste a suivre un code de couleur et de
     * tuile. Cette méthode parcours la liste de tuile global dans la map et
     * regarde s'il existe une tuile qui fait apparaitre une entité quelconque.
     * Si cette tuile existe l'entité sera ajouter a une liste qui sera gérer
     * dans les states.
     *
     * @param map
     * @throws SlickException
     */
    public void addEntities(TiledMap map) throws SlickException {
        for (Tile tile : listeEntitySpawnTile) {

            if (tile instanceof BatSpawnTile) {
                listeEntities.add(new BatEntity(tile.getx(), tile.gety()));

            }
            if (tile instanceof SqueletonSpawnTile) {
                listeEntities.add(new SqueletonEntity(tile.getx(), tile.gety()));
            }
            if (tile instanceof SpiderSpawnTile) {
                listeEntities.add(new SpiderEntity(tile.getx(), tile.gety()));
            }
            if (tile instanceof WolfSpawnTile) {
                listeEntities.add(new WolfEntity(tile.getx(), tile.gety()));
            }

        }

    }

    /**
     * Permet d'obtenir la posision des tuiles solides et activables qui sont
     * dans la map La position est ensuite sotcké dans un tableau. Ce tableau
     * servira par la suite a créer les rectangles qui seront placés aux
     * positions des tuiles.
     *
     * @param map
     */
    public void tileSolidTriggerPosition(TiledMap map) {

        int k = 0;
        int l = 0;
        int x = 0;
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getTileId(i, j, solidTileSetID) == solidTileID) {

                    if (k < tileSolid.solidTileDown) {
                        tabSolidTile[k].setX(i * 32);
                        tabSolidTile[k].setY(j * 32);
                    }
                    k++;
                }

                if (l < listeTile.size()) {

                    switch (map.getTileId(i, j, 2)) {
                        case 2:

                            listeTile.get(l).setX(i * 32);
                            listeTile.get(l).setY(j * 32);
                            l++;
                            break;
                        case 3:

                            listeTile.get(l).setX(i * 32);
                            listeTile.get(l).setY(j * 32);
                            l++;
                            break;
                        case 4:

                            listeTile.get(l).setX(i * 32);
                            listeTile.get(l).setY(j * 32);
                            l++;
                            break;
                        case 5:

                            listeTile.get(l).setX(i * 32);
                            listeTile.get(l).setY(j * 32);
                            l++;
                            break;
                        case 6:

                            listeTile.get(l).setX(i * 32);
                            listeTile.get(l).setY(j * 32);
                            l++;
                            break;
                        case 7:

                            listeTile.get(l).setX(i * 32);
                            listeTile.get(l).setY(j * 32);
                            l++;
                            break;

                        case 21:

                            listeTile.get(l).setX(i * 32);
                            listeTile.get(l).setY(j * 32);
                            l++;
                            break;
                        case 22:

                            listeTile.get(l).setX(i * 32);
                            listeTile.get(l).setY(j * 32);
                            l++;
                            break;
                        case 13:

                            listeTile.get(l).setX(i * 32);
                            listeTile.get(l).setY(j * 32);
                            l++;
                            break;
                    }

                }
                if (x < listeEntitySpawnTile.size()) {
                    switch (map.getTileId(i, j, solidTileSetID)) {
                        case 9:

                            listeEntitySpawnTile.get(x).setX(i * 32);
                            listeEntitySpawnTile.get(x).setY(j * 32);

                            x++;
                            break;
                        case 10:

                            listeEntitySpawnTile.get(x).setX(i * 32);
                            listeEntitySpawnTile.get(x).setY(j * 32);
                            x++;
                            break;
                        case 11:

                            listeEntitySpawnTile.get(x).setX(i * 32);
                            listeEntitySpawnTile.get(x).setY(j * 32);
                            x++;
                            break;
                        case 12:

                            listeEntitySpawnTile.get(x).setX(i * 32);
                            listeEntitySpawnTile.get(x).setY(j * 32);
                            x++;
                            break;
                    }
                }

            }
        }

    }

    /**
     * Instancie un tableau des tuiles de la map
     *
     * @param tiledMap La map en question
     */
    public void loadMapTile(TiledMap tiledMap) {

        for (int i = 0; i < tabSolidTile.length; i++) {

            tabSolidTile[i] = new TileSolid(tiledMap);

        }
    }

    /**
     * Permet de créer des rectangles graphique sur les tuiles activable.
     *
     * @param map
     */
    public void boxTrigger(TiledMap map) {
        recsTrigger = new ArrayList<>();
        for (int i = 0; i < listeTile.size(); i++) {

            recsTrigger.add(new Rectangle(listeTile.get(i).getx(), listeTile.get(i).gety(), listeTile.get(i).getWidth(), listeTile.get(i).getHeight()));

        }
    }

    /**
     * Permet de créer des rectangles graphique sur les tuiles solides.
     *
     * @param map
     */
    public void boxCollision(TiledMap map) {
        recsCollision = new Rectangle[taille];
        for (int i = 0; i < taille; i++) {

            recsCollision[i] = new Rectangle(tabSolidTile[i].getx(), tabSolidTile[i].gety(), tabSolidTile[i].getWidth(), tabSolidTile[i].getHeight());

        }
    }

    /**
     * Lorsque le joueur clique sur un coffre,il peux prendre l'item en
     * question. Cette méthode enleve ensuite l'item de la liste global d'item
     * qui sont apparu dans la carte. Et met ensuite cet item dans la liste
     * inventaire.
     *
     * @param player Le joueur
     * @param map La map actuelle
     */
    public void itemPickUp(Player player, Map map) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (Item item : map.getListeItem()) {

                    if (player.getTriggerRadius().intersects(item.getBox())) {
                       
                        item.setLooted(true);

                        inventaire.add(item);
                        try {
                            Inventaire.addToItemHolder(gc);
                        } catch (SlickException ex) {
                            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //Methode a appeler pour ne pas modifier la liste pendant qu'elle est parcourue, sinon le programme plante.
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                map.getListeItem().remove(item);
                            }
                        });

                    }

                }
            }
        });

    }
    int fois;

    /**
     * Parcours la map et regarde si une tuile qui peut etre activé a ete activé
     * Par exemple si une tuile porte ouver a été activé, alors elle sera
     * remplacer par une tuile porte fermée. Cette méthode contient aussi le
     * code pour faire apparaitre un item lorsque un coffre a été ouvert.
     *
     * @param player Le joueur
     * @param map La map actuelle
     * @return Retourne vrai si une tuile a été activé.
     * @throws SlickException
     */
    public boolean checkTrigger(Entity player, Map map) throws SlickException {

        boolean trigger = false;

        for (int i = 0; i < recsTrigger.size(); i++) {
            if (player.getTriggerRadius().intersects(recsTrigger.get(i))) {

                if (listeTile.get(i) instanceof DoorTile) {
                    map.tiledMap.setTileId(listeTile.get(i).getx() / 32, listeTile.get(i).gety() / 32, 2, 3);
                }
                if (listeTile.get(i) instanceof OpenedDoorTile) {

                    map.tiledMap.setTileId(listeTile.get(i).getx() / 32, listeTile.get(i).gety() / 32, 2, 2);
                }
                if (listeTile.get(i) instanceof ChestTile) {
                    Vector2f vector = new Vector2f();
                    vector.x = listeTile.get(i).getx();
                    vector.y = listeTile.get(i).gety();

                    Item item = new Item(vector);

                    item.setId(itemDropRate());;

                    item.getItemStats();

                    listeItem.add(item);

                    map.tiledMap.setTileId(listeTile.get(i).getx() / 32, listeTile.get(i).gety() / 32, 2, 5);
                }

                if (listeTile.get(i) instanceof MetalDoor) {
                    map.tiledMap.setTileId(listeTile.get(i).getx() / 32, listeTile.get(i).gety() / 32, 2, 7);
                }
                if (listeTile.get(i) instanceof OpenedMetalDoor) {
                    map.tiledMap.setTileId(listeTile.get(i).getx() / 32, listeTile.get(i).gety() / 32, 2, 6);
                }
                if (listeTile.get(i) instanceof TileBreakable) {
                    for (Item item : LevelManager.getEquipedItem().values()) {
                        if (item.getId() == 25) {
                            if (LevelManager.getEquipedItem().containsValue(item)) {

                                map.tiledMap.setTileId(listeTile.get(i).getx() / 32, listeTile.get(i).gety() / 32, 2, 22);
                            }

                        }
                    }
                    if(LevelManager.getEquipedItem().isEmpty()){
                        HelperGuyTile.setShowDialog(true);
                        HelperGuyTile.setText("Tu ne peux pas casser ces briques avec \ntes mains ! "
                                + "Il te faut un outil special. ");
                           
                        
                         
                    }

                }

                if (listeTile.get(i) instanceof HelperGuyTile) {
                    HelperGuyTile.setFois(HelperGuyTile.getFois()+1);

                }

                initListeTile(tiledMap);
                tileSolidTriggerPosition(tiledMap);
            }
        }

        return trigger;
    }

    /**
     * Permet d'obtenir un taux de chance pour chaque item et type d'item.
     * Chaque item a une rareté differente, alors chacun a une chance differente
     * d'apparaitre
     *
     * @return L'item qui a été choisis par chance.
     */
    public int itemDropRate() {
        HashMap<Integer, int[]> chooseItemType = new HashMap<>();
        HashMap<Integer, double[]> chooseItemRate = new HashMap<>();

        int key = 0;
        int maxProb = 1000;

        double[] weaponRate = {5, 10, 30, 50, 70, 90, 180, 280, 380, 480, 580, 680, 780, 880, maxProb};
        int weapon[] = {48, 55, 0, 3, 42, 8, 4, 7, 43, 44, 24, 5, 25, 26, 37};
        double[] offhandRate = {3, 6, 9, 24, 39, 54, 69, 84, 99, 114, 129, 144, 221, 298, 375, 452, 529, 606, 683, 760, 837, 914, maxProb};
        int offhand[] = {1, 2, 6, 9, 10, 61, 62, 63, 64, 65, 66, 67, 11, 12, 13, 14, 15, 16, 17, 18, 19, 60, 68};

        double[] bootsRate = {250, 500, 750, maxProb};
        int boots[] = {20, 21, 22, 23};

        double[] chestRate = {80, 160, 240, 320, 400, 480, 560, 640, 720, 800, 880, maxProb};
        int chest[] = {29, 30, 31, 32, 33, 34, 35, 38, 45, 46, 47, 36};
        double[] helmetRate = {140, 280, 420, 560, 700, 840, maxProb};
        int helmet[] = {40, 41, 49, 50, 51, 52, 53};
        chooseItemType.put(0, weapon);
        chooseItemType.put(1, offhand);
        chooseItemType.put(2, boots);
        chooseItemType.put(3, chest);
        chooseItemType.put(4, helmet);
        chooseItemRate.put(0, weaponRate);
        chooseItemRate.put(1, offhandRate);
        chooseItemRate.put(2, bootsRate);
        chooseItemRate.put(3, chestRate);
        chooseItemRate.put(4, helmetRate);

        switch (r.nextInt(5)) {
            case 0:
                key = 0;
                break;
            case 1:
                key = 1;
                break;
            case 2:
                key = 2;
                break;
            case 3:
                key = 3;
                break;
            case 4:
                key = 4;
                break;

        }

        int dropChance = r.nextInt(maxProb);
        int i = 0;

        while (chooseItemRate.get(key)[i] <= dropChance) {
            i++;
        }
        return chooseItemType.get(key)[i];
    }

    public void checkTriggerCollision(Entity entity) {

        for (int i = 0; i < recsTrigger.size(); i++) {
            Rectangle A = entity.getBox();

            Rectangle B = recsTrigger.get(i);
            if (entity.getBox().intersects(recsTrigger.get(i))) {

                if (listeTile.get(i) instanceof OpenedDoorTile || listeTile.get(i) instanceof OpenedMetalDoor
                        || listeTile.get(i) instanceof VoidTile) {

                } else {
                    float inter = calculateIntersects(A.getX(), A.getX() + A.getWidth(), A.getY(), A.getY() + A.getHeight(),
                            B.getX(), B.getX() + B.getWidth(), B.getY(), B.getY() + B.getHeight());
                    if (Math.abs(yOverLap) < Math.abs(xOverLap)) {
                        if (A.getCenterY() < B.getCenterY()) {
                            entity.setY(entity.getY() - yOverLap);
                        } else {
                            entity.setY(entity.getY() + yOverLap);
                        }
                    }
                    if (Math.abs(xOverLap) < Math.abs(yOverLap)) {
                        if (A.getCenterX() < B.getCenterX()) {
                            entity.setX(entity.getX() - xOverLap);
                        } else {
                            entity.setX(entity.getX() + xOverLap);
                        }
                    }

                }

            }
        }

    }

    public void checkCollision(Entity entity) {

        for (int i = 0; i < recsCollision.length; i++) {

            Rectangle A = entity.getBox();

            Rectangle B = recsCollision[i];

            if (entity.getBox().intersects(recsCollision[i])) {
                float inter = calculateIntersects(A.getX(), A.getX() + A.getWidth(), A.getY(), A.getY() + A.getHeight(),
                        B.getX(), B.getX() + B.getWidth(), B.getY(), B.getY() + B.getHeight());
                if (Math.abs(yOverLap) < Math.abs(xOverLap)) {
                    if (A.getCenterY() < B.getCenterY()) {
                        entity.setY(entity.getY() - yOverLap);
                    } else {
                        entity.setY(entity.getY() + yOverLap);
                    }

                }
                if (Math.abs(xOverLap) < Math.abs(yOverLap)) {
                    if (A.getCenterX() < B.getCenterX()) {
                        entity.setX(entity.getX() - xOverLap);

                    } else {
                        entity.setX(entity.getX() + xOverLap);

                    }
                }
            }

        }

    }

    public boolean isInCollision(Entity entity) {
        for (int i = 0; i < recsCollision.length; i++) {
            if (entity.getBox().intersects(recsCollision[i])) {
                return true;
            }
        }
        for (int i = 0; i < recsTrigger.size(); i++) {
            if (entity.getBox().intersects(recsTrigger.get(i))) {
                if (listeTile.get(i) instanceof OpenedDoorTile) {
                    return false;
                } else {
                    return true;
                }

            }
        }
        return false;
    }

    //Debug a enlever
    public void drawCollisionRecs(Graphics g, Map map) {
//        g.draw(entity.box);
//        g.draw(entity.triggerRadius);
        for (int i = 0; i < map.recsCollision.length; i++) {

            g.draw(map.recsCollision[i]);

        }
    }

    public void drawTriggerRecs(Graphics g, Map map) {
        for (int i = 0; i < map.recsTrigger.size(); i++) {
            g.draw(map.recsTrigger.get(i));
        }
    }

    /**
     * Calcule le nombre de pixel en x et en y qui rentre dans un rectangle lors
     * d'une collision.
     *
     * @param xa1
     * @param xa2
     * @param ya1
     * @param ya2
     * @param xb1
     * @param xb2
     * @param yb1
     * @param yb2
     * @return La surface complete, en pixel, qui est rentrée en collision avec
     * un rectangle.
     */
    public float calculateIntersects(float xa1, float xa2, float ya1, float ya2, float xb1, float xb2, float yb1, float yb2) {

        xOverLap = Math.max(0, Math.min(xa2, xb2) - Math.max(xa1, xb1));
        yOverLap = Math.max(0, Math.min(ya2, yb2) - Math.max(ya1, yb1));
        float overlapArea = xOverLap * yOverLap;
        return overlapArea;

    }

    public boolean isChestOpened() {

        for (int i = 0; i < listeTile.size(); i++) {
            if (listeTile.get(i).isChestOpened()) {

                return true;
            }
        }
        return false;

    }

    public boolean isPlayerTped(Map map, Player player) {
        for (int i = 0; i < listeTile.size(); i++) {
            if (listeTile.get(i).getTile(map, player.getX(), player.getY()) == 8) {
                return true;
            }
        }
        return false;
    }
    boolean dbug;

    public void dbug(GameContainer gc, Graphics g) {

        if (gc.getInput().isKeyPressed(Input.KEY_F2)) {
            dbug = !dbug;

        }
        if (dbug) {
            drawCollisionRecs(g, this);
            drawTriggerRecs(g, this);
        }

    }

    public int getSolidTileDown() {
        return solidTileDown;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;

    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Player2 getPlayer2() {
        return player2;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public static LinkedHashMap<Item.TypeItem, Item> getEquipedItem() {
        return equipedItem;
    }

    public ArrayList<Tile> getListeTile() {
        return listeTile;
    }
}
