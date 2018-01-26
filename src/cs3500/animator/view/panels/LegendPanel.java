package cs3500.animator.view.panels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * A panel for a keyboard legend, some visual cues for the user.
 */
public class LegendPanel extends JPanel {
  private ArrayList<String> legend = new ArrayList<>();

  /**
   * Constructor for the legend panel.
   */
  public LegendPanel() {
    legend.add("==== KEYBOARD LEGEND ====");
    legend.add("Start: Key 's'");
    legend.add("Pause: Key 'p'");
    legend.add("Resume: Key 'c'");
    legend.add("Restart: Key 'r");
    legend.add("Enable Loop: Key 'l'");
    legend.add("Disable Loop: Key 'k'");
    legend.add("Increase Speed: ↑");
    legend.add("Decrease Speed: ↓");
    legend.add("Export to SVG: 'e'");
    legend.add("Choose Shapes: 'a'");
    legend.add("Choose Background Color: 'b'");
    legend.add("Enable scrubbing: 'f'");
    this.setBackground(Color.BLACK);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.white);
    g2d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
    for (String s: legend) {
      g2d.drawString(s, 20, 30 + 30 * legend.indexOf(s));
    }
  }
}
