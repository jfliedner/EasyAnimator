package cs3500.animator.model;

import java.awt.Color;

/**
 * A shape that represents an cs3500.animator.model.Oval, containing a radius x and radius y.
 */
public class Oval extends AShape implements Shape {


  /**
   * Constructor.
   * @param radiusX             The oval's width/radius X
   * @param radiusY             The oval's height/radius Y
   * @param shapeColor          The oval's color
   * @param shapeName           The oval's name
   * @param timeAppear          The time to appear
   * @param timeDisappear       The time to disappear
   * @param posnX               The oval's position X
   * @param posnY               The oval's position Y
   * @param angle               The oval's angle
   */
  public Oval(double radiusX, double radiusY, Color shapeColor, String shapeName, long timeAppear,
              long timeDisappear, double posnX, double posnY, double angle) {
    super(shapeColor, shapeName, timeAppear, timeDisappear, posnX, posnY, angle, radiusX, radiusY);
  }

  @Override
  public String getShapeType() {
    return "oval";
  }

  @Override
  public Shape makeCopy() {
    Shape newShape = new Oval(this.getWidth(), this.getHeight(), this.getColor(), this.getName(),
            this.getTimeAppear(), this.getTimeDisappear(), this.getPosnX(), this.getPosnY(),
            this.getAngle());
    for (Animation a : this.getAnimationsToExecute()) {
      newShape.addAnAnimation(a);
    }
    return newShape;
  }
}
