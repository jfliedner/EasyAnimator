package cs3500.animator.model;

import java.awt.Color;

/**
 * A shape that represents a rectangle, containing a width and a height.
 */
public class Rectangle extends AShape implements Shape {


  /**
   * Constructor.
   * @param width               The rectangle's width
   * @param height              The rectangle's height
   * @param shapeColor          The rectangle's color
   * @param shapeName           The rectangle's name
   * @param timeAppear          The time to appear
   * @param timeDisappear       The time to disappear
   * @param posnX               The rectangle's position X
   * @param posnY               The rectangle's position Y
   * @param angle               The rectangles's angle
   */
  public Rectangle(double width, double height, Color shapeColor, String shapeName, long timeAppear,
                   long timeDisappear, double posnX, double posnY, double angle, int layerLevel) {
    super(shapeColor, shapeName, timeAppear, timeDisappear, posnX, posnY, angle, width, height,
            layerLevel);
  }

  @Override
  public String getShapeType() {
    return "rectangle";
  }

  @Override
  public Shape makeCopy() {
    Shape newShape = new Rectangle(this.getWidth(), this.getHeight(), this.getColor(),
            this.getName(),
            this.getTimeAppear(), this.getTimeDisappear(), this.getPosnX(), this.getPosnY(),
            this.getAngle(), this.getLayerLevel());
    for (Animation a : this.getAnimationsToExecute()) {
      newShape.addAnAnimation(a);
    }
    return newShape;
  }
}
