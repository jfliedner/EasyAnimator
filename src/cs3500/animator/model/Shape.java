package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents a general shape, forces behaviors that all shapes have in common.
 */
public interface Shape extends Comparable {

  /**
   * Executes the animations for the shape.
   * @param time            The current time.
   */
  void executeAnimations(long time);

  /**
   * Add an animation to a cs3500.animator.model.Shape's list of animations.
   * @param add         The animation to add.
   */
  void addAnAnimation(Animation add);

  /**
   * Gets the shape's color.
   * @return      The shape's color
   */
  Color getColor();

  /**
   * Sets the shape's color.
   * @param toSet     The color to set as.
   */
  void setColor(Color toSet);

  /**
   * Gets the shape's angle.
   * @return          The angle of the shape.
   */
  double getAngle();

  /**
   * Sets the shape's angle.
   * @param toSet   The angle to set as.
   */
  void setAngle(double toSet);

  /**
   * Gets the position X of the shape.
   * @return        The shape's position X.
   */
  double getPosnX();

  /**
   * Sets the shape's position x.
   * @param toSet  The position x to set as.
   */
  void setPosnX(double toSet);

  /**
   * Gets the position Y of the shape.
   * @return        The shape's position Y.
   */
  double getPosnY();

  /**
   * Sets the shape's position y.
   * @param toSet   The position y to set as.
   */
  void setPosnY(double toSet);

  /**
   * Gets the width of the shape.
   * @return    The shape's width
   */
  double getWidth();

  /**
   * Sets the width of the shape.
   * @param toSet     The width to set as.
   */
  void setWidth(double toSet);

  /**
   * Gets the height of the shape.
   * @return          The height of the shape.
   */
  double getHeight();

  /**
   * Sets the height of the shape.
   * @param toSet     The height to set as.
   */
  void setHeight(double toSet);

  /**
   * Gets the name of the shape.
   * @return    The shape's name.
   */
  String getName();

  /**
   * Gets the time for the shape to appear.
   * @return      The time to appear.
   */
  long getTimeAppear();

  /**
   * Gets the time for the shape to disappear.
   * @return      The time to disappear.
   */
  long getTimeDisappear();

  /**
   * Gets the animations the shape has to execute.
   * @return      The animations for the shape.
   */
  ArrayList<Animation> getAnimationsToExecute();

  /**
   * Gets the type of the shape as a String.
   * @return      The type of the shape.
   */
  String getShapeType();

  @Override
  int compareTo(Object o);

  /**
   * Makes a copy of this shape.
   * @return    The copy
   */
  Shape makeCopy();

  /**
   * Gets the layer level for this shape.
   * @return          The layer level
   */
  int getLayerLevel();

}
