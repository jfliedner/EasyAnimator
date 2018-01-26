package cs3500.animator.view.panels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;

import cs3500.animator.model.Shape;
import javax.swing.JPanel;

/**
 * The panel for the animations to be drawn on, extends JPanel.
 */
public class AnimationPanel extends JPanel {
  private ArrayList<Shape> shapes;

  /**
   * Constructor for AnimationPanel, takes in an ArrayList of Shapes.
   * @param shapes        Shapes to animate
   */
  public AnimationPanel(ArrayList<Shape> shapes) {
    super();
    this.shapes = shapes;
    this.setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    for (Shape s: shapes) {
      String type = s.getShapeType();
      g2d.setColor(s.getColor());
      switch (type) {
        case "oval":
          g2d.fillOval((int) s.getPosnX(),(int)s.getPosnY(), (int) s.getWidth(),
                  (int) s.getHeight());
          break;
        case "rectangle":
          g2d.fillRect((int)s.getPosnX(),(int)s.getPosnY(), (int)s.getWidth(),
                  (int)s.getHeight());
          break;
        default: throw new IllegalArgumentException("Invalid shape");

      }
    }
  }

  /**
   * Sets the shapes field.
   * @param shapes      Shapes to set shapes as
   */
  public void setShapes(ArrayList<Shape> shapes) {
    this.shapes = shapes;
  }

}
