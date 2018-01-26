package cs3500.animator.view;

import cs3500.animator.model.Shape;

import java.awt.*;
import java.util.ArrayList;

/**
 * The GENERAL, view interface for the Easy Animator.
 */
public interface IView {

  /**
   * Creates the text output for this view, only applicable to Text and SVG.
   * @return    The text output
   */
  String getOutput();

  /**
   * Make the view visible. This is usually called
   * after the view is constructed.
   */
  void makeVisible();

  /**
   * Sets the shapes field within the view.
   * @param shapes      Shapes to set the shapes field as.
   */
  void setShapes(ArrayList<Shape> shapes);

  /**
   * Transmit an error message to the view, in case
   * the command could not be processed correctly.
   * @param error         The message to be shown
   */
  void showErrorMessage(String error);

  /**
   * Signal the view to draw itself.
   */
  void refresh();

  /**
   * Gets the type of view.
   * @return  The type of view as a String.
   */
  String getViewType();
}
