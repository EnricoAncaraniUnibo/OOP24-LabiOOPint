package labioopint.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

import labioopint.controller.api.GameController;
import labioopint.controller.api.LogicDrawPanel;
import labioopint.controller.impl.LogicDrawPanelImpl;
import labioopint.model.utilities.api.Pair;
/**
 * Represents a custom panel for rendering game elements.
 * The `DrawPanel` class is responsible for drawing images and blocks on the screen
 * based on the logic provided by the {@link LogicDrawPanel}.
 */
public final class DrawPanel extends JPanel {
        private final LogicDrawPanel ldp;
        public static final long serialVersionUID = 42L;
        /**
         * Constructs a new {@code DrawPanel} with the specified size and game controller.
         *
         * @param size the dimensions of the user screen
         * @param gc the {@link GameController} used to initialize the logic for drawing
         */
        public DrawPanel(final Dimension size, final GameController gc) {
                ldp = new LogicDrawPanelImpl(gc, size);
        }
        /**
         * Triggers a repaint of the panel to update the drawn elements.
         */
        public void draw() {
                repaint();
        }
        /**
         * Paints the components of the panel, including images and blocks.
         * This method is overridden to provide custom rendering logic.
         *
         * @param g the {@link Graphics} object used for drawing
         */
        @Override
        protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                final Graphics2D g2 = (Graphics2D) g;
                final AffineTransform old = g2.getTransform();
                for (final Pair<Pair<Image, Double>, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> element : ldp
                                .getImagesBlocks()) {
                        g2.rotate(element.getFirst().getSecond());
                        g2.drawImage(element.getFirst().getFirst(), element.getSecond().getFirst().getFirst(),
                                        element.getSecond().getFirst().getSecond(),
                                        element.getSecond().getSecond().getFirst(),
                                        element.getSecond().getSecond().getSecond(), this);
                        g2.setTransform(old);
                }
        }

        /**
         * Retrive the information about the size of a drawed block.
         * @return the size of a drawed block.
         */
        public Integer getBlockSize() {
                return ldp.getPixelSize();
        }
}
