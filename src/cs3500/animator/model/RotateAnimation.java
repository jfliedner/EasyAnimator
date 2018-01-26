package cs3500.animator.model;

/**
 * Represents an animation in which the shape is rotated from an angle to an angle.
 */
public class RotateAnimation extends AAnimation implements Animation {

  private double angleTo;
  private double angleFrom;

  /**
   * Constructor.
   * @param toMutate            The shape to mutate.
   * @param angleTo             The angle you're rotating to.
   * @param angleFrom           The angle you're rotating from.
   * @param start               Start time.
   * @param end                 End time.
   */
  public RotateAnimation(Shape toMutate, double angleTo, double angleFrom, long start, long end) {
    super(toMutate, start, end);
    this.angleTo = angleTo;
    this.angleFrom = angleFrom;
  }

  @Override
  public void changeShape(AShape toModify, long time) {
    toModify.setAngle(this.getValueToChangeShape(angleFrom, angleTo, time, startTime, endTime));
  }

  @Override
  public String animationType() {
    return "Rotate";
  }

  /**
   * Gets the from angle.
   * @return      The angleFrom
   */
  public double getAngleFrom() {
    return angleFrom;
  }

  /**
   * Gets the to angle.
   * @return      The angleTo
   */
  public double getAngleTo() {
    return angleTo;
  }
}
