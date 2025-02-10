package labioopint.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import labioopint.model.api.Coordinate;
import labioopint.model.maze.api.BlockType;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Maze;

public class DrawPanel extends JPanel {
    private final Integer pixelSize;
    private Maze maze;
    private final static Color NO_STREET_COLOR = new Color(88,57,39);
    private final static Color STREET_COLOR = new Color(255,200,0);

    public DrawPanel(final Dimension size) {
        pixelSize = (int)size.getWidth()/20;
    }

    public void update(final Maze m) {
        this.maze = m;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
            g.setColor(NO_STREET_COLOR);
            g.fillRect(pixelSize,pixelSize,pixelSize,pixelSize/4);
            g.fillRect(pixelSize,pixelSize,pixelSize/4,pixelSize);
            g.setColor(STREET_COLOR);
            g.fillRect(pixelSize+pixelSize/4,pixelSize+pixelSize/4,pixelSize-(pixelSize/4),pixelSize/3);
            g.fillRect(pixelSize+pixelSize/4,pixelSize+pixelSize/4,pixelSize/3,pixelSize-(pixelSize/4));
            g.setColor(NO_STREET_COLOR);
            g.fillRect(pixelSize+pixelSize/4+pixelSize/3,pixelSize+pixelSize/4+pixelSize/3,pixelSize-(pixelSize/4+pixelSize/3),pixelSize-(pixelSize/4+pixelSize/3));

            g.setColor(NO_STREET_COLOR);
            g.fillRect(pixelSize+pixelSize,pixelSize,pixelSize,pixelSize/4);
            g.fillRect(pixelSize+pixelSize+(pixelSize-pixelSize/4),pixelSize,pixelSize/4,pixelSize);
            g.setColor(STREET_COLOR);
            g.fillRect(pixelSize+pixelSize,pixelSize+pixelSize/4,pixelSize-(pixelSize/4),pixelSize/3);
            g.fillRect(pixelSize+pixelSize+(pixelSize-(pixelSize/4+pixelSize/3)),pixelSize+pixelSize/4,pixelSize/3,pixelSize-(pixelSize/4));
            g.setColor(NO_STREET_COLOR);
            g.fillRect(pixelSize+pixelSize,pixelSize+pixelSize/4+pixelSize/3,pixelSize-(pixelSize/4+pixelSize/3),pixelSize-(pixelSize/4+pixelSize/3));

            g.setColor(NO_STREET_COLOR);
            g.fillRect(pixelSize+pixelSize+(pixelSize-pixelSize/4),pixelSize+pixelSize,pixelSize/4,pixelSize);
            g.fillRect(pixelSize+pixelSize,pixelSize+pixelSize+(pixelSize-pixelSize/4),pixelSize,pixelSize/4);
            g.setColor(STREET_COLOR);
            g.fillRect(pixelSize+pixelSize,pixelSize+pixelSize+(pixelSize-(pixelSize/4+pixelSize/3)),pixelSize-(pixelSize/4),pixelSize/3);
            g.fillRect(pixelSize+pixelSize+(pixelSize-(pixelSize/4+pixelSize/3)),pixelSize+pixelSize,pixelSize/3,pixelSize-(pixelSize/4));
            g.setColor(NO_STREET_COLOR);
            g.fillRect(pixelSize+pixelSize,pixelSize+pixelSize,pixelSize-(pixelSize/4+pixelSize/3),pixelSize-(pixelSize/4+pixelSize/3));

            g.setColor(NO_STREET_COLOR);
            g.fillRect(pixelSize,pixelSize+pixelSize+(pixelSize-pixelSize/4),pixelSize,pixelSize/4);
            g.fillRect(pixelSize,pixelSize+pixelSize,pixelSize/4,pixelSize);
            g.setColor(STREET_COLOR);
            g.fillRect(pixelSize+pixelSize/4,pixelSize+pixelSize,pixelSize/3,pixelSize-(pixelSize/4));
            g.fillRect(pixelSize+(pixelSize/4),pixelSize+pixelSize+(pixelSize-(pixelSize/4+pixelSize/3)),pixelSize-(pixelSize/4),pixelSize/3);
            g.setColor(NO_STREET_COLOR);
            g.fillRect(pixelSize+(pixelSize/4+pixelSize/3),pixelSize+pixelSize,pixelSize-(pixelSize/4+pixelSize/3),pixelSize-(pixelSize/4+pixelSize/3));
    }

