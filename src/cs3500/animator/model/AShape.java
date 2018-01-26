package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * An abstract shape class containing fields and methods shared by all concrete shapes.
 */
public abstract class AShape implements Shape {

  private Color shapeColor = new Color(0, 0, 0);

  private String shapeName = "";

  private long timeAppear = 0;
  private long timeDisappear = 0;

  private double width = 0;
  private double height = 0;

  private double posnX = 0;
  private double posnY = 0;

  private double angle = 0;

  private ArrayList<Animation> animationsToExecute = new ArrayList<>();

  private int layerLevel;

  /**
   * Constructor.
   * @param shapeColor                The shape's color
   * @param shapeName                 The shape's name
   * @param timeAppear                The time for the shape to appear
   * @param timeDisappear             The time for the shape to disappear
   * @param posnX                     The shape's position x
   * @param posnY                     The shape's position y
   * @param angle                     The shape's angle
   * @param width                     The shape's width
   * @param height                    The shape's height
   */
  AShape(Color shapeColor, String shapeName, long timeAppear, long timeDisappear, double posnX,
         double posnY, double angle, double width, double height, int layerLevel) {
    this.shapeColor = shapeColor;
    this.shapeName = shapeName;
    this.timeAppear = timeAppear;
    this.timeDisappear = timeDisappear;
    this.posnX = posnX;
    this.posnY = posnY;
    this.angle = angle;
    this.width = width;
    this.height = height;
    this.layerLevel = layerLevel;
  }


  @Override
  public void executeAnimations(long time) {
    if (this.timeAppear <= time && this.timeDisappear > time) {
      for (Animation a : this.animationsToExecute) {
        if (a.getStartTime() <= time && a.getEndTime() > time) {
          a.changeShape(this, time);
        }
      }
    }
  }

  @Override
  public void addAnAnimation(Animation add) {
    if (doesNotConflict(add)) {
      animationsToExecute.add(add);
    }
    else {
      throw new IllegalArgumentException("Cannot add this animation because it conflicts with"
              + "the time of another animation of the same type");
    }
  }

  /**
   * Helper method to aid in adding an animation to verify if the animation conflicts.
   * @param toCheck         Animation to check for conflict
   * @return                If it conflicts or not.
   */
  private boolean doesNotConflict(Animation toCheck) {
    double timeStart = toCheck.getStartTime();
    double timeEnd = toCheck.getEndTime();
    String type = toCheck.animationType();
    boolean doesNot = true;
    for (int i = 0; i < animationsToExecute.size(); i++) {
      if (animationsToExecute.get(i).animationType().equals(type)) {
        double aStart = animationsToExecute.get(i).getStartTime();
        double aEnd = animationsToExecute.get(i).getEndTime();
        if (timeStart >= aStart && aEnd >= timeStart) {
          doesNot = false;
          break;
        }
        else if (timeStart <= aStart && timeEnd >= aStart) {
          doesNot = false;
          break;
        }
      }
    }
    return doesNot;
  }

  /**
   * Compares shapes based on their layer level, from the Comparable interface.
   * @param o       Object to compare
   * @return        Result of the comparison
   */
  @Override
  public int compareTo(Object o) {
    if (o instanceof AShape) {
      if (this.getLayerLevel() < ((AShape) o).getLayerLevel()) {
        return -1;
      }
      else if (this.getLayerLevel() > ((AShape) o).getLayerLevel()) {
        return 1;
      }
      else {
        return 0;
      }
    }
    else {
      throw new IllegalArgumentException("Not an cs3500.aAnimation");
    }
  }

  @Override
  public Color getColor() {
    return this.shapeColor;
  }

  @Override
  public void setColor(Color toSet) {
    this.shapeColor = toSet;
  }

  @Override
  public double getAngle() {
    return this.angle;
  }

  @Override
  public void setAngle(double toSet) {
    this.angle = toSet;
  }

  @Override
  public double getPosnX() {
    return this.posnX;
  }

  @Override
  public void setPosnX(double toSet) {
    this.posnX = toSet;
  }

  @Override
  public double getPosnY() {
    return this.posnY;
  }

  @Override
  public void setPosnY(double toSet) {
    this.posnY = toSet;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public void setWidth(double toSet) {
    this.width = toSet;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public void setHeight(double toSet) {
    this.height = toSet;
  }

  @Override
  public String getName() {
    return this.shapeName;
  }

  @Override
  public long getTimeAppear() {
    return this.timeAppear;
  }

  @Override
  public long getTimeDisappear() {
    return this.timeDisappear;
  }

  @Override
  public ArrayList<Animation> getAnimationsToExecute() {
    return this.animationsToExecute;
  }

  @Override
  public int getLayerLevel() {
    return this.layerLevel;
  }



}
