package labioopint.view;

import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import labioopint.model.Block.impl.BlockImpl;
import labioopint.model.Core.impl.ImageLoader;
import labioopint.model.Core.impl.TurnManager;
import labioopint.model.Enemy.api.Enemy;
import labioopint.model.Maze.impl.MazeImpl;
import labioopint.model.Player.impl.PlayerImpl;
import labioopint.model.PowerUp.api.PowerUp;
import labioopint.model.api.Coordinate;
import labioopint.model.api.DualMap;
import labioopint.model.api.Pair;

public class LogicDrawPanel {

    private TurnManager turn;
    private Integer pixelSize;
    private MazeImpl maze;
    private DualMap<PlayerImpl> coorPlayers;
    private DualMap<Enemy> coorEnemies;
    private DualMap<PowerUp> coorPowerUps;
    private BlockImpl outsideBlock;

    public LogicDrawPanel(TurnManager tu, Dimension size) {
        turn = tu;
        pixelSize = (int) size.getWidth() / 13;
        ImageLoader.load();
    }

    public Integer getPixelSize() {
        return pixelSize;
    }

    public void updateData(final MazeImpl m, final DualMap<PlayerImpl> mapPlayers,
            final DualMap<Enemy> mapEnemies, final DualMap<PowerUp> mapPowerUps,
            final BlockImpl outside) {
        this.maze = m;
        this.coorPlayers = mapPlayers;
        this.coorEnemies = mapEnemies;
        this.coorPowerUps = mapPowerUps;
        this.outsideBlock = outside;
    }

    public Boolean canDraw(){
        return maze!=null;
    }

    public List<Pair<Pair<Image,Double>,Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>> getImagesBlocks() {
        List<Pair<Pair<Image,Double>,Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>> ls = new ArrayList<>();
        BlockImpl b;
        boolean end = false;
        Image imageTemp = null;
        Double rotationTemp = null;
        Integer valuePrint1 = null;
        Integer valuePrint2 = null;
        Pair<Image,Double> pImageRotation;
        Pair<Integer,Integer> pPositions;
        Pair<Integer,Integer> pSize;
        Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> pNumbers;
        Pair<Pair<Image,Double>,Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>> pFinal;
        for (int i = 0; i <= maze.getSize() && end==false; i++) {
            for (int j = 0; j < maze.getSize() && end==false; j++) {
                if(i == maze.getSize()) {
                        b = outsideBlock;
                        j = maze.getSize()+1;
                        i = 0;
                        end=true;
                } else {
                        b = maze.GetBlock(new Coordinate(i, j)).get();
                }
                switch (b.getType()) {
                        case CORNER:
                                imageTemp = (ImageLoader.getImage("Corner").get());
                                break;
                        case CORRIDOR:
                                imageTemp = (ImageLoader.getImage("Corridor").get());
                                break;
                        case CROSSING:
                                imageTemp = (ImageLoader.getImage("Crossing").get());
                                break;
                }
                switch (b.getRotation()) {
                    case ZERO:
                            rotationTemp = Math.toRadians(0);
                            valuePrint1 = j*pixelSize;
                            valuePrint2 = i*pixelSize;
                            break;
                    case NINETY:
                            rotationTemp = Math.toRadians(-90);
                            valuePrint1=(-i*pixelSize)-pixelSize;
                            valuePrint2= j*pixelSize;
                            break;
                    case ONE_HUNDRED_EIGHTY:
                            rotationTemp = Math.toRadians(-180);
                            valuePrint1 = (-j*pixelSize)-pixelSize;
                            valuePrint2 = (-i*pixelSize)-pixelSize;
                            break;
                    case TWO_HUNDRED_SEVENTY:
                            rotationTemp = Math.toRadians(-270);
                            valuePrint1 = i*pixelSize;
                            valuePrint2 = (-j*pixelSize)-pixelSize;
                            break;
                }
                pImageRotation = new Pair<Image,Double>(imageTemp, rotationTemp);
                pPositions = new Pair<Integer,Integer>(valuePrint1, valuePrint2);
                pSize = new Pair<Integer,Integer>(pixelSize, pixelSize);
                pNumbers = new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(pPositions, pSize);
                pFinal = new Pair<Pair<Image,Double>,Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>(pImageRotation, pNumbers);
                ls.add(pFinal);
            }
        }
        for (PlayerImpl p : coorPlayers.getElemets()) {
            Coordinate c = coorPlayers.getCoordinateFromElement(p);
            if(p == turn.GetCurrentPlayer()) {
                pImageRotation = new Pair<Image,Double>(ImageLoader.getImage(""+p.getID()+"Turn").get(), Math.toRadians(0));
            }else {
                pImageRotation = new Pair<Image,Double>(ImageLoader.getImage(""+p.getID()+"").get(), Math.toRadians(0));
            }
            pPositions = new Pair<Integer,Integer>(c.getColumn()*pixelSize+pixelSize/4, c.getRow()*pixelSize+pixelSize/4);
            pSize = new Pair<Integer,Integer>(pixelSize*3/5, pixelSize*3/5);
            pNumbers = new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(pPositions, pSize);
            pFinal = new Pair<Pair<Image,Double>,Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>(pImageRotation, pNumbers);
            ls.add(pFinal);
        }
        for (Enemy e : coorEnemies.getElemets()) {
            Coordinate c = coorEnemies.getCoordinateFromElement(e);
            pImageRotation = new Pair<Image,Double>(ImageLoader.getImage("Monster").get(), Math.toRadians(0));
            pPositions = new Pair<Integer,Integer>(c.getColumn()*pixelSize+pixelSize/4, c.getRow()*pixelSize+pixelSize/4);
            pSize = new Pair<Integer,Integer>(pixelSize*3/5, pixelSize*3/5);
            pNumbers = new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(pPositions, pSize);
            pFinal = new Pair<Pair<Image,Double>,Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>(pImageRotation, pNumbers);
            ls.add(pFinal);
        }
        for (PowerUp po : coorPowerUps.getElemets()) {
            Coordinate c = coorPowerUps.getCoordinateFromElement(po);
            pImageRotation = new Pair<Image,Double>(ImageLoader.getImage(""+po.getName()+"").get(), Math.toRadians(0));
            pPositions = new Pair<Integer,Integer>(c.getColumn()*pixelSize+pixelSize/4, c.getRow()*pixelSize+pixelSize/4);
            pSize = new Pair<Integer,Integer>(pixelSize*3/5, pixelSize*3/5);
            pNumbers = new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(pPositions, pSize);
            pFinal = new Pair<Pair<Image,Double>,Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>(pImageRotation, pNumbers);
            ls.add(pFinal);
        }
        return ls;
    }

}
    

