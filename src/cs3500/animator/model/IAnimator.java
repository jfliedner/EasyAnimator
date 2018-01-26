package cs3500.animator.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Interface for Animator model, forces behavior on any types of models.
 */
public interface IAnimator {

  /**
   * Animates all the shapes within this model.
   */
  void animateShapes();

  /**
   * Adds a shape to the model.
   * @param toAdd     The shape to add
   */
  void addShape(Shape toAdd);

  /**
   * Increases the current time by the long passed in.
   */
  void addTime(long toAdd);

  /**
   * Gets the list of shapes in this animation.
   * @return    the shapes in this animation.
   */
  ArrayList<Shape> getShapes();

  /**
   * Gets the original list of shapes.
   * @return    The original list of shapes
   */
  ArrayList<Shape> getOriginals();

  /**
   * Resets the shapes of this model to the original shapes.
   */
  void reset();

  /**
   * Resets the tempo of this model, true for increase tempo and false for decrease.
   */
  void incTempo(boolean temp);

  /**
   * Sets the shapes to be the ones passed in.
   * @param selected      The shapes selected by the user
   */
  void setShapes(ArrayList<Shape> selected);

  /**
   * Adds or sets the background color of this animation to the given color.
   * @param color   the background color to be changed to.
   */
  void setBackground(Color color);
}
