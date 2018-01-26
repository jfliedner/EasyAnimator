package cs3500.animator.model;

/**
 * An abstract animation containing fields and methods shared by all concrete animations.
 */

public abstract class AAnimation implements Animation, Comparable {

  protected long startTime;
  protected long endTime;
  protected Shape shapeToAnimate;

  /**
   * Default constructor.
   */
  AAnimation() {
    startTime = 0;
    endTime = 0;
    shapeToAnimate = null;
  }

  /**
   * Constructor.
   * @param toMutate        The shape this animation will be mutating
   * @param startTime       The start time for the animation
   * @param endTime         The end time for the animation
   */
  AAnimation(Shape toMutate, long startTime, long endTime) {
    if (endTime < startTime) {
      throw new IllegalArgumentException("End time cannot be before start time");
    }
    if (toMutate == null) {
      throw new IllegalArgumentException("Invalid shape");
    }
    this.shapeToAnimate = toMutate;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public double getValueToChangeShape(double startingVal, double endingVal, long timeNow,
                                      long timeStart, long timeEnd) {
    double result = 0;
    result = result + startingVal * ((double)(timeEnd - timeNow) / (double)(timeEnd - timeStart));
    result = result + endingVal * ((double)(timeNow - timeStart) / (double)(timeEnd - timeStart));
    return result;
  }

  /**
   * Gets the startTime for this animation.
   * @return        The startTime
   */
  public double getStartTime() {
    return startTime;
  }

  /**
   * Gets the endTime for this animation.
   * @return        The endTime
   */
  public double getEndTime() {
    return endTime;
  }

  /**
   * Compares animations based on their start time, from the Comparable interface.
   * @param o       Object to compare
   * @return        Result of the comparison
   */
  @Override
  public int compareTo(Object o) {
    if (o instanceof AAnimation) {
      if (this.getStartTime() < ((AAnimation) o).getStartTime()) {
        return -1;
      }
      else if (this.getStartTime() > ((AAnimation) o).getStartTime()) {
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

  /**
   * Gets the shape that it is animating.
   * @return      The shape to animate
   */
  public Shape getShapeToAnimate() {
    return this.shapeToAnimate;
  }
}
