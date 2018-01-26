package cs3500.animator.model;

/**
 * Interface for an animation, containing methods to force behaviors shared by all animations.
 */
public interface Animation extends Comparable {

  /**
   * Changes the shape passed in based on the concrete cs3500.animator.model.Animation class
   * it is in.
   *
   * @param toModify      The shape to mutate
   * @param time          The current time
   */
  void changeShape(AShape toModify, long time);


  /**
   * Calculates a value to change the shape with based on the current times and the times for the
   * animation, along with the values the shape is changing from and to in order to allow
   * for a gradual change.
   * @param startingVal         The starting value to be mutated
   * @param endingVal           The ending value the shape needs to reach
   * @param timeNow             The current time
   * @param timeStart           The time this animation starts
   * @param timeEnd             The time this animation ends
   * @return                    A value based on time and values being changed
   */
  double getValueToChangeShape(double startingVal, double endingVal, long timeNow,
                               long timeStart, long timeEnd);

  /**
   * Retrieves the start time.
   * @return      The start time
   */
  double getStartTime();

  /**
   * Retrieves the end time.
   * @return     The end time
   */
  double getEndTime();

  /**
   * Retrieves the type of animation this is.
   * @return        The type of animation
   */
  String animationType();

  @Override
  int compareTo(Object o);

}
