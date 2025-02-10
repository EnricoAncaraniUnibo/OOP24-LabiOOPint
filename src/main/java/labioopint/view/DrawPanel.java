package labioopint.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import labioopint.model.api.Coordinate;
import labioopint.model.maze.impl.Block;
import labioopint.model.maze.impl.Maze;

public class DrawPanel extends JPanel {
    private final Integer pixelSize;
    private Maze maze;
    private final static Color NO_STREET_COLOR = new Color(88, 57, 39);
    private final static Color STREET_COLOR = new Color(255, 200, 0);

    public DrawPanel(final Dimension size) {
        pixelSize = (int) size.getWidth() / 21;
    }

    public void update(final Maze m) {
        this.maze = m;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < maze.getSize(); i++) {
            for (int j = 0; j < maze.getSize(); j++) {
                Block b = maze.GetBlock(new Coordinate(i, j));
                switch (b.getType()) {
                    case CORNER:
                        switch (b.getRotation()) {
                            case ZERO:
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize, pixelSize,
                                        (pixelSize / 3));
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize, (pixelSize / 3),
                                        pixelSize);
                                g.setColor(STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3,
                                        j * pixelSize + pixelSize + pixelSize / 3,
                                        (pixelSize - pixelSize / 3), (pixelSize / 3));
                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3,
                                        j * pixelSize + pixelSize + pixelSize / 3, (pixelSize / 3),
                                        (pixelSize - pixelSize / 3));
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize + (pixelSize - pixelSize / 3),
                                        j * pixelSize + pixelSize + (pixelSize - pixelSize / 3), (pixelSize / 3),
                                        (pixelSize / 3));
                                break;
                            case NINETY:
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize, pixelSize,
                                        (pixelSize / 3));
                                g.fillRect(i * pixelSize + pixelSize + (pixelSize - pixelSize / 3),
                                        j * pixelSize + pixelSize, (pixelSize / 3),
                                        pixelSize);
                                g.setColor(STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize  + pixelSize / 3,
                                        pixelSize - (pixelSize / 3),
                                        (pixelSize / 3));
                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3,
                                        j * pixelSize + pixelSize + pixelSize / 3,
                                        (pixelSize / 3), (pixelSize - pixelSize / 3));
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize,
                                        j * pixelSize + pixelSize + (pixelSize - pixelSize / 3), (pixelSize / 3),
                                        (pixelSize / 3));
                                break;
                            case ONE_HUNDRED_EIGHTY:
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize + (pixelSize - pixelSize / 3),
                                        j * pixelSize + pixelSize, pixelSize / 3,
                                        pixelSize);
                                g.fillRect(i * pixelSize + pixelSize,
                                        j * pixelSize + pixelSize + (pixelSize - pixelSize / 3), pixelSize,
                                        pixelSize / 3);
                                g.setColor(STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize,
                                        j * pixelSize + pixelSize + (pixelSize - (pixelSize / 3 + pixelSize / 3)),
                                        pixelSize - (pixelSize / 3), pixelSize / 3);
                                g.fillRect(i * pixelSize + pixelSize + (pixelSize - (pixelSize / 3 + pixelSize / 3)),
                                        j * pixelSize + pixelSize,
                                        pixelSize / 3, pixelSize - (pixelSize / 3));
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize,
                                        pixelSize - (pixelSize / 3 + pixelSize / 3),
                                        pixelSize - (pixelSize / 3 + pixelSize / 3));
                                break;
                            case TWO_HUNDRED_SEVENTY:
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize,
                                        j * pixelSize + pixelSize + (pixelSize - pixelSize / 3), pixelSize,
                                        pixelSize / 3);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize, pixelSize / 3,
                                        pixelSize);
                                g.setColor(STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3, j * pixelSize + pixelSize,
                                        pixelSize / 3,
                                        pixelSize - (pixelSize / 3));
                                g.fillRect(i * pixelSize + pixelSize + (pixelSize / 3),
                                        j * pixelSize + pixelSize + (pixelSize - (pixelSize / 3 + pixelSize / 3)),
                                        pixelSize - (pixelSize / 3), pixelSize / 3);
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize + (pixelSize / 3 + pixelSize / 3),
                                        j * pixelSize + pixelSize,
                                        pixelSize - (pixelSize / 3 + pixelSize / 3),
                                        pixelSize - (pixelSize / 3 + pixelSize / 3));
                                break;
                        }
                        break;
                    case CORRIDOR:
                        switch (b.getRotation()) {
                            case ZERO, ONE_HUNDRED_EIGHTY:
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize, pixelSize,
                                        pixelSize / 3);
                                g.setColor(STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize + pixelSize / 3,
                                        pixelSize, pixelSize / 3);
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize,
                                        j * pixelSize + pixelSize + (pixelSize / 3 + pixelSize / 3), pixelSize,
                                        (pixelSize / 3));
                                break;
                            case NINETY, TWO_HUNDRED_SEVENTY:
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize, pixelSize / 3,
                                        pixelSize);
                                g.setColor(STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3, j * pixelSize + pixelSize,
                                        pixelSize / 3, pixelSize);
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize + (pixelSize / 3 + pixelSize / 3),
                                        j * pixelSize + pixelSize,
                                        pixelSize / 3, pixelSize);
                                break;
                        }
                        break;
                    case CROSSING:
                        switch (b.getRotation()) {
                            case ZERO:
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize, pixelSize,
                                        (pixelSize / 3));
                                g.setColor(STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize + pixelSize / 3,
                                        pixelSize, pixelSize / 3);
                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3, j * pixelSize + pixelSize,
                                        pixelSize / 3, pixelSize / 3);
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize,
                                        j * pixelSize + pixelSize + (pixelSize / 3 + pixelSize / 3), pixelSize,
                                        (pixelSize / 3));
                                break;
                            case NINETY:
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize, pixelSize / 3,
                                        pixelSize);
                                g.fillRect(i * pixelSize + pixelSize + (pixelSize / 3 + pixelSize / 3),
                                        j * pixelSize + pixelSize,
                                        (pixelSize / 3), pixelSize);
                                g.setColor(STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3, j * pixelSize + pixelSize,
                                        pixelSize / 3, pixelSize);
                                g.fillRect(i * pixelSize + pixelSize + (pixelSize - pixelSize / 3),
                                        j * pixelSize + pixelSize + pixelSize / 3, (pixelSize / 3),
                                        pixelSize / 3);
                                break;
                            case ONE_HUNDRED_EIGHTY:
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize, pixelSize,
                                        pixelSize / 3);
                                g.fillRect(i * pixelSize + pixelSize,
                                        j * pixelSize + pixelSize + (pixelSize / 3 + pixelSize / 3), pixelSize,
                                        (pixelSize / 3));
                                g.setColor(STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize + pixelSize / 3,
                                        pixelSize, pixelSize / 3);
                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3,
                                        j * pixelSize + pixelSize + (pixelSize - pixelSize / 3), pixelSize / 3,
                                        pixelSize / 3);
                                break;
                            case TWO_HUNDRED_SEVENTY:
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize, pixelSize / 3,
                                        pixelSize);
                                g.setColor(STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize + pixelSize / 3, j * pixelSize + pixelSize,
                                        pixelSize / 3, pixelSize);
                                g.fillRect(i * pixelSize + pixelSize, j * pixelSize + pixelSize + pixelSize / 3,
                                        pixelSize / 3, pixelSize / 3);
                                g.setColor(NO_STREET_COLOR);
                                g.fillRect(i * pixelSize + pixelSize + (pixelSize / 3 + pixelSize / 3),
                                        j * pixelSize + pixelSize,
                                        pixelSize / 3, pixelSize);
                                break;
                        }
                        break;
                }
            }
        }
    }

}