    /*
     * 
     * protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        for(int i=0; i<maze.getSize(); i++) {
            for(int j=0; j<maze.getSize(); j++) {
                //Block b = maze.GetBlock(new Coordinate(i, j));
                Block b= new Block(BlockType.CORNER);
                switch (b.getType()) {
                    case CORNER:
                        switch (b.getRotation()) {
                            case ZERO:
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(20,20,20,20);
                                /*g.fillRect(i+pixelSize, j+pixelSize,pixelSize+5,pixelSize+10);
                                g.setColor(STREET_COLOR);
                                g.fillRect(i+pixelSize*2,j+pixelSize*2,pixelSize+10,pixelSize+5);
                                g.fillRect(i+pixelSize*2, j+pixelSize*2,pixelSize+5,pixelSize+10);
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i+pixelSize,j+pixelSize,pixelSize+10,pixelSize+10);
                                break;
                                 * 
                                 */
                                /* 
                                 case NINETY:
                                 g.fillRect(i+pixelSize,j+pixelSize,pixelSize+10,pixelSize+5);
                                 g.fillRect(i+pixelSize, j+pixelSize,pixelSize+10,pixelSize+5);
                                 break;
                             case ONE_HUNDRED_EIGHTY:
                                 g.fillRect(i+pixelSize,j+pixelSize,pixelSize+10,pixelSize+5);
                                 g.fillRect(i+pixelSize, j+pixelSize,pixelSize+10,pixelSize+5);
                                 break;
                             case TWO_HUNDRED_SEVENTY:
                                 g.fillRect(i+pixelSize,j+pixelSize,pixelSize+10,pixelSize+5);
                                 g.fillRect(i+pixelSize, j+pixelSize,pixelSize+10,pixelSize+5);
                                 break;
                         }
                         break;
                     case CORRIDOR:
                     switch (b.getRotation()) {
                         case ZERO:
                             g.fillRect(i+pixelSize,j+pixelSize,pixelSize+10,pixelSize+5);
                             g.fillRect(i+pixelSize, j+pixelSize,pixelSize+10,pixelSize+5);
                             break;
                         case NINETY:
                             g.fillRect(i+pixelSize,j+pixelSize,pixelSize+10,pixelSize+5);
                             g.fillRect(i+pixelSize, j+pixelSize,pixelSize+10,pixelSize+5);
                             break;
                         case ONE_HUNDRED_EIGHTY:
                             g.fillRect(i+pixelSize,j+pixelSize,pixelSize+10,pixelSize+5);
                             g.fillRect(i+pixelSize, j+pixelSize,pixelSize+10,pixelSize+5);
                             break;
                         case TWO_HUNDRED_SEVENTY:
                             g.fillRect(i+pixelSize,j+pixelSize,pixelSize+10,pixelSize+5);
                             g.fillRect(i+pixelSize, j+pixelSize,pixelSize+10,pixelSize+5);
                             break;
                         }
                         break;
                     case CROSSING:
                     switch (b.getRotation()) {
                         case ZERO:
                             g.fillRect(i+pixelSize,j+pixelSize,pixelSize+10,pixelSize+5);
                             g.fillRect(i+pixelSize, j+pixelSize,pixelSize+10,pixelSize+5);
                             break;
                         case NINETY:
                             g.fillRect(i+pixelSize,j+pixelSize,pixelSize+10,pixelSize+5);
                             g.fillRect(i+pixelSize, j+pixelSize,pixelSize+10,pixelSize+5);
                             break;
                         case ONE_HUNDRED_EIGHTY:
                             g.fillRect(i+pixelSize,j+pixelSize,pixelSize+10,pixelSize+5);
                             g.fillRect(i+pixelSize, j+pixelSize,pixelSize+10,pixelSize+5);
                             break;
                         case TWO_HUNDRED_SEVENTY:
                             g.fillRect(i+pixelSize,j+pixelSize,pixelSize+10,pixelSize+5);
                             g.fillRect(i+pixelSize, j+pixelSize,pixelSize+10,pixelSize+5);
                             break;
                         }
                         break;
                 }
             }
         }
     }
     * 
     */
    
}
