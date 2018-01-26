package cs3500.animator.controller;

import java.awt.*;
import java.util.ArrayList;


/**
 * Interface for controllers which interact with the user.
 */
interface IInteractiveController {

  /**
   * Sets the animation to loop or not loop.
   * @param toLoop      If the animations is meant to loop.
   */
  void setLoop(boolean toLoop);

  /**
   * Returns if this animation enables loopback.
   * @return  true if loopback is enabled.
   */
  boolean toLoop();

  /**
   * Gets the delay of the timer right now.
   * @return the delay of the timer.
   */
  int getDelay();

  /**
   * Increases the tempo of the animation.
   */
  void incTempo();

  /**
   * Decreases the tempo of the animation.
   */
  void decTempo();

  /**
   * Resets the animation.
   */
  void reset();

  /**
   * Starts the animation.
   */
  void start();

  /**
   * Draws the animation.
   */
  void draw();

  /**
   * Adds the command to the log (a visual cue).
   * @param command     The command to add
   */
  void addToLog(String command);

  /**
   * Create an SVG file with the given file name as a String.
   * @param filename      The filename to create.
   */
  void createSVG(String filename);

  /**
   * Gets the name of the shapes the controller is using.
   * @return      The list of shape names
   */
  ArrayList<String> getShapeNames();

  /**
   * Reset the shapes to be the ones selected by the user.
   */
  void resetShapes(ArrayList<String> selectedShapes);

  /**
   * Gets the log of this animation up to now.
   */
  ArrayList<String> getLog();

  /**
   * Sets the background of the animation to the given Color.
   */
  void setBackground(Color color);

  /**
   * Sets the delay of this animation to the given number.
   */
  void setDelay(int delay);

}
