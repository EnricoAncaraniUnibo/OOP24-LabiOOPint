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

public final class DrawPanel extends JPanel {
        private final LogicDrawPanel ldp;
        public static final long serialVersionUID = 42L;

        public DrawPanel(final Dimension size, final GameController gc) {
                ldp = new LogicDrawPanelImpl(gc, size);
        }

        public void draw() {
                repaint();
        }
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

        public Integer getBlockSize() {
                return ldp.getPixelSize();
        }
}
